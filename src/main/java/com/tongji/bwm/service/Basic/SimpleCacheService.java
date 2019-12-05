package com.tongji.bwm.service.Basic;

import com.tongji.bwm.entity.ERMS.All;
import com.tongji.bwm.entity.ERMS.Region;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.pojo.SimpleItem;
import com.tongji.bwm.service.ERMS.AllService;
import com.tongji.bwm.service.ERMS.RegionService;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author starcloud
 * @date 2019/11/16
 **/
@Slf4j
@Service
public class SimpleCacheService {

    @Autowired
    private AllService allService;

    @Autowired
    private RegionService regionService;

    @CacheEvict(value = "home",allEntries = true)
    public void evictAllHome(){
        log.info("正在清除整个缓存");
    }


    @Cacheable(value = "home",key="#regionType.nameCN")
    public Map<String, SimpleItem> getHomeItem(CommonEnum.RegionType regionType){

        List<Pair<String,String>> parameters = new ArrayList<>();

        Region region= regionService.GetById(regionType.getParentid());
        parameters.add(new Pair<>("start","0"));
        parameters.add(new Pair<>("wt","json"));
        parameters.add(new Pair<>("sort","dateissued_sort desc"));
        switch (regionType){
            case All:
                parameters.add(new Pair<>("q","channel:1 AND status:1"));
                parameters.add(new Pair<>("rows","4"));
                break;
            default:
                String query = "channel:1 AND status:1 AND (region:"+regionType.getParentid();
                if(!region.getSolrQueryString().isEmpty()){
                    query += " OR (" + region.getSolrQueryString()+")";
                }
                query +=")";
                parameters.add(new Pair<>("q",query));
                parameters.add(new Pair<>("rows","4"));
                break;
//            case EuUnion:
//                parameters.add(new Pair<>("q","channel:1 AND status:1 AND region:"+regionType.getParentid()));
//                parameters.add(new Pair<>("rows","4"));
//                break;
//            case EuSubArea:
//                parameters.add(new Pair<>("q","channel:1 AND status:1 AND region:"+regionType.getParentid());
//                parameters.add(new Pair<>("rows","4"));
//                break;
//            case EuUnionMember:
//                parameters.add(new Pair<>("q","channel:1 AND status:1 AND region:"));
//                parameters.add(new Pair<>("rows","4"));
//                break;
//            case EuCountries:
//                parameters.add(new Pair<>("q","channel:1 AND status:1 AND region:1"));
//                parameters.add(new Pair<>("rows","4"));
//                break;
        }
        Pagination<All> result = new Pagination<>();
        try{
            result = allService.GetPageList(parameters);
        }catch (Exception e){
            log.error("出错了有");
        }
        log.info("看看"+result.getTotal());
        return SimpleItem.getInstance(result);
    }



    public Map<String, SimpleItem> getHomeItemNotCache(CommonEnum.RegionType regionType) {

        return getHomeItem(regionType);
    }
}
