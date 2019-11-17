package com.tongji.bwm.germany.service;

import com.tongji.bwm.germany.entity.Item;
import org.dom4j.DocumentException;

/**
 * @author starcloud
 * @date 2019/09/24
 **/
public interface IItemService<T> {

    T Insert(Item item);

    void InsertToSolr(String serverURL, Item[] items, boolean commit) throws DocumentException;
}
