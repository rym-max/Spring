package com.tongji.bwm.utils;

import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FilterEntityUtils {

    public static FilterCondition getPageRowCondition(int page,int rows){
        FilterCondition filterCondition = new FilterCondition();
        filterCondition.page = page;
        filterCondition.rows = rows;
        return filterCondition;
    }


    public static  <T> T getOneExample(T entity,FilterCondition filterCondition){
        Class clazz = entity.getClass();

        if(filterCondition.filter==null || filterCondition.filter.size()==0)
            return entity;
//        Method[] methods = clazz.getMethods();
        for(com.tongji.bwm.pojo.FilterCondition.Field field:filterCondition.filter){
            try{

                //先获取属性类型
                //默认属性首字母小写
                String fieldName = CamelCaseUtils.LowerFirstLetter(field.name);
                Field field1 = clazz.getDeclaredField(fieldName);
                String fieldClazz = field1.getGenericType().toString();
                //只判断整型和字符型
                if(field1.getType()==Integer.class){
                    //获取方法
                    Method method = clazz.getDeclaredMethod("set"+field.name,Integer.class);
                    method.invoke(entity,Integer.parseInt(field.value));
                }
//                else if(fieldClazz.equals("class java.lang.String"))
                else if(field1.getType()==String.class)
                {
                    //获取方法
                    Method method = clazz.getDeclaredMethod("set"+field.name,String.class);
                    method.invoke(entity,field.value);
                }else{
                    //跳过这个属性
                    continue;
                }


            }catch (Exception e){
                //啥事也不做
            }
        }
        return entity;
    }


    public static Sort getSort(FilterCondition filterCondition){

        List<Sort.Order> orders = new ArrayList<>();
        if(filterCondition.sort==null||filterCondition.sort.size()==0)
            return Sort.unsorted();
        for(com.tongji.bwm.pojo.FilterCondition.Sort sort:filterCondition.sort){
            if(sort.asc) {
                orders.add(new Sort.Order(Sort.Direction.ASC, sort.name));
            }else {
                orders.add(new Sort.Order(Sort.Direction.DESC, sort.name));
            }
        }

        return Sort.by(orders);
    }

    public static Pageable getPageable(FilterCondition filterCondition){

        //page & rows
        int page = filterCondition.page;
        int rows = filterCondition.rows;
        //Sort
        Sort sort = getSort(filterCondition);
        return PageRequest.of(page,rows,sort);
    }

    public static <T> Pagination<T> getPagination(Page<T> tPage){
        Pagination pagination = new Pagination(
                tPage.getContent(),
                new Long(tPage.getTotalElements()).intValue(),
                tPage.getNumber(),
                tPage.getSize()
        );
        return pagination;
    }
}
