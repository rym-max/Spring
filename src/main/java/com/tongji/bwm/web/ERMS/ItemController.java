package com.tongji.bwm.web.ERMS;

import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.entity.ERMS.*;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.filters.validation.CustomValidationException;
import com.tongji.bwm.pojo.FrontMetadataField;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
//@Controller
//@RequestMapping("/ERMS/Item")
//@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ARTICLE','ROLE_EDITOR')")
public class  ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

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
        modelMap.addAttribute("location","ITEM");
        return new ModelAndView("/ERMS/Item/Index",
                modelMap);
    }

    @ResponseBody
    @RequestMapping("/Search")
    public Pagination<Item> Search(HttpServletRequest httpServletRequest,
                                   @RequestParam(value = "title",defaultValue = "") String title,
                                   @RequestParam(value = "subject",defaultValue = "") String subject,
                                   @RequestParam(value = "channel",defaultValue = "0") Integer channel,
                                   @RequestParam(value = "category",defaultValue = "0") Integer category,
                                   @RequestParam(value = "status",defaultValue = "0") Integer status,
                                   @RequestParam(value = "page",defaultValue = "1") Integer page,
                                   @RequestParam(value = "rows",defaultValue = "10") Integer rows
                                     ){
        List<Pair<String,String>> parameters = itemService.GetParameters(title,subject,channel,category,status,page,rows);

        Pagination<Item> pageList = null;
        for(int i=0;i<5;i++) {
            try {
                pageList = itemService.GetPageList(solrConfig.getUrl(), parameters);
                break;
            }catch (Exception e){
                log.info(e.toString());
            }
        }
        if(pageList == null)
                throw new CustomException("操作失败！","Solr查询失败，请重试!");
//        List<SpiderItem> pageList2 = new ArrayList<>();
//        for (SpiderItem item : pageList.getList()){
//            item.setOwnerChannel(channelService.GetById(item.getOwnerChannel().getId()));
//            if(item.getOwnerCategory().getId()!=null){
//                item.setOwnerCategory(categoryService.GetById(item.getOwnerCategory().getId()));
//            }
//            pageList2.add(item);
//        }
//        pageList.setList(pageList2);
        return pageList;
    }

    @RequestMapping(value = {"/Edit/{id}","/Edit/","/Edit"})
    public ModelAndView Edit(@PathVariable(required = false) String id){
        Item item = new Item();
        if(id!=null){
            item = itemService.GetById(id);
            if(item == null)
                throw new CustomException("操作失败！","文献不存在或已经被删除");

        }
        return new ModelAndView("/ERMS/Item/Edit",
                "item",item);
    }

    @RequestMapping(value = {"/edit"})
    @ResponseBody
    public Map<String,Object> edit(HttpServletRequest httpServletRequest,
                                   @Validated @RequestBody Item model,
                                   BindingResult bindingResult){
        //极大概率出错
        if(bindingResult.hasErrors()){
            throw new CustomValidationException("操作失败！",bindingResult.getFieldErrors());
        }

        boolean flag = false;
        Item item = itemService.GetById(model.getId());
        if(item == null){
            item = new Item();
            flag = true;
        }
//        item.setOwnerCategory(categoryService.GetById(model.getCategoryId()));
//        item.setOwnerChannel(channelService.GetById(model.getChannelId()));
//        item.setClick(model.getClick());
//        item.setSort(model.getSort());
        if(model.getStatus()!=null) {
            item.setStatus(model.getStatus());
        }
        List<MetadataSchemaRegistry> all = metadataSchemaRegistryService.GetAll();
        List<RelationMetadataField> list = new ArrayList<>();

        if(model.getCategoryId()!=null) {
            Category byId = categoryService.GetById(model.getCategoryId());
            item.setOwnerCategory(byId);
            if (byId != null) {
                list = relationMetadataFieldService.GetList(CommonEnum.CustomMetadataFieldObject.values()[1], byId.getId(), true);
                if (list.size() == 0 && byId.getParentId()!=null && byId.getParentId() > 0) {
                    list = relationMetadataFieldService.GetList(CommonEnum.CustomMetadataFieldObject.values()[1], byId.getParentId(), true);
                }
            }
        }
        if(model.getChannelId()!=null) {
            item.setOwnerChannel(channelService.GetById(model.getChannelId()));
            if (list.size() == 0) {
                list = relationMetadataFieldService.GetList(CommonEnum.CustomMetadataFieldObject.values()[0], model.getChannelId(), true);
            }
        }
        if(list.size()>0){
            Map<String,String[]> parameters = httpServletRequest.getParameterMap();
            String metadata = itemService.GetMetadataString(list,parameters);
            item.setMetadataValue(metadata);
        }

        //插入至Solr
        try {
            itemService.InsertToSolr(solrConfig.getUrl(), new Item[]{item}, true);

        }catch (DocumentException e){
            //logger
            throw new CustomException("操作失败！","插入Solr发生错误");
        }

        if(flag){
            itemService.Insert(item);
        }else {
            itemService.Update(item);
        }

        return Success("操作成功！",null);
    }

    @RequestMapping("/Operation")
    @ResponseBody
    public Map<String,Object> Operation(@RequestParam(value = "ids",defaultValue = "") String ids,
                                        @RequestParam(value = "action",defaultValue = "-1") Integer action){

        String[] array = ids.split(",");
        log.info("操作的id列表："+ids);
        if(array.length==0)
            throw new CustomException("操作失败！","请选择操作项");
        if(action.equals(0)){//删除命令
            String[] array2 = array;
            for(int i=0;i<array2.length;i++){
                String id = array2[i];
                itemService.Delete(id);
                itemService.DeleteToSolr(solrConfig.getUrl(),id,true);
            }
        }else if(action.equals(1) || action.equals(2)){
            String[] array3 = array;
            for(int j=0;j<array3.length;j++){
                String id2 = array3[j];
                Item byId = itemService.GetById(id2);
                if(byId!=null){
                    byId.setStatus((action==1)? CommonEnum.AuditStatusEnum.Pass:CommonEnum.AuditStatusEnum.NotPass);

                    try {
                        itemService.InsertToSolr(solrConfig.getUrl(), new Item[]{byId}, true);
                    }catch (DocumentException e){
                        throw new CustomException("操作失败！","插入Solr发生错误！");
                    }
                    //MODIFYTime自动修改
                    itemService.Update(byId);
                }
            }
        }else {
            throw new CustomException("操作失败！","请输入正确的操作指令！");
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
        Item ItemById =null;
        if(ItemId!=null && !ItemId.isEmpty()){
            ItemById = itemService.GetById(ItemId);
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

        return new ModelAndView("/ERMS/Item/ShowMetadataFieldForm","items",items);
    }


}
