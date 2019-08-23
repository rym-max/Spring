package com.tongji.bwm.service.ERMS;

import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.entity.ERMS.Item;
import com.tongji.bwm.solr.Client.Models.ClusterResult;
import javafx.util.Pair;
import org.dom4j.DocumentException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface IItemService<T> {

    T Insert(Item item);

    Item GetById(T id);

    void Update(Item item);

    void Delete(Item item);

    void Delete(T id);

    List<Item> GetList(Example<Item> example);

    Page<Item> GetPageList(Example<Item> example, Pageable pageable);

    Pagination<Item> GetPageList(String serverURL, List<Pair<String,String>> parameters) throws InterruptedException, ExecutionException;

    Pagination<Item> GetPageList(String serverURL, List<Pair<String,String>> parameters, Map<String,List<ClusterResult>> cluster) throws InterruptedException, ExecutionException;

    long Count(Example<Item> example);

    void InsertToSolr(String serverURL, Item[] items, boolean commit) throws DocumentException;

    void DeleteToSolr(String serverURL, T id, boolean commit);

    void DeleteToSolr(String serverURL, List<T> ids, boolean commit);

    void DeleteAllToSolr(String serverURL, List<T> ids, boolean commit);
}
