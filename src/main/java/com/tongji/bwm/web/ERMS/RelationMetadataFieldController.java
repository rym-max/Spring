package com.tongji.bwm.web.ERMS;

import com.tongji.bwm.entity.ERMS.MetadataFieldRegistry;
import com.tongji.bwm.entity.ERMS.RelationMetadataField;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.filters.validation.CustomValidationException;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.pojo.MetaFieldRegistryNameAndId;
import com.tongji.bwm.service.ERMS.*;
import com.tongji.bwm.web.Basic.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/ERMS/RelationMetadataField")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ARTICLE')")
public class RelationMetadataFieldController extends BaseController {

    @Autowired
    private RelationMetadataFieldService relationMetadataFieldService;

    @Autowired
    private MetadataFieldRegistryService metadataFieldRegistryService;

    @Autowired
    private MetadataSchemaRegistryService metadataSchemaRegistryService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/Search")
    @ResponseBody
    public List<RelationMetadataField> Search(@RequestBody FilterCondition filterCondition){
        return relationMetadataFieldService.GetList(filterCondition);
    }

    @RequestMapping("/Create")
    public ModelAndView Create(@RequestParam(value = "ChannelId",required = false) Integer ChannelId,
                               @RequestParam(value = "CategoryId",required = false) Integer CategoryId,
                               @RequestParam(value = "MetadataFieldId",required = false,defaultValue = "0") Integer MetadataFieldId){
        log.info("看一眼传入的参数");
        log.info("ChannelId==============="+ChannelId);
        log.info("CategoryId=============="+CategoryId);
        log.info("MetaFieldId============="+MetadataFieldId);


        //传回的模型
        ModelMap model = new ModelMap();

        //
        List<MetaFieldRegistryNameAndId> list = metadataFieldRegistryService.GetNameAndId();
        model.addAttribute("metafield",list);

        //创建一个元数据
        RelationMetadataField relationMetadataField = new RelationMetadataField();

        MetadataFieldRegistry byId = metadataFieldRegistryService.GetById(MetadataFieldId);
        if (byId !=null){
            relationMetadataField.setOwnerMetaFieldRegistry(metadataFieldRegistryService.GetById(byId.getId()));
            relationMetadataField.setName(byId.getName());
            relationMetadataField.setDataType(byId.getDataType());
            relationMetadataField.setOptions(byId.getOptions());
            relationMetadataField.setControlType(byId.getControlType());
            relationMetadataField.setIsRequired(byId.getIsRequired());
            relationMetadataField.setIsSearch(byId.getIsSearch());
            relationMetadataField.setSearchName(byId.getSearchName());
            relationMetadataField.setIsFullSearch(byId.getIsFullSearch());
            relationMetadataField.setIsMultiple(byId.getIsMultiple());

//            byId.setsetIsCluster(byId.setisIsCluster());
//            byId.setsetIsSort(byId.setisIsSort());

            relationMetadataField.setValidationRules(byId.getValidationRules());
            relationMetadataField.setDefaultValue(byId.getDefaultValue());

        }
        //需要设置默认值，主要是两个属性
        //relationObjectId和objectType

        //先确定channel 在确定category
        if(ChannelId!=null){
            relationMetadataField.setRelationObjectId(ChannelId);
            relationMetadataField.setObjectType(CommonEnum.CustomMetadataFieldObject.Channel);
        }else if(CategoryId !=null){
            relationMetadataField.setRelationObjectId(CategoryId);
            relationMetadataField.setObjectType(CommonEnum.CustomMetadataFieldObject.Category);
        }else{
            relationMetadataField.setRelationObjectId(-1);
            relationMetadataField.setObjectType(CommonEnum.CustomMetadataFieldObject.Others);
        }
        //加入model
        model.addAttribute("rmdf",relationMetadataField);


        return new ModelAndView("/ERMS/RelationMetadataField/Create",
               model);
    }

    @RequestMapping(value = {"/Edit/{id}","/Edit/","/Edit"})
    public ModelAndView Edit(@PathVariable("id") Integer id){
        RelationMetadataField byId = relationMetadataFieldService.GetById(id);
//        MetadataFieldRegistry byId2 = byId.getOwnerMetaFieldRegistry();
//        if(byId2!=null){
//            byId2.setOwnerMetadataSchemaRegistry(metadataSchemaRegistryService.GetById(byId2.getMetadataSchemaId()));
//        }
//        byId.setOwnerMetaFieldRegistry(byId2);
        return new ModelAndView("/ERMS/RelationMetadataField/Edit",
                "rmdf",byId);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Map<String,Object> edit(@RequestBody @Validated RelationMetadataField model,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CustomValidationException("操作失败！",bindingResult.getFieldErrors());
        }
        if(model.getId()==null){
            if(metadataFieldRegistryService.GetById(model.getMetadataFieldId())==null)
                throw new CustomException("操作失败！","元数据不存在或者已经被删除！");
            if(model.getObjectType()!= CommonEnum.CustomMetadataFieldObject.Channel && model.getObjectType()!= CommonEnum.CustomMetadataFieldObject.Category)
                throw new CustomException("操作失败！","元数据关联的对象无效！");
            if(model.getObjectType() == CommonEnum.CustomMetadataFieldObject.Channel){
                if(channelService.GetById(model.getRelationObjectId())==null)
                    throw new CustomException("操作失败！","元数据关联的栏目不存在或者已经被删除！");
            }else if(categoryService.GetById(model.getRelationObjectId())==null){
                throw new CustomException("操作失败！","元数据关联的分类不存在或者已经被删除！");
            }
            model.setOwnerMetaFieldRegistry(metadataFieldRegistryService.GetById(model.getMetadataFieldId()));
            relationMetadataFieldService.Insert(model);
        }else {
            RelationMetadataField byId = relationMetadataFieldService.GetById(model.getId());
            byId.setOwnerMetaFieldRegistry(metadataFieldRegistryService.GetById(model.getMetadataFieldId()));
            byId.setName(model.getName());
            byId.setDataType(model.getDataType());
            byId.setOptions(model.getOptions());
            byId.setControlType(model.getControlType());
            byId.setIsSearch(model.getIsSearch());
            byId.setSearchName(model.getSearchName());
            byId.setIsRequired(model.getIsRequired());
            byId.setIsFullSearch(model.getIsFullSearch());
            byId.setIsMultiple(model.getIsMultiple());
            byId.setIsCluster(model.getIsCluster());
            byId.setIsSort(model.getIsSort());
            byId.setValidationRules(model.getValidationRules());
            byId.setDefaultValue(model.getDefaultValue());
            byId.setSort(model.getSort());
            relationMetadataFieldService.Update(byId);
        }
        return Success("操作成功！",null);
    }


    @RequestMapping("/Delete/{id}")
    @ResponseBody
    public Map<String,Object> Delete(@PathVariable("id") Integer id){
        relationMetadataFieldService.Delete(id);//此处异常怎么办，exception
        return Success("操作成功！",null);
    }
}
