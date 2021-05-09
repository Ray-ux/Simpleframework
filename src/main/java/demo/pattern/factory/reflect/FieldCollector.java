package demo.pattern.factory.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class FieldCollector {


    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        获取Class对象
        Class<?> aClass = Class.forName("demo.pattern.factory.reflect.ReflectTarget");
//        获取所有公有的字段
        Field[] fields = aClass.getFields();
        System.out.println("获取所有的公有的字段");
        for (Field field : fields) {
            System.out.println(field);
        }

//        获取所有字段
        Field[] declaredFields = aClass.getDeclaredFields();
        System.out.println("获取所有的字段");
        for (Field f :
                declaredFields) {
            System.out.println(f);
        }

//        获取单个特定的公有的字段
        System.out.println("获取单个特定的公有的字段");

        Field name = aClass.getField("name");
        System.out.println("公有的field name：" + name);
        ReflectTarget reflectTarget = (ReflectTarget) aClass.getConstructor().newInstance();
//       给获取到的field赋值
        name.set(reflectTarget, "待反射一号");
//        验证对应的值
        System.out.println("验证name：" + reflectTarget.name);

//        获取单个的私有的Field
        Field targetInfo = aClass.getDeclaredField("targetInfo");
        System.out.println(targetInfo);
        targetInfo.setAccessible(true);
        targetInfo.set(reflectTarget, "私有的filed");
        System.out.println("验证信息" + reflectTarget);

    }
}
