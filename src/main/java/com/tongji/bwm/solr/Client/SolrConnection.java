package com.tongji.bwm.solr.Client;

import javafx.util.Pair;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;


@Component
public class SolrConnection {


    @Autowired
    private RestTemplate restTemplate;

    private String version = "2.2";//???
    /*
    * 定义异步任务
    * 访问solr
    */
    //相对底层方法
    @Deprecated
    @Async("solrTaskExecutor")
    public Future<String> GetSolr(String solrServerURL,String relativeUrl, List<Pair<String,String>> parameters){
        Future<String> getMessage;

        try{
            //使用spring自带的HTTP工具
            String querystring = GetQuery(parameters);
            UriComponents uriComponents = UriComponentsBuilder.fromUriString(solrServerURL + relativeUrl + "?" + querystring).build();
            URI uri = uriComponents.toUri();

            SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();//可以bean化？

            ClientHttpRequest chr = schr.createRequest(uri, HttpMethod.GET);

            ClientHttpResponse res = chr.execute();
            InputStream is = res.getBody();

            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line = null;
            String result = "";
            while((line=br.readLine())!=null){//读一行
                result += line;
            }

            getMessage = new AsyncResult<>(result);
        } catch (IOException e){
            //失败后返回什么信息
            getMessage = new AsyncResult<>("failed");
        }
        return getMessage;
    }




    @Async("solrTaskExecutor")
    public Future<String> Get(String solrServerURL,String relativeUrl, List<Pair<String,String>> parameters){
        Future<String> getMessage;
        //构建uri
        String querystring = GetQuery(parameters);
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(solrServerURL + relativeUrl + "?" + querystring).build();
        URI uri = uriComponents.toUri();

        //
        ResponseEntity<String> res = restTemplate.getForEntity(uri,String.class);
        String body = res.getBody();

        getMessage = new AsyncResult<>(body);

        //失败后返回什么信息,失败返回的不就是空了
        return getMessage;
    }

    @Async("solrTaskExecutor")
    public Future<String> Post(String solrServerURL,String relativeUrl, String s){

        return PostSolr(solrServerURL,relativeUrl,"text.xml",s,null);
    }


    @Async("solrTaskExecutor")
    public Future<String> PostSolr(String solrServerURL,String relativeUrl, String contentType, String content, List<Pair<String,String>> parameters){

        //先构建uri
        String querystring = GetQuery(parameters);
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(solrServerURL + relativeUrl + "?" + querystring).build();
        URI uri = uriComponents.toUri();

        HttpHeaders headers = new HttpHeaders();

        MediaType mediaType = MediaType.parseMediaType(contentType+"; charset=UTF-8");
        headers.setContentType(mediaType);
        headers.add("UserAgent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36");
        headers.add("KeepAlive", "true");

        HttpEntity<String> httpEntity = new HttpEntity<>(content,headers);

        ResponseEntity<String> responseEntity= restTemplate.postForEntity(uri,httpEntity,String.class);

        return new AsyncResult<>(responseEntity.getBody());
    }

    /*
    * 对于solr查询的参数信息整合，主要是对facet.field进行分割
     */
    public String GetQuery(List<Pair<String,String>> parameters){
        List<String> param = new ArrayList<>();
        if(parameters == null)
            return "";
        if(parameters.size()!=0){
            for(Pair<String,String> item : parameters){
                if(item.getKey().equals("facet.field")){
                    String[] array = item.getValue().split(",");
                    for(int i = 0;i<array.length;i++){
                        String value = array[i];
                        param.add(String.format("%s=%s",item.getKey(),value));
                    }
                }else {
                    param.add(String.format("%s=%s",item.getKey(),item.getValue()));
                }
            }
        }
        //java8以后的特性
//        return String.join("&",param.toArray(new String[param.size()]));
        return String.join("&",param);
    }

}
