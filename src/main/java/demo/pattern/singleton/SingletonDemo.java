package demo.pattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author 张烈文
 */
public class SingletonDemo {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        System.out.println(EnumStarvingSingleton.getInstance());
//        通过反射获取到EnumStarvingSingleton的实例
        Class<?> aClass = EnumStarvingSingleton.class;
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
//        将获取到的私有构造函数,设置为强制允许外部访问
        declaredConstructor.setAccessible(true);
       EnumStarvingSingleton  enumStarvingSingleton = (EnumStarvingSingleton) declaredConstructor.newInstance();
        System.out.println(enumStarvingSingleton.getInstance());


    }
}
