package org.simpleframework.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Component;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Repository;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 张烈文
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class BeanContainer {

    /**
     * 存放所有被配置标记的目标对象的Map
     */
    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

    /**
     * 加载bean的注解列表
     */
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION
            = Arrays.asList(Component.class, Repository.class, Service.class, Controller.class);


    /**
     * 容器是否已经加载bean
     */
    private volatile boolean  loaded = false;

    /**
     *
     * @return 是否已加载
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * 获取Container容器实例
     * @return
     */
    public static BeanContainer getInstance() {
        return ContainerHolder.HOLDER.instance;
    }

    private enum ContainerHolder {
        HOLDER;
        private BeanContainer instance;

        ContainerHolder() {
            instance = new BeanContainer();
        }
    }

    /**
     * 扫描加载所有Bean
     *
     * 加锁：线程安全
     */
    public synchronized void loadBeans(String packageName) {
//        判断容器是否被加载过
//        如果已经被加载过了直接返回，并记录日志
        if (isLoaded()) {
            log.warn("extract nothing from packageName" + packageName);
            return;
        }
//        通过ClassUtil来获取Class对象
            Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);

//        判断classSet是否为空
            if (ValidationUtil.isEmpty(classSet)) {
                log.warn("extract noting from packageName" + packageName);
                return;
            }
//        如果不为空
            for (Class<?> clazz : classSet) {
                for (Class<? extends Annotation> annotation : BEAN_ANNOTATION) {
//                如果类上面标记了定义的注解
                    if (clazz.isAnnotationPresent(annotation)) {
//                    将目标类作为键，目标类的实例作为值，放入到beanMap中
                        beanMap.put(clazz, ClassUtil.newInstance(clazz, true));
                    }
                }
            }
//            加载完成则将loaded设为true
        loaded = true;
        }

    /**
     * Bean实例数量
     * @return
     */
    public int size() {
        return beanMap.size();
    }
}





