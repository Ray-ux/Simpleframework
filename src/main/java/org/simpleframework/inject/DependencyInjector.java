package org.simpleframework.inject;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.annotation.Autowired;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;

@Slf4j
public class DependencyInjector {
    /**
     * Bean容器
     */
    private BeanContainer beanContainer;

    /**
     * 当我们创建DependencyInjector实例，就可以得到BeanContainer的实例
     */
    public DependencyInjector() {
        beanContainer = BeanContainer.getInstance();
    }


    /**
     * 该方法用于对整个容器的Class对象做依赖注入处理
     */
    public void doIoc() {
        if (ValidationUtil.isEmpty(beanContainer.getClasses())) {
            log.warn("empty classset in beanContainer");
            return;
        }
//        1.遍历容器中所有的Class对象
        for (Class<?> clazz : beanContainer.getClasses()) {

//        2.获取Class对象的所有成员变量
            Field[] fields = clazz.getDeclaredFields();
            if (ValidationUtil.isEmpty(fields)) {
                continue;
            }
            for (Field f : fields) {
//        3.找出被Autowired标记的成员变量
                if (f.isAnnotationPresent(Autowired.class)) {

//                    当确定了某个属性被Autowired属性标记时，我们获取到Autowired
                    Autowired autowired = f.getAnnotation(Autowired.class);
//                    获取autowired的值
                    String autowiredValue = autowired.value();

//        4.获取成员变量的类型
                    Class<?> filedClass = f.getType();
//        5.获取这些成员变量的类型在容器里对应的实例
                    Object filedValue = getFiledInstance(filedClass,autowiredValue);
                    if (filedValue == null) {
                        throw new RuntimeException("unable to inject relevant type,target fieldClass is:" + filedClass.getName());
                    }
//        6.通过反射将对应的成员变量实例注入到成员变量所在类
                    Object targetBean = beanContainer.getBean(clazz);
                    ClassUtil.setField(f, targetBean, filedValue, true);
                }
            }

        }
    }

    /**
     * 根据Class在beanContainer里获取其实例或者实现类
     * @param filedClass
     * @return
     */
    private Object getFiledInstance(Class<?> filedClass,String autowiredValue) {

        Object fieldValue = beanContainer.getBean(filedClass);
        if (fieldValue != null) {
            return fieldValue;
        }else {
            Class<?> implementedClass = getImplementClass(filedClass,autowiredValue);
            if (implementedClass != null) {
                return beanContainer.getBean(implementedClass);
            }else{
                return null;
            }
        }
    }

    /**
     * 获取接口的实现类
     *
     * @param filedClass
     * @return
     */

    private Class<?> getImplementClass(Class<?> filedClass, String autowiredValue) {
//        通过接口获取实现类
        Set<Class<?>> classSet = beanContainer.getClassesBySuper(filedClass);

//        一个接口有多个实现类：
//        由于@Autowired是按照类型注入的，如果一个接口下有多个实现类，应该如何注入我们想要的实现类呢？
//        Spring通过利用@Qualifier可以指定具体的名字，从而确定注入实例的唯一性

//        自研框架，为了使代码更简洁，我们不像spring那样引入@Qualifier
        if (!ValidationUtil.isEmpty(classSet)) {
//            1.判断autowiredValue是否为默认值"",如果为空就说明用户没有指定具体需要返回什么类型的实例
            if (ValidationUtil.isEmpty(autowiredValue)) {
//                若classSet。size()==1,说明只有一个实现类则直接返回
                if (classSet.size() == 1) {
                    return classSet.iterator().next();
                }else{
//                    如果多于两个实现类且用户未指定其中一个实现类，则抛出异常
                    throw new RuntimeException("multiple implemented classes for" + filedClass.getName() + "please assign @Autowired value");
                }
//                如果用户指定了值
            }else{

                for (Class<?> clazz : classSet) {
//                    判断用户指定的值是否与类的类名一致
                    if (autowiredValue.equals(clazz.getSimpleName())) {
                        return clazz;
                    }
                }

            }
        }
        return null;
    }
}
