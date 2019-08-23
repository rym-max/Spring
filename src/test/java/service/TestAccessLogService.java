package service;

import com.tongji.bwm.Application;
import com.tongji.bwm.repository.Log.AccessLogRepository;
import com.tongji.bwm.service.Log.AccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

@SpringBootTest(classes ={Application.class})
public class TestAccessLogService extends AbstractTestNGSpringContextTests{


    @Autowired
    private AccessLogService accessLogService;
    @Autowired
    private AccessLogRepository accessLogRepository;

    @Test
    public void getIpCount() throws Throwable{
        assertEquals(accessLogRepository.GetIpCount(2019,2).size(),0);
    }

    @Test
    public void getDayByMonth() throws Throwable{
        assertEquals(accessLogRepository.GetEachDayAccessByMonth(2019,2).size(),0);
    }

}
