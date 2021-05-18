package org.simpleframework.inject;

import com.ray.controller.frontend.MainPageController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.core.BeanContainer;

@Slf4j
public class DependencyInjectorTest {


    @DisplayName("依赖注入")
    @Test
    public void doIocTest() {
        BeanContainer instance = BeanContainer.getInstance();
        instance.loadBeans("com.ray");
        Assertions.assertEquals(true, instance.isLoaded());
        MainPageController mainPageController = (MainPageController) instance.getBean(MainPageController.class);
        Assertions.assertEquals(true, mainPageController instanceof MainPageController);

//        在没进行Ioc时获取mainPageController实例里的属性,应为null
        Assertions.assertEquals(null, mainPageController.getService());

//        调用doIoc
        new DependencyInjector().doIoc();

        Assertions.assertNotEquals(null, mainPageController.getService());
    }
}
