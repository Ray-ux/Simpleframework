package demo.pattern.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationParser {
//    解析类的注解

    public static void parseTypeAnnotation() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("demo.pattern.annotation.ImoocCourse");
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            CourseInfoAnnotation courseInfoAnnotation = (CourseInfoAnnotation) annotation;
            System.out.println(courseInfoAnnotation.courseName() + courseInfoAnnotation.courseTag());
        }
    }

    //   解析成员变量的注解
    public static void parseFieldAnnotation() throws ClassNotFoundException, NoSuchFieldException {
        Class<?> aClass = Class.forName("demo.pattern.annotation.ImoocCourse");
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field f :
                declaredFields) {
//            判断成员变量中是否有指定注解类型的注解
            boolean annotationPresent = f.isAnnotationPresent(PersonInfoAnnotation.class);

            if (annotationPresent) {
                PersonInfoAnnotation annotation = f.getAnnotation(PersonInfoAnnotation.class);
                String name = annotation.name();
                System.out.println(annotation.name() + "" + annotation.age());
            }
        }
    }

    public static void parseMethodAnnotation() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("demo.pattern.annotation.ImoocCourse");
        Method[] declaredMethods = aClass.getDeclaredMethods();

        for (Method m :
                declaredMethods) {

            boolean annotationPresent = m.isAnnotationPresent(CourseInfoAnnotation.class);
            if (annotationPresent) {
                CourseInfoAnnotation annotation = m.getAnnotation(CourseInfoAnnotation.class);
                System.out.println(annotation.courseName() + annotation.courseIndex());
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
//        parseTypeAnnotation();

        parseFieldAnnotation();

    }
}
