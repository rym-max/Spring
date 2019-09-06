package com.tongji.bwm.service.ERMS;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tongji.bwm.entity.ERMS.All;
import com.tongji.bwm.entity.ERMS.RelationMetadataField;
import com.tongji.bwm.filters.CustomException;
import com.tongji.bwm.pojo.FilterCondition.FilterCondition;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.repository.ERMS.AllRepository;
import com.tongji.bwm.solr.Client.SolrConnection;
import com.tongji.bwm.solr.Models.ClusterResult;
import com.tongji.bwm.solr.Models.SearchResult;
import com.tongji.bwm.utils.DateFormatterUtils;
import com.tongji.bwm.utils.FilterEntityUtils;
import javafx.util.Pair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class AllService implements IAllService<String> {

    @Autowired
    private AllRepository allRepository;

    @Autowired
    private SolrConnection solrConnection;

    @Override
    public String Insert(All all) {
        return allRepository.save(all).getId();
    }

    @Override
    public All GetById(String id) {
        Optional<All> optional = allRepository.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public void Update(All all) {
        allRepository.save(all);
    }

    @Override
    public void Delete(All all) {
        allRepository.delete(all);
    }

    @Override
    public void Delete(String id) {
        allRepository.deleteById(id);
    }

    @Override
    public List<All> GetList(Example<All> example) {
        return allRepository.findAll(example);
    }

    @Override
    public Page<All> GetPageList(Example<All> example, Pageable pageable) {
        return allRepository.findAll(example, pageable);
    }

    public Pagination<All> GetPageList(FilterCondition filterCondition){
        //定义pageable
        Pageable pageable = FilterEntityUtils.getPageable(filterCondition);
        Page<All> page = allRepository.findAll(pageable);
        return FilterEntityUtils.getPagination(page);
    }

    @Override
    public long Count(Example<All> example) {
        if(example==null)
            return allRepository.count();
        return allRepository.count(example);
    }

    @Override
    public void InsertToSolr(String serverURL, All[] items, boolean commit) throws DocumentException {
        StringBuffer xmlStringB = new StringBuffer();
        for(int i=0;i<items.length;i++){
            All all = items[i];
            Document document = DocumentHelper.parseText(all.getMetadataValue());
            xmlStringB.append("<doc>")
                    .append("<field name=\"id\">" + all.getId() + "</field>")
                    .append("<field name=\"channel\">" + all.getOwnerChannel().getId() + "</field>")
                    .append("<field name=\"category\">" + all.getOwnerCategory().getId() + "</field>")
                    .append("<field name=\"click\">" + all.getClick() + "</field>")
                    .append("<field name=\"sort\">" + all.getSort() + "</field>")
                    .append("<field name=\"status\">" + all.getStatus() + "</field>")
                    .append("<field name=\"ctime\">" + all.getCreateTime().getTime() + "</field>")
                    .append("<field name=\"mtime\">" + all.getModifyTime().getTime() + "</field>")
                    .append(document.getRootElement().asXML())
                    .append("</doc>");
        }
        String xmlString =  "<add commitWithin=\"1000\" overwrite=\"true\">" +
                xmlStringB.toString() +
                (commit ? "<commit></commit>" : "") +
                "</add>";
        solrConnection.Post(serverURL,"/update",xmlString);
    }

    @Override
    public void DeleteToSolr(String serverURL, String id, boolean commit) {
        StringBuffer parametersB = new StringBuffer();
        parametersB.append("<add commitWithin=\"1000\" overwrite=\"true\"><delete><query>")
                .append("id:"+id)
                .append("</query></delete>" + (commit ? "<commit></commit>" : "") + "</add>");
        solrConnection.Post(serverURL,"/update", parametersB.toString());
    }

    @Override
    public void DeleteToSolr(String serverURL, List<String> ids, boolean commit) {
        StringBuffer parameterB = new StringBuffer();
        parameterB.append("<add commitWithin=\"1000\" overwrite=\"true\"><delete><query>");
        int i = 0;
        for(String id :ids){
            if(i>0)
                parameterB.append("OR");
            parameterB.append("id:"+id);
            i++;
        }
        parameterB.append("</query></delete>" + (commit ? "<commit></commit>" : "") + "</add>");
        solrConnection.Post(serverURL,"/update",parameterB.toString());
    }

    @Override
    public void DeleteAllToSolr(String serverURL, List<String> ids, boolean commit) {
        String parameters = "<add commitWithin=\"1000\" overwrite=\"true\"><delete><query></query></delete>" + (commit ? "<commit></commit>" : "") + "</add>";
        solrConnection.Post(serverURL,"/update",parameters);
    }

    @Override
    public Pagination<All> GetPageList(String serverURL, List<Pair<String, String>> parameters) throws InterruptedException, ExecutionException {
//        Future<String> future = solrConnection.Get(serverURL,"/select",parameters);
////        JSONObject object = JSONObject.parseObject(future.get());
//        SearchResult<String> searchResult = JSON.parseObject(future.get(), SearchResult.class);
//
//        List<All> items = new ArrayList<>();
//        for(Document<String> doc:searchResult.getDocs()){
//            All all = GetById(doc.getId());
//            if(all!=null){
//                items.add(all);
//            }
//        }
//        for(Pair<String,String> param: parameters) {
//            if (param.getKey().equals("start"))
//                searchResult.setStart(Integer.parseInt(param.getValue()));
//            if (param.getKey().equals("row"))
//                searchResult.setStart(Integer.parseInt(param.getValue()));
//        }
//
//        int page = (searchResult.getStart() +searchResult.getRows())/searchResult.getRows();
//        return new Pagination<All>(items,searchResult.getNumFound(),page,searchResult.getRows());
        return GetPageList(serverURL,parameters,null);
    }

    public SearchResult<String> GetSearchResult(String str){
        //注意相关参数分别在不同位置
        JSONObject jsonObject = JSONObject.parseObject(str);
        SearchResult<String> searchResult = JSON.parseObject(jsonObject.getJSONObject("response").toJSONString(), SearchResult.class);

        //header
        JSONObject resHeader = jsonObject.getJSONObject("responseHeader");
        if(resHeader==null) {
            //问题是有的
            searchResult.setRows(10);//防止除以0
            return searchResult;
        }
        searchResult.setStatus(resHeader.getInteger("status"));
        searchResult.setQTime(resHeader.getInteger("QTime"));

        //params
        JSONObject params = jsonObject.getJSONObject("params");
        if(params==null){
            searchResult.setRows(10);
            return searchResult;
        }
        searchResult.setStart(params.getInteger("start"));
        searchResult.setRows(params.getInteger("rows"));

        return searchResult;
    }

    @Override
    public Pagination<All> GetPageList(String serverURL, List<Pair<String, String>> parameters, Map<String, List<ClusterResult>> cluster) throws InterruptedException,ExecutionException{
        Future<String> future = solrConnection.Get(serverURL,"/select",parameters);
        String parseStr = future.get();
        JSONObject jsonObject = JSONObject.parseObject(parseStr);
        SearchResult<String> searchResult = GetSearchResult(parseStr);

        List<All> items = new ArrayList<>();
        for(com.tongji.bwm.solr.Models.Document<String> doc:searchResult.getDocs()){
            All all = GetById(doc.getId());
            if(all!=null){
                items.add(all);
            }
        }


        for(Pair<String,String> param: parameters){
            if(param.getKey().equals("start"))
                searchResult.setStart(Integer.parseInt(param.getValue()));
            if(param.getKey().equals("rows"))
                searchResult.setStart(Integer.parseInt(param.getValue()));
            if(cluster!=null && param.getKey().equals("facet.field")) {
                Map<String, HashMap<String,Integer>> clusterArray = new HashMap<>();
                String[] array2 = param.getValue().split(",");
                for(int j=0;j<array2.length;j++){
                    String value = array2[j];

                    List<ClusterResult> clusterResultList = new ArrayList<>();
                    JSONArray jsonArray = jsonObject.getJSONObject("facet_counts").getJSONObject("facet_field").getJSONArray(value);

                    for(int i =0;i<jsonArray.size();i++){
                        clusterResultList.add(
                                new ClusterResult(
                                        (String)jsonArray.get(i),
                                        (Integer)jsonArray.get(++i)
                                )
                        );
                    }
                    cluster.put(value,clusterResultList);//应该是引用 会改变值
                }

            }
        }

        int page = (searchResult.getStart() * searchResult.getRows() + searchResult.getRows())/searchResult.getRows();
        return new Pagination<All>(items,searchResult.getNumFound(),page,searchResult.getRows());
    }

    public List<Pair<String,String>> GetParameters(String title,
                                                   String subject,
                                                   Integer channel,
                                                   Integer category,
                                                   Integer status,
                                                   Integer page,
                                                   Integer rows){
        StringBuffer sbf = new StringBuffer();
        if(!title.trim().isEmpty()){
            title = title.trim().replaceAll(" ","\\ ");
            sbf.append(sbf.length()==0 ? "" : " AND ");
            sbf.append("(title:")
                    .append(title)
                    .append(" OR title_ss:*")
                    .append(title)
                    .append("*)");
        }

        if(!subject.trim().isEmpty()){
            subject = subject.trim().replaceAll(" ","\\ ");
            sbf.append(sbf.length()==0 ? "" : " AND ");
            sbf.append("(subject:")
                    .append(subject)
                    .append(")");
        }

        if(status>0){
            sbf.append(sbf.length()==0 ? "" : " AND ");
            sbf.append("(status:")
                    .append(status)
                    .append(")");
        }

        if(channel>0){
            sbf.append(sbf.length()==0 ? "" : " AND ");
            sbf.append("(channel:")
                    .append(channel)
                    .append(")");
        }

        if(category>0){
            sbf.append(sbf.length()==0 ? "" : " AND ");
            sbf.append("(category:")
                    .append(category)
                    .append(")");
        }

        sbf.append(sbf.length()!=0 ? "" : "*:*");

        List<Pair<String,String>> parameters = new ArrayList<>();
        parameters.add(new Pair<>("q",sbf.toString()));
        parameters.add(new Pair<>("start",String.valueOf((page-1) *rows)));
        parameters.add(new Pair<>("rows",String.valueOf(rows)));
        parameters.add(new Pair<>("sort","mtime desc,ctime desc"));
        parameters.add(new Pair<>("wt","json"));

        return parameters;
    }

    public String GetMetadataString(List<RelationMetadataField> list, Map<String,String[]> parameters) throws CustomException {

        StringBuffer sbf = new StringBuffer();
        sbf.append("<doc>\r\n");
        for(RelationMetadataField field:list){
            //判断schema&qualifier
            String fieldName = field.getOwnerMetaFieldRegistry().getOwnerMetadataSchemaRegistry().getCode() + "." + field.getOwnerMetaFieldRegistry().getElement();
            String qualifier = field.getOwnerMetaFieldRegistry().getQualifier();
            if(qualifier!=null && !qualifier.isEmpty())
                fieldName += "."+qualifier;
            //判断是否有这个key
            if(parameters.containsKey(fieldName)){
                //是否多值，否组合为一个字符串
                String[] attributes = parameters.get(fieldName);
                if(!field.getIsMultiple()){
                    String oneAttribute = "";
                    for(int i=0;i<parameters.get(fieldName).length;i++){
                        oneAttribute += attributes[i];
                    }
                    attributes = new String[] {oneAttribute};
                }


                for(int i=0;i<attributes.length;i++){
                    //先加原始
                    String oneText = attributes[i];
                    if(!oneText.isEmpty()){
                        sbf.append("<field name=\"")
                                .append(fieldName)
                                .append("\"><![CDATA[")
                                .append(oneText)
                                .append("]]></field>\r\n");

                        //IsSearch
                        if(field.getIsSearch()){
                            String searchText;
                            if(field.getSearchName()==null||field.getSearchName().isEmpty()){
                                searchText=fieldName;
                            }else {
                                searchText=field.getSearchName();
                            }
                            sbf.append("<field name=\"")
                                    .append(searchText)
                                    .append("\"><![CDATA[")
                                    .append(oneText)
                                    .append("]]></field>\r\n");
                            sbf.append("<field name=\"")
                                    .append(searchText)
                                    .append("_ss\"><![CDATA[")
                                    .append(oneText)
                                    .append("]]></field>\r\n");
                            //判断是否是日期
                            if(field.getOwnerMetaFieldRegistry().getElement().equals("date")){
                                String year = DateFormatterUtils.GetYear(oneText);
                                if(!year.isEmpty()){
                                    sbf.append("<field name=\"")
                                            .append(searchText)
                                            .append("_year\"><![CDATA[")
                                            .append(year)
                                            .append("]]></field>\r\n");
                                }
                            }
                            //Cluster
                            if(field.getIsCluster()){
                                //判断是否是日期
                                if(field.getOwnerMetaFieldRegistry().getElement().equals("date")){
                                    String year = DateFormatterUtils.GetYear(oneText);
                                    if(!year.isEmpty()){
                                        sbf.append("<field name=\"")
                                                .append(searchText)
                                                .append("_filter\"><![CDATA[")
                                                .append(year)
                                                .append("]]></field>\r\n");
                                    }
                                }else {
                                    sbf.append("<field name=\"")
                                            .append(searchText)
                                            .append("_filter\"><![CDATA[")
                                            .append(oneText)
                                            .append("]]></field>\r\n");
                                }
                            }
                            //IsSort
                            if(field.getIsSort()){
                                sbf.append("<field name=\"")
                                        .append(searchText)
                                        .append("_sort\"><![CDATA[")
                                        .append(oneText)
                                        .append("]]></field>\r\n");
                            }
                        }


                        //

                    }
                }



            }else if(field.getIsRequired()){//是否必须
                throw new CustomException("操作失败",field.getName()+"为必填项！");
            }
        }
        //合并
        sbf.append("</doc>");
        return sbf.toString();
    }
}
