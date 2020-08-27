package com.turing.ledi.dubbo;

import com.alibaba.fastjson.JSON;
import com.turing.similarity.comm.entity.*;
import com.turing.similarity.comm.service.SemanticSimilarityService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 相似度测试
 *
 * @author ChenOT
 * @date 2019-04-10
 * @see
 * @since
 */
public class SimilarityTest extends TestBase {
    private SemanticSimilarityService semanticSimilarityService;

    @Before
    public void init() {
        System.out.println("simi init");
        semanticSimilarityService = context.getBean("semanticSimilarityService", SemanticSimilarityService.class);
    }

    @Test
    public void testDataProcess() {
        assert (null != context);
        System.out.println("测试成功");
    }

    @Test
    public void testChat() {
        String[] tableNames = {"chat_question"};
        SimilarityRequest similarityRequest = new SimilarityRequest("A分到哪类垃圾", RequestType.CHAT);
//            SimilarityRequestWithoutCache similarityRequest = new SimilarityRequestWithoutCache("武汉加油", RequestType.CHAT);
        similarityRequest.setTableNames(tableNames);
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
        String[] tableNames = {"qa_common_type_11"};
        SimilarityRequest similarityRequest = new SimilarityRequest("身体哪些部位能看出寿命长短", RequestType.QA_SKILL);
//        SimilarityRequestWithoutCache similarityRequest = new SimilarityRequestWithoutCache("身体哪些部位能看出寿命长短", RequestType.QA_SKILL);
        similarityRequest.setTableNames(tableNames);
//        try {
//            SimilarityClient.getSimilarity(similarityRequest);
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
        SimilarityResult result = semanticSimilarityService.getQaSkillServiceSimilarity(similarityRequest);
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
        Pair<Source, Double> pair = semanticSimilarityService.getSimilarity("你好，看到了把abc", "你好，看到了把abc", RequestType.CHAT);
        System.out.println(JSON.toJSONString(pair));
    }
}
