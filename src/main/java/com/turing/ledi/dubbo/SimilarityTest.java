package com.turing.ledi.dubbo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.turing.similarity.comm.data.process.ChatDataProcessService;
import com.turing.similarity.comm.entity.*;
import com.turing.similarity.comm.service.SemanticSimilarityService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 相似度测试
 *
 * @author ChenOT
 * @date 2019-04-10
 * @see
 * @since
 */
public class SimilarityTest extends BaseTest {
    private SemanticSimilarityService semanticSimilarityService;
//    private ChatDataProcessService chatDataProcessService;

    @Before
    public void init() {
        System.out.println("simi init");
        semanticSimilarityService = context.getBean("semanticSimilarityService", SemanticSimilarityService.class);
//        chatDataProcessService = context.getBean("chatDataProcessService", ChatDataProcessService.class);
    }

    @Test
    public void testDataProcess() {
        assert (null != context);
        System.out.println("测试成功");
    }

    @Test
    public void testChat() {
//        List<Scheme> schemes = new ArrayList<>();
//        Scheme scheme = new Scheme();
//        scheme.set
//        chatDataProcessService.insert();
        String[] tableNames = {"turing_scripts"};
//        SimilarityRequest similarityRequest = new SimilarityRequest("中秋节的习俗都有哪些呢", RequestType.FAQ_DUP);
        SimilarityRequestWithoutCache similarityRequest = new SimilarityRequestWithoutCache("给我读在花园的绘本", RequestType.FAQ_DUP);
        similarityRequest.setTableNames(tableNames);
        similarityRequest.setUserAccount("b79a94caa96e4e8692be89263cdbe82e");
        SimilarityResult result = semanticSimilarityService.getFAQSimilarity(similarityRequest);
        List<SimiResultItem> resultItems = result.getResultItems();
        System.out.println(JSONObject.toJSONString(result));
        if (CollectionUtils.isNotEmpty(resultItems)) {
            for (SimiResultItem s : resultItems) {
                System.out.println(s.getQuestion() + "\t" + s.getMatchScore() + "\t" + s.getTotalScore() + "\t" + s.getId() + "\t" + s.getTableName());
            }
        } else {
            System.out.println(JSONObject.toJSONString(result));
        }
    }

    @Test
    public void testNlu() {
//        String[] tableNames = {"chat_question"};
        SimilarityRequest similarityRequest = new SimilarityRequest("屏幕调到最亮", RequestType.NLU);
//            SimilarityRequestWithoutCache similarityRequest = new SimilarityRequestWithoutCache("武汉加油", RequestType.CHAT);
//        similarityRequest.setTableNames(tableNames);
//            similarityRequest.setUserAccount("3c8a7f5b3ddd4203b59846addc12d0e2");
        SimilarityResult result = semanticSimilarityService.getChatSimilarity(similarityRequest);
        List<SimiResultItem> resultItems = result.getResultItems();
        if (CollectionUtils.isNotEmpty(resultItems)) {
            for (SimiResultItem s : resultItems) {
                System.out.println(s.getQuestion() + "\t" + s.getMatchScore() + "\t" + s.getTotalScore() + "\t" + s.getId() + "\t" + s.getTableName());
            }
        } else {
            System.out.println("resultItems is empty");
        }
    }

    @Test
    public void testQa() {
        String[] tableNames = {"turing_faq_simi_smart"};
        SimilarityRequest similarityRequest = new SimilarityRequest("人工服务", RequestType.FAQ_CS);
//        SimilarityRequestWithoutCache similarityRequest = new SimilarityRequestWithoutCache("身体哪些部位能看出寿命长短", RequestType.QA_SKILL);
        similarityRequest.setTableNames(tableNames);
//        try {
//            SimilarityClient.getSimilarity(similarityRequest);
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
        SimilarityResult result = semanticSimilarityService.getCustomerServiceSimilarity(similarityRequest);
        List<SimiResultItem> resultItems = result.getResultItems();
        if (CollectionUtils.isNotEmpty(resultItems)) {
            for (SimiResultItem s : resultItems) {
                System.out.println(s.getQuestion() + "\t" + s.getMatchScore() + "\t" + s.getTotalScore() + "\t" + s.getId() + "\t" + s.getTableName());
            }
        } else {
            System.out.println("resultItems is empty");
        }
    }

    @Test
    public void testTowSentence() {
        Pair<Source, Double> pair = semanticSimilarityService.getSimilarity("继续播放", "不用再播了", RequestType.CHAT);
        System.out.println(JSON.toJSONString(pair));
    }
}
