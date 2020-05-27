package com.turing.ledi.dubbo;

import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ChenOT
 * @date 2019-10-12
 * @see
 * @since
 */
public class TestBase {
    protected ClassPathXmlApplicationContext context;

    @Before
    public void initContext() {
        context = new ClassPathXmlApplicationContext(
                "classpath*:dubbo-application.xml");
        context.start();
        System.out.println("load application success......");
    }
}
