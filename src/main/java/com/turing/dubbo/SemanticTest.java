package com.turing.dubbo;

import com.alibaba.fastjson.JSON;
import com.turing.dialogue.protocol.entity.BasisSemantic;
import com.turing.semantic.service.api.DynamicConfigService;
import com.turing.semantic.service.api.MultiSemanticService;
import com.turing.semantic.service.api.SemanticService;
import com.turing.semantic.service.bean.MultiSegment;
import com.turing.semantic.service.util.Pattern;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * AskClient
 *
 * @author LJL
 * @date 2016/3/17
 */
public class SemanticTest extends BaseTest {

    @Test
    public void testMultiSemantic() {
        MultiSemanticService service = context.getBean("multiSemanticService", MultiSemanticService.class);
        List<Pattern> var2 = new ArrayList<>();
        var2.add(Pattern.SDEP);
        List<MultiSegment> multiSegmentList = service.multiSegment("电脑的电阻约121Ω");
        System.out.println(JSON.toJSONString(multiSegmentList));
    }

    @Test
    public void testMultiSemanticEnglish() {
        MultiSemanticService service = context.getBean("multiSemanticService", MultiSemanticService.class);
        List<Pattern> var2 = new ArrayList<>();
        var2.add(Pattern.SDEP);
        List<MultiSegment> multiSegmentList = service.multiSegmentEnglish("w lucky is in the toilet.he is washing his");
        System.out.println(JSON.toJSONString(multiSegmentList));
    }

    @Test
    public void testDictSemantic(){

    }

    @Test
    public void testSemantic() {
        SemanticService service = context.getBean("semanticService", SemanticService.class);
        List<Pattern> var2 = new ArrayList<>();
        var2.add(Pattern.SDEP);
        BasisSemantic result = service.parseSemantic("我爱北京天安门", var2);
        System.out.println(result.getTermList().toString());
        result.getTermList().forEach(e -> {
            System.out.println(e.getWord());
        });
    }

    @Test
    public void testDynamicConfig() {
        DynamicConfigService dynamicConfigService = context.getBean("dynamicConfigService", DynamicConfigService.class);
        System.out.println(dynamicConfigService.hashCode());
        String userid = "CS00000000000000000000000000000005";
        List<String> entities = Arrays.asList("刘备", "");
        boolean result = dynamicConfigService.addDynamicWords(userid, entities);
        assert (result);
    }
}
