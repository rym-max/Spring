package service;

import com.tongji.bwm.entity.ERMS.RelationMetadataField;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.utils.CamelCaseUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnlyForTest {

    public static void main(String[] args){

        String[] s = new String[]{"WODE","NIDE"};
        List<String> l = new ArrayList<>(Arrays.asList(s));
        l.add("我再加个呢");
        for(String p:l){
            System.out.println(p);
        }

    }

    public static void Test1(){
        FilterCondition filterCondition = new FilterCondition();
        List<com.tongji.bwm.pojo.FilterCondition.Field> list1 = new ArrayList<>();
        list1.add(new com.tongji.bwm.pojo.FilterCondition.Field("RelationObjectId","1"));
        list1.add(new com.tongji.bwm.pojo.FilterCondition.Field("ObjectTypeId","1"));
        filterCondition.filter = list1;

        RelationMetadataField relationMetadataField1 = getOneExample(new RelationMetadataField(),filterCondition);

        System.out.println("看眼结果\r\n"+relationMetadataField1.toString());
    }


    public static  <T> T getOneExample(T entity, FilterCondition filterCondition){
        Class clazz = entity.getClass();
        System.out.println("先看眼类型"+clazz.toString());
        if(filterCondition.filter==null || filterCondition.filter.size()==0)
            return entity;
//        Method[] methods = clazz.getMethods();
        int i=0;
        for(com.tongji.bwm.pojo.FilterCondition.Field field:filterCondition.filter){
            System.out.println("循环第"+ (i++) +"次："+"[" + field.name +"]");
            try{
                //获取方法


                //先获取属性类型
                //默认属性首字母小写
                String fieldName = CamelCaseUtils.LowerFirstLetter(field.name);
                Field field1 = clazz.getDeclaredField(fieldName);
                System.out.println("最后看眼属性"+field1.toString());
                String fieldClazz = field1.getGenericType().toString();
                System.out.println("属性类型名：1    "+fieldClazz);
                //只判断整型和字符型
                if(fieldClazz.equals("class java.lang.Integer")){
                    Method method = clazz.getDeclaredMethod("set"+field.name,Integer.class);
                    System.out.println("再看眼方法"+method.toString());
                    method.invoke(entity,Integer.parseInt(field.value));
                }else if(fieldClazz.equals("class java.lang.String")){
                    Method method = clazz.getDeclaredMethod("set"+field.name,String.class);
                    System.out.println("再看眼方法"+method.toString());
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
}
