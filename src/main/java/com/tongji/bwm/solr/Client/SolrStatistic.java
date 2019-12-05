package com.tongji.bwm.solr.Client;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javafx.util.Pair;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author starcloud
 * @date 2019/12/01
 **/
@Slf4j
@Service
public class SolrStatistic {


    @Autowired
    private SolrConnection solrConnection;

    @Setter
    @Value("${solrserver.core.tongjieu}")
    private String solrUrl;

    /**
     *注释用中文
     * date: 19/12/1 18:54
     * description:
     * @params

     * @return
    **/
    public Map<String,Object> oneFacet(String query,String field,Integer mincount, Integer limit, String sort) throws InterruptedException, ExecutionException {

        if(!sort.equals("index")&&!sort.equals("count"))
            sort="index";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("field",field);
        jsonObject.put("mincount",mincount);
        jsonObject.put("limit",limit);
        jsonObject.put("sort",sort);

        List<Pair<String,String>> parameters = new ArrayList<>();

        parameters.add(new Pair<>("q",query));
        parameters.add(new Pair<>("start","0"));
        parameters.add(new Pair<>("rows","0"));
        parameters.add(new Pair<>("wt","json"));

        parameters.add(new Pair<>("json.facet",
                "{"+field+":{terms:{"+
                "field:"+field+","+
                "mincount:"+mincount+","+
                "limit:"+limit+","+
                "sort:"+sort+
                "}}}"
        ));

        Future<String> future = solrConnection.Get(solrUrl,"/select",parameters);

        String respon = future.get();
//        log.info("响应:\n"+respon);
        JSONObject jsonObject1 = JSONObject.parseObject(respon);
        Map<String,Object> allFacets = parseJson(jsonObject1);
        if(allFacets.get(field)!=null){
            return (Map)allFacets.get(field);
        }

        return null;
    }


    private Map<String,Object> parseJson(JSONObject jsonObject){
        log.info("至少进入了！");
        Map<String,Object> result = new HashMap<>();
        JSONObject facets = jsonObject.getJSONObject("facets");
        for(String key:facets.keySet()){
            if(!key.equals("count")){
//                log.info("生成map前");
                JSONArray jsonArray = facets.getJSONObject(key).getJSONArray("buckets");
//                log.info("生成map后");
                Map<String,Object> map = new HashMap<>();
                List<String> index_li = new ArrayList<>();
                List<Integer> count_li = new ArrayList<>();
                List<JSONObject> data_li = new ArrayList<>();
                for(int i=0;i<jsonArray.size();i++){
                    JSONObject jsonobj= jsonArray.getJSONObject(i);
                    index_li.add((String) jsonobj.get("val"));
                    count_li.add((Integer) jsonobj.get("count"));
                    jsonobj.put("name",jsonobj.get("val"));
                    jsonobj.put("value",jsonobj.get("count"));
                    data_li.add(jsonobj);

                }
                map.put("index",index_li);
                map.put("count",count_li);
                map.put("data_li",data_li);
                result.put(key,map);
            }
        }

        return result;
    }


    @Cacheable(value = "visual",key = "#root.methodName")
    public String hotWord(){
        return "china";//TODO
    }
}
