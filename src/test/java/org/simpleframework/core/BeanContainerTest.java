package org.simpleframework.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Slf4j
public class BeanContainerTest {

    private static BeanContainer beanContainer;

    @BeforeAll
    static void init() {
        beanContainer = BeanContainer.getInstance();
    }

    @Test
    public void loadBeansTest() {
        if (!beanContainer.isLoaded()) {
            System.out.println("ray");
        }
        Assertions.assertEquals(false, beanContainer.isLoaded());
        beanContainer.loadBeans("com.ray");
        Assertions.assertEquals(6, beanContainer.size());
        System.out.println(beanContainer.size());
    }
}
