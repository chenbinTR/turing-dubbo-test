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
    public void testQa() {
        String[] tableNames = {"turing_faq_14","turing_faq_simi_14"};
//        String[] tableNames = {"common_chat_question","chat_question"};
//        SimilarityRequestWithoutCache similarityRequest = new SimilarityRequestWithoutCache("公司的社会责任", RequestType.FAQ_CS);
        SimilarityRequest similarityRequest = new SimilarityRequestWithoutCache("公司的社会责任", RequestType.FAQ_CS);
        similarityRequest.setTableNames(tableNames);
//        similarityRequest.setUserAccount("64563a7dda578f98c78e549a0c4e9189");
        similarityRequest.setUserAccount("a8de3e58c894461d86b0a7ae46f15540");
        SimilarityResult result = semanticSimilarityService.getFAQSimilarity(similarityRequest);
        System.out.println(JSONObject.toJSONString(result));
        List<SimiResultItem> resultItems = result.getResultItems();
        if (CollectionUtils.isNotEmpty(resultItems)) {
            for (SimiResultItem s : resultItems) {
                System.out.println(s.getQuestion() + "\t" + s.getMatchScore() + "\t" + s.getTotalScore() + "\t" + s.getId() + "\t" + s.getTableName());
            }
        }
    }

    @Test
    public void testTowSentence() {
        Pair<Source, Double> pair = semanticSimilarityService.getSimilarity("公司的社会责任", "社会责任", RequestType.FAQ_CS);
        System.out.println(JSON.toJSONString(pair));
    }
}
