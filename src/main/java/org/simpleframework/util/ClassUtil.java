package org.simpleframework.util;




import demo.pattern.reflect.FieldCollector;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 张烈文
 */
@Slf4j
public class ClassUtil {

    public static final String FILE_PROTOCOL = "file";

    /**
     * 获取包下类集合
     *
     * @param packageName
     * @return
     */
    public static Set<Class<?>> extractPackageClass(String packageName) {

//        1.获取到类的加载器
        ClassLoader classLoader = getClassLoader();

//        2.通过类加载器获取到加载的资源信息
        URL url = classLoader.getResource(packageName.replace(".", "/"));
        if (url == null) {


            log.warn("unable to retrieve anything from package:{}", packageName);

            return null;
        }
//        3.依据不同的资源类型，采用不同的方式获取资源的集合
        Set<Class<?>> classSet = null;
//        过滤出文件类型的资源
        if (url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)) {
            classSet = new HashSet<>();
            File packageDirectory = new File(url.getPath());
            extractClassFile(classSet, packageDirectory, packageName);
        }
        return classSet;

    }

    /**
     * 递归获取目标package里面的所有class文件（包括子package里的class文件）
     * @param emptyClassSet
     * @param fileSource
     * @param packageName
     */
    private static void extractClassFile(Set<Class<?>> emptyClassSet, File fileSource, String packageName) {
        if (!fileSource.isDirectory()) {
            return;
        }
//        如果是一个文件夹，则调用其listFiles方法获取当前文件夹下的所有文件或文件夹
        File[] files = fileSource.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }else{
//                    获取文件的绝对值路径
                    String absolutePath = file.getAbsolutePath();
                    if (absolutePath.endsWith(".class")) {
//                        若是class文件,则直接加载
                        addToClassSet(absolutePath);
                    }
                }
                return false;

            }

//            根据class文件的绝对路径,获取并生成class对象,并放入classSet中
            private void addToClassSet(String absolutePath) {

//                1.从class文件的绝对值路径里提取出包含了package的类名
//                如/user/ray/springframework/sampleframework/target/classes/com/ray/entity/dto/MainPageInfoDTO.class
//                需要弄成com.ray.entity.dto.MainPageInfoDTO

//                先将绝对路径中的/替换为.
                absolutePath = absolutePath.replace(File.separator, ".");
//                截取类的路径
                String className = absolutePath.substring(absolutePath.indexOf(packageName));
//                去掉.class
                className = className.substring(0, className.lastIndexOf("."));

//                2.通过反射机制获取对应的Class对象并加入到classSet里

                Class<?> targetClass = loadClass(className);
                emptyClassSet.add(targetClass);
            }
        });
        if (files != null) {
            for (File f : files) {
//                递归调用
                extractClassFile(emptyClassSet, f, packageName);
            }
        }
    }

    /**
     * 获取Class对象
     *
     * @param className class全名=package+类全名
     */
    private static Class<?> loadClass(String className) {

        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("load class error:{}", e);
            throw new RuntimeException();
        }
    }

    /**
     * 获取classLoader
     * @return
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }


    /**
     * 实例化class,
     * 简易版框架，这里先只通过无参构造函数创建实例
     * @param clazz
     * @param accessible  是否支持private类型的构造函数
     * @param <T>  class的类型
     * @return
     */
    public static <T> T newInstance(Class<?> clazz,boolean accessible)  {
        try {
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
            declaredConstructor.setAccessible(accessible);
            return (T) declaredConstructor.newInstance();
        } catch (Exception e) {
            log.error("newInstance error", e);
            throw new RuntimeException(e);
        }
    }


    /**
     * 设置类的属性值
     * @param field 成员变量
     * @param target 类实例
     * @param value 成员变量的值
     * @param accessible 是否允许设置私有属性
     */
    public static void setField(Field field, Object target, Object value, boolean accessible) {
        field.setAccessible(accessible);
        try {
            field.set(target, value);
        } catch (IllegalAccessException e) {
            log.error("setField error", e);
            throw new RuntimeException(e);
        }
    }
}
