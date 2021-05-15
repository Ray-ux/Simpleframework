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
import java.util.stream.Collectors;

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


    /**
     * 添加一个class对象及其Bean实例
     *
     * @param clazz clazz对象
     * @param bean  bean实例
     * @return 原有的Bean实例，没有则返回
     */

    public Object addBean(Class<?> clazz, Object bean) {

        return beanMap.put(clazz, bean);
    }


    /**
     * 移除一个IOC容器管理的对象
     * @param clazz
     * @return
     */
    public Object removeBean(Class<?> clazz) {

        return beanMap.remove(clazz);
    }

    /**
     * 根据Class对象获取Bean实例
     * @param clazz
     * @return
     */
    public Object getBean(Class<?> clazz) {
        return beanMap.get(clazz);
    }


    /**
     * 获取容器管理的所有Class对象集合
     * @return
     */
    public Set<Class<?>> getClasses() {
        return beanMap.keySet();
    }

    /**
     * 获取容器中所有的Bean集合
     * @return
     */
   public Collection<Object> getBeans() {
        return beanMap.values();
    }

    /**
     * 根据注解筛选出Bean的Class集合
     *
     * @param annotation
     */
    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation) {

//        1.获取beanMap的所有class对象
        Set<Class<?>> classSet = getClasses();
        if (ValidationUtil.isEmpty(classSet)) {
            log.warn("nothing in beanMap");
            return null;
        }
//        2.通过注解筛选被注解标记的class对象，并添加到classSet里
        Set<Class<?>> collect = classSet.stream().filter(aClass -> aClass.isAnnotationPresent(annotation))
                .collect(Collectors.toSet());
        return collect.size() > 0 ? collect : null;

    }


    /**
     * 通过接口或者父类获取实现类或者子类的Class集合，不包括其本身
     *
     * @param interfaceOrClass
     * @return Class集合
     */
    public Set<Class<?>> getClassesBySuper(Class<?> interfaceOrClass) {

//        1.获取beanMap的所有class对象
        Set<Class<?>> classSet = getClasses();
        if (ValidationUtil.isEmpty(classSet)) {
            log.warn("nothing in beanMap");
            return null;
        }
//        2.判断keySet里的元素是否是传入的接口或者类的子类，如果是就将其添加到classSet里
        Set<Class<?>> collect = classSet.stream().filter(aClass -> interfaceOrClass.isAssignableFrom(aClass))
                .collect(Collectors.toSet());
        return collect.size() > 0 ? collect : null;

    }
}





