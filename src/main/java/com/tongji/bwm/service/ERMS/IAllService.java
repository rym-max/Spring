package com.tongji.bwm.service.ERMS;

import com.tongji.bwm.entity.ERMS.All;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.solr.Models.ClusterResult;
import javafx.util.Pair;
import org.dom4j.DocumentException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface IAllService<T> {
    T Insert(All all);

    All GetById(T id);

    void Update(All all);

    void Delete(All all);

    void Delete(T id);

    List<All> GetList(Example<All> example);

    Page<All> GetPageList(Example<All> example, Pageable pageable);

    Pagination<All> GetPageList(String serverURL, List<Pair<String,String>> parameters) throws InterruptedException, ExecutionException;

    Pagination<All> GetPageList(String serverURL, List<Pair<String,String>> parameters, Map<String,List<ClusterResult>> cluster) throws InterruptedException, ExecutionException;

    long Count(Example<All> example);

    void InsertToSolr(String serverURL, All[] items, boolean commit) throws DocumentException;

    void DeleteToSolr(String serverURL, T id, boolean commit);

    void DeleteToSolr(String serverURL, List<T> ids, boolean commit);

    void DeleteAllToSolr(String serverURL, List<T> ids, boolean commit);
    
}
