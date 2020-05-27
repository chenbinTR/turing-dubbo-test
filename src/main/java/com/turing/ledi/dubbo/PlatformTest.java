package com.turing.ledi.dubbo;

import com.turing.platform.base.util.JsonUtils;
import com.turing.platform.cloud.entity.CloudLog;
import com.turing.platform.cloud.service.OpenApiService;
import com.turing.platform.logger.api.CloudLogAcceptService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author chenbin
 * @Description:
 * @date 2018-04-18 15:10
 */
public class PlatformTest extends TestBase{
    @Test
    public void testPlatform() {
        OpenApiService openApiService = context.getBean("openApiServiceImpl", OpenApiService.class);
        long time = System.currentTimeMillis();

        String strLog = "{\"accountname\":\"weizhiwen\",\"answer\":\"北京:1月7号 周六,-3-3° 3° 小雪转雾 北风微风;1月8号 周日,-4-5° 阴 北风微风;1月9号 周一,-6-3° 晴 南风微风;1月10号 周二,-6-3° 晴 北风微风;\",\"apikey\":\"9e1fee5650ef4a9785749141f67765c7\",\"appid\":1800201,\"code\":\"100000\",\"createDate\":1483779371116,\"new\":true,\"parsetype\":3,\"question\":\"北京\",\"responsetime\":444,\"type\":0,\"userid\":69409915,\"userreqid\":\"69409915148377937068275775640-6\"}";
        CloudLog cloudLog = new CloudLog();
        cloudLog = JsonUtils.toObject(strLog, CloudLog.class);
        openApiService.invoking("9e1fee5650ef4a9785749141f67765c7", cloudLog);

    }

    @Test
    public void testLogger() {
        String strLog = "{\"accountname\":\"weizhiwen\",\"answer\":\"good morning\",\"apikey\":\"9e1fee5650ef4a9785749141f67765c7\",\"appid\":1800201,\"code\":\"100000\",\"createDate\":1483779371116,\"new\":true,\"parsetype\":3,\"question\":\"hello how\",\"responsetime\":444,\"type\":0,\"userid\":69409915,\"userreqid\":\"69409915148377937068275775640-6\"}";
        CloudLogAcceptService service = context.getBean("cloudLogAcceptService", CloudLogAcceptService.class);
        service.logger(strLog);
    }
}
