package org.simpleframework.core;

import com.ray.controller.DispatcherServlet;
import com.ray.controller.frontend.MainPageController;
import com.ray.service.solo.HeadLineService;
import com.ray.service.solo.impl.HeadLineServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.simpleframework.core.annotation.Controller;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeanContainerTest {

    private static BeanContainer beanContainer;

    @BeforeAll
    static void init() {
        beanContainer = BeanContainer.getInstance();
    }

    @DisplayName("加载目标类及其实例到BeanContainer:loadBeansTest")
    @Test
    @Order(1)
    public void loadBeansTest() {
        if (!beanContainer.isLoaded()) {
            System.out.println("ray");
        }
        Assertions.assertEquals(false, beanContainer.isLoaded());
        beanContainer.loadBeans("com.ray");
        Assertions.assertEquals(6, beanContainer.size());
        System.out.println(beanContainer.size());
    }

    @DisplayName("根据类获取其实例：getBeanTest")
    @Order(2)
    @Test
    public void getBeanTest() {
        MainPageController controller = (MainPageController) beanContainer.getBean(MainPageController.class);
        Assertions.assertEquals(true, controller instanceof MainPageController);
//        由于DispatcherServlet没有被我们自己定义的注解修饰，因此不会被BeanContainer所管理
        DispatcherServlet dispatcherServlet = (DispatcherServlet) beanContainer.getBean(DispatcherServlet.class);
        Assertions.assertEquals(null, dispatcherServlet);
        System.out.println(1);
    }

    @DisplayName("根据注解获取对应的实例：getClassesByAnnotationTest")
    @Order(3)
    @Test
    public void getClassesByAnnotationTest() {
        Assertions.assertEquals(3, beanContainer.getClassesByAnnotation(Controller.class).size());
        System.out.println(beanContainer.getClassesByAnnotation(Controller.class).size());
    }

    @DisplayName("通过超类获取对应的子类Class：getClassesBySupperTest")
    @Order(3)
    @Test
    public void getClassesBySupperTest() {
        Assertions.assertEquals(true, beanContainer.getClassesBySuper(HeadLineService.class).contains(HeadLineServiceImpl.class));
    }
}
