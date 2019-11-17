package com.tongji.bwm.service.Basic;

import com.tongji.bwm.entity.ERMS.All;
import com.tongji.bwm.pojo.Enum.CommonEnum;
import com.tongji.bwm.pojo.Pagination;
import com.tongji.bwm.pojo.SimpleItem;
import com.tongji.bwm.service.ERMS.AllService;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author starcloud
 * @date 2019/11/16
 **/
@Slf4j
@Service
public class SimpleCacheService {

    @Autowired
    private AllService allService;

    @Cacheable(value = "home",key="#regionType.nameCN")
    public Pagination<SimpleItem> getHomeItem(CommonEnum.RegionType regionType){

        List<Pair<String,String>> parameters = new ArrayList<>();
        parameters.add(new Pair<>("start","0"));
        parameters.add(new Pair<>("wt","json"));
        parameters.add(new Pair<>("sort","dateissued_sort desc"));
        switch (regionType){
            case All:
                parameters.add(new Pair<>("q","channel:1 AND status:1"));
                parameters.add(new Pair<>("rows","4"));
                break;
            default:
                parameters.add(new Pair<>("q","channel:1 AND status:1 AND region:"+regionType.getParentid()));
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
        return SimpleItem.getInstance(result);
    }
}
