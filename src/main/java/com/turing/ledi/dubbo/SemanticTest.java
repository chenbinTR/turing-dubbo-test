package com.turing.ledi.dubbo;

import com.turing.dialogue.protocol.entity.BasisSemantic;
import com.turing.semantic.service.api.DynamicConfigService;
import com.turing.semantic.service.api.SemanticService;
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
	public void testSemantic() {
		SemanticService service = context.getBean("semanticService", SemanticService.class);
        List<Pattern> var2  = new ArrayList<>();
        var2.add(Pattern.SDEP);
        BasisSemantic result = service.parseSemantic("I want to go home",var2);
		System.out.println(result.getTermList().toString());
		result.getTermList().forEach(e->{
			System.out.println(e.getWord());
		});
	}

	@Test
	public void testDynamicConfig(){
		DynamicConfigService dynamicConfigService = context.getBean("dynamicConfigService", DynamicConfigService.class);
		System.out.println(dynamicConfigService.hashCode());
		String userid = "CS00000000000000000000000000000005";
		List<String> entities = Arrays.asList("刘备","");
		boolean result = dynamicConfigService.addDynamicWords(userid, entities);
		assert (result);
	}
}
