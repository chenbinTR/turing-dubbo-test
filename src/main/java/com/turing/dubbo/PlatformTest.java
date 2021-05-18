package com.turing.dubbo;

import com.turing.platform.base.util.JsonUtils;
import com.turing.platform.cloud.entity.CloudLog;
import com.turing.platform.cloud.service.OpenApiService;
import com.turing.platform.logger.api.CloudLogAcceptService;
import com.turing.protocal.data.DataRequest;
import com.turing.protocal.data.DataResult;
import com.turing.service.data.DataService;
import com.turing.service.faq.api.FaqService;
import com.turing.service.faq.model.FaqRequest;
import org.junit.Test;

/**
 * @author chenbin
 * @Description:
 * @date 2018-04-18 15:10
 */
public class PlatformTest extends BaseTest {
    @Test
    public void testPlatform() {
        OpenApiService openApiService = context.getBean("openApiServiceImpl", OpenApiService.class);
        long time = System.currentTimeMillis();

        String strLog = "{\"accountname\":\"weizhiwen\",\"answer\":\"北京:1月7号 周六,-3-3° 3° 小雪转雾 北风微风;1月8号 周日,-4-5° 阴 北风微风;1月9号 周一,-6-3° 晴 南风微风;1月10号 周二,-6-3° 晴 北风微风;\",\"apikey\":\"9e1fee5650ef4a9785749141f67765c7\",\"appid\":1800201,\"code\":\"100000\",\"createDate\":1483779371116,\"new\":true,\"parsetype\":3,\"question\":\"北京\",\"responsetime\":444,\"type\":0,\"userid\":69409915,\"userreqid\":\"69409915148377937068275775640-6\"}";
        CloudLog cloudLog = JsonUtils.toObject(strLog, CloudLog.class);
        openApiService.invoking("9e1fee5650ef4a9785749141f67765c7", cloudLog);

    }

    @Test
    public void testLogger() {
        String strLog = "{\"accountname\":\"weizhiwen\",\"answer\":\"good morning\",\"apikey\":\"9e1fee5650ef4a9785749141f67765c7\",\"appid\":1800201,\"code\":\"100000\",\"createDate\":1483779371116,\"new\":true,\"parsetype\":3,\"question\":\"hello how\",\"responsetime\":444,\"type\":0,\"userid\":69409915,\"userreqid\":\"69409915148377937068275775640-6\"}";
        CloudLogAcceptService service = context.getBean("cloudLogAcceptService", CloudLogAcceptService.class);
        service.logger(strLog);
    }

    @Test
    public void testData() {
        DataService dataService = context.getBean("dataService", DataService.class);
//        String reqStr = "{\"ret_count\":1,\"user_reqid\":\"0010496423146306188779400798632-6\",\"parse_result\":[{\"app_id\":1800201,\"app_name\":\"REALTIMEINFO.WEATHER.DOMESTIC\",\"match_score\":100,\"parse_type\":1,\"negation\":0,\"auto_learn\":0,\"text\":\"\",\"semantic\":{\"city\":\"北京\",\"county\":\"\",\"date\":\"\"}}],\"user_info\":{\"userid\":\"0010496423\"},\"location_info\":{\"province\":\"北京\",\"city\":\"北京\",\"address\":\"信息路\",\"nearest_poi_name\":\"上地环岛南\",\"user_latitude\":\"39.45492\",\"user_longitude\":\"119.239293\"},\"suspect_info\":{},\"assist_version\":33}";
//        String reqStr = "{\"ret_count\":1,\"user_reqid\":\"0010496423146306188779400798632-6\",\"parse_result\":[{\"app_id\":1600401,\"app_name\":\"REALTIMEINFO.WEATHER.DOMESTIC\",\"match_score\":100,\"parse_type\":1,\"negation\":0,\"auto_learn\":0,\"text\":\"\",\"semantic\":{\"chat_content\":\"what's the date today\",\"county\":\"\",\"date\":\"\"}}],\"user_info\":{\"userid\":\"0010496423\"},\"location_info\":{},\"assist_version\":33}";
//        String reqStr = "{\"ret_count\":1,\"user_reqid\":\"0010496423146346844110782587885-6\",\"parse_result\":[{\"app_id\":2000101,\"text\":\"\",\"app_name\":\"TIME.TIMECITY.NOW\",\"semantic\":{\"city\":\"北京\"}}],\"user_info\":{\"userid\":\"0010496423\"},\"location_info\":{\"province\":\"北京\",\"city\":\"北京\",\"address\":\"信息路\",\"nearest_poi_name\":\"上地环岛南\",\"user_latitude\":\"39.45492\",\"user_longitude\":\"119.239293\"},\"suspect_info\":{},\"assist_version\":33}";
//        String reqStr = "{\"ret_count\":1,\"user_reqid\":\"0010496423146676348324845728278-6\",\"parse_result\":[{\"app_id\":1100301,\"text\":\"\",\"app_name\":\"EDU.POETRY.SEARCH\",\"semantic\":{\"peotry_author\":\"\",\"peotry_name\":\"\",\"peotry_type\":\"诗\",\"peotry_years\":\"\"}}],\"user_info\":{\"userid\":\"0010496423\"},\"location_info\":{\"province\":\"北京\",\"city\":\"北京\",\"address\":\"信息路\",\"nearest_poi_name\":\"上地环岛南\",\"user_latitude\":\"39.45492\",\"user_longitude\":\"119.239293\"},\"suspect_info\":{},\"assist_version\":33}";
//        String reqStr = "{\"ret_count\":1,\"user_reqid\":\"0010496423146676359859267649577-6\",\"parse_result\":[{\"app_id\":2222,\"text\":\"\",\"app_name\":\"EDU.POETRY.SEARCH\",\"semantic\":{\"peotry_author\":\"\",\"peotry_name\":\"000\",\"peotry_type\":\"\",\"peotry_years\":\"\"}}],\"user_info\":{\"userid\":\"0010496423\"},\"location_info\":{\"province\":\"北京\",\"city\":\"北京\",\"address\":\"信息路\",\"nearest_poi_name\":\"上地环岛南\",\"user_latitude\":\"39.45492\",\"user_longitude\":\"119.239293\"},\"suspect_info\":{},\"assist_version\":33}";
//        String reqStr = "{\"ret_count\":1,\"user_reqid\":\"0010496423146676414989690836055-6\",\"parse_result\":[{\"app_id\":1100301,\"text\":\"\",\"app_name\":\"EDU.POETRY.SEARCH\",\"semantic\":{\"peotry_author\":\"苏轼\",\"peotry_name\":\"赤壁怀古\",\"peotry_type\":\"\",\"peotry_years\":\"\"}}],\"user_info\":{\"userid\":\"0010496423\"},\"location_info\":{\"province\":\"北京\",\"city\":\"北京\",\"address\":\"信息路\",\"nearest_poi_name\":\"上地环岛南\",\"user_latitude\":\"39.45492\",\"user_longitude\":\"119.239293\"},\"suspect_info\":{},\"assist_version\":33}";
//        String reqStr = "{\"user_info\":{\"userid\":\"124653\"},\"ret_count\":1,\"assist_version\":0,\"location_info\":{\"address\":\"\",\"province\":\"\",\"user_latitude\":\"\",\"user_longitude\":\"\",\"nearest_poi_name\":\"\",\"city\":\"\"},\"user_reqid\":\"0\",\"parse_result\":[{\"text\":\"\",\"semantic\":{\"baike_name\":\"周杰伦\"},\"app_id\":1100101,\"app_name\":\"\"}]}";
//        String reqStr = "{\"ret_count\":1,\"user_reqid\":\"0010496423149683958778189430993-6\",\"parse_result\":[{\"app_id\":1000101,\"text\":\"\",\"app_name\":\"READ.JOKE.RANDOMTEXT\",\"semantic\":{\"joke_type\":\"讲个笑话\"}}],\"user_info\":{\"userid\":\"0010496423\",\"channelType\":0},\"location_info\":{\"province\":\"北京\",\"city\":\"北京\",\"address\":\"信息路\",\"nearest_poi_name\":\"上地环岛南\",\"user_latitude\":\"39.45492\",\"user_longitude\":\"119.239293\"},\"suspect_info\":{},\"assist_version\":33}";
//        String reqStr="{\"ret_count\":1,\"user_reqid\":\"0010496423149741755257072526337-6\",\"parse_result\":[{\"app_id\": 2200801,\"app_name\": \"OTHERS.CLEVER.RIDDLES\",\"match_score\": 99,\"parse_type\": 2,\"negation\": 0,\"auto_learn\": 0,\"text\": \"\",\"semantic\": {\"riddles\": \"\"}}],\"user_info\":{\"userid\":\"0010496423\",\"channelType\":0},\"location_info\":{\"province\":\"北京\",\"city\":\"北京\",\"address\":\"信息路\",\"nearest_poi_name\":\"上地环岛南\",\"user_latitude\":\"39.45492\",\"user_longitude\":\"119.239293\"},\"suspect_info\":{},\"assist_version\":33}";
//        String reqStr="{\"ret_count\":1,\"user_reqid\":\"0010496423149879158015202411036-6\",\"parse_result\":[{\"app_id\":2203101,\"text\":\"\",\"app_name\":\"OTHERS.EXPRESS.EXPRESSQUERY\",\"semantic\":{\"express_company\":\"\",\"express_number\":\"9772488038208\"}}],\"user_info\":{\"userid\":\"0010496423\",\"channelType\":0},\"location_info\":{\"province\":\"北京\",\"city\":\"北京\",\"address\":\"信息路\",\"nearest_poi_name\":\"上地环岛南\",\"user_latitude\":\"39.45492\",\"user_longitude\":\"119.239293\"},\"suspect_info\":{},\"assist_version\":33}";
//          String reqStr="{\"ret_count\":1,\"user_reqid\":\"0010496423150000126129925978757-6\",\"parse_result\":[{\"app_id\":1000101,\"text\":\"\",\"app_name\":\"READ.JOKE.RANDOMTEXT\",\"semantic\":{\"joke_type\":\"讲个笑话\"}}],\"user_info\":{\"userid\":\"0010496423\",\"channelType\":0,\"supportType\":[0,1]},\"location_info\":{\"province\":\"北京\",\"city\":\"北京\",\"address\":\"信息路\",\"nearest_poi_name\":\"上地环岛南\",\"user_latitude\":\"39.45492\",\"user_longitude\":\"119.239293\"},\"suspect_info\":{},\"assist_version\":33}";
        String reqStr="{\"ret_count\":1,\"user_reqid\":\"0010496423153068308378555099205-6\",\"parse_result\":[{\"app_id\":1000402,\"text\":\"\",\"app_name\":\"READ.IMMORTAL.RESOURCES\",\"semantic\":{\"resources_name\":\"真人笑话\"}}],\"user_info\":{\"userid\":\"0010496423\",\"channelType\":0},\"location_info\":{\"province\":\"北京\",\"city\":\"北京\",\"address\":\"信息路\",\"nearest_poi_name\":\"上地环岛南\",\"user_latitude\":\"39.45492\",\"user_longitude\":\"119.239293\"},\"suspect_info\":{},\"assist_version\":33}";
//        String str = "{\"user_info\":{\"supportType\":[],\"supportMany\":true,\"channelType\":0,\"userid\":\"123321123\"},\"assist_version\":33,\"ret_count\":1,\"user_reqid\":\"0\",\"location_info\":{},\"parse_result\":[{\"app_name\":\"READ.JOKE.RANDOMTEXT\",\"semantic\":{\"joke_type\":\"笑话\"},\"app_id\":1000101}]}";
        DataRequest request = new DataRequest();
        request.setRequest(reqStr);
        DataResult result = dataService.requestData(request);
//        DataResult result = service.getResponse(request);

        System.out.println(result);
    }

    @Test
    public void testFaq(){
        FaqService faqService = context.getBean("faqService", FaqService.class);
        FaqRequest faqRequest = new FaqRequest();
        faqRequest.setQuestion("业务发展如何");
        faqRequest.setApiKey("dde0b179afa4430c8d52cb0eee59b228");

        System.out.println(faqService.requestFaq(faqRequest).getAnswer());

    }
}
