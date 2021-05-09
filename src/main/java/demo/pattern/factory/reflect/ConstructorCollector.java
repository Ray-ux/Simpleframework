package demo.pattern.factory.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorCollector {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Class.forName("demo.pattern.factory.reflect.ReflectTarget");

//        获取所有的公有的构造方法
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor s : constructors) {
            System.out.println(s);
        }

//        获取所有构造方法
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();

        for (Constructor c : declaredConstructors) {
            System.out.println(c);
        }
//        获取单个带参数的公有方法
        Constructor constructor = clazz.getConstructor(String.class, int.class);
        System.out.println(constructor);
//        获取单个私有的构造方法

        Constructor declaredConstructor = clazz.getDeclaredConstructor(int.class);
        System.out.println(declaredConstructor);


//        调用私有构造方法创建你实例

//        暴力访问忽略掉范围跟修饰符
        declaredConstructor.setAccessible(true);

        ReflectTarget o = (ReflectTarget) declaredConstructor.newInstance(1);

    }
}
