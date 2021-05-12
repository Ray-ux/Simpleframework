package demo.pattern.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodCollector {


    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class<?> aClass = Class.forName("demo.pattern.reflect.ReflectTarget");
//        2获取所有的公有方法
        Method[] methods = aClass.getMethods();

        for (Method m :
                methods) {
            System.out.println(m);

        }

//        3.获取该类的所有方法
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method m : declaredMethods
        ) {
            System.out.println(m);

        }
//        4.获取单个公有的方法

        Method show1 = aClass.getMethod("show1", String.class);
        System.out.println(show1);

        ReflectTarget reflectTarget = (ReflectTarget) aClass.getConstructor().newInstance();
        show1.invoke(reflectTarget, "show1");

//        获取一个私有的成员方法

        Method show4 = aClass.getDeclaredMethod("show4", int.class);
        show4.setAccessible(true);
        show4.invoke(reflectTarget, 1);
    }
}
