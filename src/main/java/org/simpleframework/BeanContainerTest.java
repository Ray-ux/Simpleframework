package org.simpleframework;

import org.simpleframework.core.BeanContainer;

/**
 * @author 张烈文
 */
public class BeanContainerTest {
    private static BeanContainer beanContainer;


    static{
        beanContainer = BeanContainer.getInstance();
    }


}
