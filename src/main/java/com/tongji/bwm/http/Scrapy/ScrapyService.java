package com.tongji.bwm.http.Scrapy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongji.bwm.entity.Spider.SpiderConfig;
import com.tongji.bwm.entity.Spider.SpiderItem;
import com.tongji.bwm.pojo.Enum.Scrapy.ScrapyEnum;
import com.tongji.bwm.service.Spider.SpiderItemService;
import com.tongji.bwm.utils.DateFormatterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;

@Slf4j
@Service
public class ScrapyService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SpiderItemService spiderItemService;

    @Value("${scrapyserver.url}")
    private String scrapyserver;


    //此处只需要定义post命令
    public ResponseEntity<String> Post(String spiderServerURL, String relativeUrl, MultiValueMap<String,String> parameters){

        //构建uri
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(spiderServerURL+relativeUrl).build();
        URI uri = uriComponents.toUri();

        //header
        HttpHeaders headers = new HttpHeaders();

        //request
        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(parameters,headers);

        ResponseEntity<String> responseEntity =restTemplate.postForEntity(uri,request,String.class);

        return responseEntity;
    }

    public boolean Start(SpiderItem spiderItem, String actionUser, String ip){


        /* 判断状态
        是否finish？否，报错；换种判断
         */
        if(!IsFinish(spiderItem.getStatus(), spiderItem.getId(),actionUser))
            return false;

        /*
        1.提取Config Id
        2.提取CustomSettings
         */
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();

        //SpiderConfig
        SpiderConfig spiderConfig = spiderItem.getOwnerSpiderConfig();
        if(spiderConfig ==null){
            BanSpiderProcess(spiderItem.getId(),"'spider not have a spiderConfig'");
            return false;
        }

        //先设置Item 状态
        spiderItem.setStatus(ScrapyEnum.StatusEnum.OPENING);
        spiderItemService.Update(spiderItem);

        //日期和spider name
        map.add("project", spiderConfig.getProject());
        map.add("spider", spiderConfig.getSpider());
        map.add("jobid", spiderItem.getName() + "_" + DateFormatterUtils.GetDateStr(new Date()));

        //部分参数需要单独设置
        //user&ip
        map.add("user",actionUser);
        map.add("ip",ip);

        //custom_settings
        //分两部分
        //1.settings需要写成settings "key=value"形式
        //2.CUSTOM_CONFIG需要整个字符串输入

        String settings = spiderItem.getCustomSettings();

        //解析字符串
        if(settings!=null && !settings.isEmpty()) {
            JSONObject jsonObject = JSON.parseObject(settings);

            String custom_config = jsonObject.getString("CUSTOM_CONFIG");
            if(custom_config!=null&&!custom_config.isEmpty()){
                map.add("CUSTOM_CONFIG",custom_config);
            }

            JSONObject custom_setting = jsonObject.getJSONObject("CUSTOM_SETTING");
            if(custom_setting!=null){
                custom_setting.entrySet().forEach(
                        s->{
                            if(s.getValue()!=null && !((String)s.getValue()).isEmpty())
                                map.add("setting",s.getKey()+"="+s.getValue());
                        }
                );
            }
        }

        ResponseEntity<String> response = Post(scrapyserver,"/schedule.json",map);

        String result = response.getBody();
        log.info("响应："+result);
        /*解析response

         */
        if(response.getStatusCode()== HttpStatus.OK){
//            String jobId = GetJobId(response.getBody());
            String jobId = map.getFirst("jobid");
            spiderItem.setLastJob(jobId);
            spiderItem.setStatus(ScrapyEnum.StatusEnum.SUSPENDING);
            return true;
        }else{
            BanSpiderProcess(spiderItem.getId(),"'spider not start successfully'");
            return false;
        }
    }

    public boolean Stop(String jobId){
        return false;
    }

    public boolean ForceStop(String job){
        return false;
    }

    private String GetJobId(String response){

        return "";
    }

    private boolean IsFinish(ScrapyEnum.StatusEnum status, Integer itemId, String actionUser){
        if(status== ScrapyEnum.StatusEnum.FINISHED)
            return true;
        if(actionUser.equals("AUTO"))
            BanSpiderProcess(itemId,"'spider not finished in one whole interval'");
        return false;
    }

    private void BanSpiderProcess(Integer itemId, String reason){
        //错误处理
        SpiderItem errorSpiderItem = spiderItemService.GetById(itemId);
        errorSpiderItem.setStatus(ScrapyEnum.StatusEnum.ERROR);

        errorSpiderItem.setLastAction(ScrapyEnum.ActionEnum.ERROR);
        errorSpiderItem.setLastJob("null");
        errorSpiderItem.setLastActionTime(new Date());
        errorSpiderItem.setLastActionUser("AUTO");
        errorSpiderItem.setLastResult("{'status':'error'," +
                "'result':'ban the spider'," +
                "'reason':" +
                reason+
                "}");

        spiderItemService.Update(errorSpiderItem);
    }
}
