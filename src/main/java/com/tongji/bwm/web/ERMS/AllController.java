package com.tongji.bwm.web.ERMS;

import com.tongji.bwm.entity.ERMS.*;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.filters.validation.CustomValidationException;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.pojo.FrontMetadataField;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.service.ERMS.*;
import com.tongji.bwm.solr.Client.SolrConfig;
import com.tongji.bwm.web.Basic.BaseController;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/ERMS/All")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ARTICLE','ROLE_EDITOR')")
public class AllController extends BaseController {

    @Autowired
    private AllService allService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RelationMetadataFieldService relationMetadataFieldService;

    @Autowired
    private MetadataFieldRegistryService metadataFieldRegistryService;

    @Autowired
    private MetadataSchemaRegistryService metadataSchemaRegistryService;
    
    @Autowired
    private SolrConfig solrConfig;

    @RequestMapping(value = {"/","/index.html"})
    public ModelAndView index(){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("location","ALL");
        return new ModelAndView("/ERMS/All/Index",
                modelMap);
    }

    @ResponseBody
    @RequestMapping(value = "/Search")
    public Pagination<All> Search(HttpServletRequest httpServletRequest,
                                  @RequestParam(value = "title",defaultValue = "") String title,
                                  @RequestParam(value = "subject",defaultValue = "") String subject,
                                  @RequestParam(value = "channel",defaultValue = "0") Integer channel,
                                  @RequestParam(value = "category",defaultValue = "0") Integer category,
                                  @RequestParam(value = "status",defaultValue = "0") Integer status,
                                  @RequestParam(value = "page",defaultValue = "1") Integer page,
                                  @RequestParam(value = "rows",defaultValue = "10") Integer rows
                                ){
        List<Pair<String,String>> parameters = allService.GetParameters(title,subject,channel,category,status,page,rows);

        Pagination<All> pageList = null;
        for(int i=0;i<5;i++){
            try {
                pageList = allService.GetPageList(solrConfig.getUrl(),parameters);
                break;
            }catch (Exception e){

            }
        }

        if(pageList == null)
            throw new CustomException("操作失败！","Solr查询失败");

        return pageList;
    }

    @RequestMapping(value = {"/Edit/{id}","/Edit/","/Edit"})
    public ModelAndView Edit(@PathVariable(required = false) String id){
        All all = new All();
        log.info("进来没有！id:"+id);
        if(id!=null&&!id.isEmpty()){
            try {
                all = allService.GetById(id);
                if(all == null){
                    throw new CustomException("操作失败！","文献不存在或已经被删除！");
                }
            }catch (Exception e){
                throw new CustomException("操作失败！","Solr内数据有误，请联系管理员！");
            }

        }
        return new ModelAndView("/ERMS/All/Edit",
                "all",all);
    }


    @RequestMapping(value = {"/edit"})
    @ResponseBody
    public Map<String,Object> edit(HttpServletRequest httpServletRequest,
                                   @Validated @RequestBody All model,
                                   BindingResult bindingResult){
        //极大概率出错
        if(bindingResult.hasErrors()){
            throw new CustomValidationException("操作失败！",bindingResult.getFieldErrors());
        }

        boolean flag = false;
        All all = allService.GetById(model.getId());
        if(all == null){
            all = new All();
            flag = true;
        }
//        all.setOwnerCategory(categoryService.GetById(model.getCategoryId()));
//        all.setOwnerChannel(channelService.GetById(model.getChannelId()));
//        all.setClick(model.getClick());
//        all.setSort(model.getSort());
        if(model.getStatus()!=null) {
            all.setStatus(model.getStatus());
        }
        List<MetadataSchemaRegistry> list1 = metadataSchemaRegistryService.GetAll();
        List<RelationMetadataField> list = new ArrayList<>();

        if(model.getCategoryId()!=null) {
            Category byId = categoryService.GetById(model.getCategoryId());
            all.setOwnerCategory(byId);
            if (byId != null) {
                list = relationMetadataFieldService.GetList(CommonEnum.CustomMetadataFieldObject.values()[1], byId.getId(), true);
                if (list.size() == 0 && byId.getParentId()!=null && byId.getParentId() > 0) {
                    list = relationMetadataFieldService.GetList(CommonEnum.CustomMetadataFieldObject.values()[1], byId.getParentId(), true);
                }
            }
        }
        if(model.getChannelId()!=null) {
            all.setOwnerChannel(channelService.GetById(model.getChannelId()));
            if (list.size() == 0) {
                list = relationMetadataFieldService.GetList(CommonEnum.CustomMetadataFieldObject.values()[0], model.getChannelId(), true);
            }
        }
        if(list.size()>0){
            Map<String,String[]> parameters = httpServletRequest.getParameterMap();
            String metadata = allService.GetMetadataString(list,parameters);
            all.setMetadataValue(metadata);
        }

        //插入至Solr
        try {
            allService.InsertToSolr(solrConfig.getUrl(), new All[]{all}, true);

        }catch (DocumentException e){
            //logger
            throw new CustomException("操作失败！","插入Solr发生错误");
        }

        if(flag){
            allService.Insert(all);
        }else {
            allService.Update(all);
        }

        return Success("操作成功！",null);
    }

    @RequestMapping("/Operation")
    @ResponseBody
    public Map<String,Object> Operation(@RequestParam(value = "ids",defaultValue = "") String ids,
                                        @RequestParam(value = "action",defaultValue = "-1") Integer action){

        String[] array = ids.split(",");
        if(array == null || array.length==0)
            throw new CustomException("操作失败！","请选择操作项");
        if(action==0){//删除命令
            String[] array2 = array;
            for(int i=0;i<array2.length;i++){
                String id = array2[i];
                allService.Delete(id);
                allService.DeleteToSolr(solrConfig.getUrl(),id,true);
            }
        }else if(action==1 || action==2){
            String[] array3 = array;
            for(int j=0;j<array3.length;j++){
                String id2 = array3[j];
                All byId = allService.GetById(id2);
                if(byId!=null){
                    byId.setStatus((action==1)? CommonEnum.AuditStatusEnum.Pass:CommonEnum.AuditStatusEnum.NotPass);

                    try {
                        allService.InsertToSolr(solrConfig.getUrl(), new All[]{byId}, true);
                    }catch (DocumentException e){
                        throw new CustomException("操作失败！","插入Solr发生错误！");
                    }
                    //MODIFYTime自动修改
                    allService.Update(byId);
                }
            }
        }
        return Success("操作成功！",null);
    }

    /*
    description:
        参数为一个map，每一个元素包含metadata
        只要dc.Element.Qualifier
     */


    @RequestMapping(value = {"/ShowMetadataFieldForm"},produces = {MediaType.TEXT_HTML_VALUE+";charset=utf-8"})
    @ResponseBody
    public ModelAndView ShowMetadataFieldForm(HttpServletRequest httpServletRequest,
                                              @RequestParam("ChannelId") Integer ChannelId,
                                              @RequestParam(value = "CategoryId",required = false) Integer CategoryId,
                                              @RequestParam(value = "ItemId",required = false,defaultValue = "") String ItemId){

        List<MetadataSchemaRegistry> all = metadataSchemaRegistryService.GetAll();
        List<RelationMetadataField> list = new ArrayList<>();

        if(CategoryId!=null){
            Category byId = categoryService.GetById(CategoryId);
            if(byId!=null){
                list = relationMetadataFieldService.GetList(CommonEnum.CustomMetadataFieldObject.values()[1],CategoryId,true);
                if(list.size()==0 && byId.getParentId()!=null && byId.getParentId()>0){
                    list = relationMetadataFieldService.GetList(CommonEnum.CustomMetadataFieldObject.values()[1],byId.getParentId(),true);
                }
            }
        }
        if(list.size()==0 && ChannelId!=null){
            Channel byId2 = channelService.GetById(ChannelId);
            if(byId2!=null){
                list = relationMetadataFieldService.GetList(CommonEnum.CustomMetadataFieldObject.values()[0],byId2.getId(),true);
            }
        }

        //
        List<FrontMetadataField> items = new ArrayList<>();
        All ItemById =null;
        if(ItemId!=null && !ItemId.isEmpty()){
            ItemById = allService.GetById(ItemId);
        }
        for(RelationMetadataField field:list){

            FrontMetadataField meta = new FrontMetadataField(field);
            if(ItemById!=null){
                String[] values = ItemById.get_field().get(meta.getFieldName());
                if(values==null || values.length==0){
                    values = new String[]{meta.getDefaultValue()};
                }
                meta.setValues(values);
            }
            items.add(meta);
        }

        return new ModelAndView("/ERMS/All/ShowMetadataFieldForm","items",items);
    }

    @RequestMapping(value = {"/Delete/{id}"})
    @ResponseBody
    public Map<String,Object> Delete(@PathVariable String id){
        allService.DeleteToSolr(solrConfig.getUrl(),id,true);
        allService.Delete(id);
        return Success("操作成功！",null);
    }
}
