package demo.pattern.factory.reflect;

import com.sun.java.swing.plaf.windows.WindowsDesktopIconUI;

public class ReflectTarget {

    public  String name;
    protected int index;
    char type;
    private String targetInfo;


    public void show1(String s) {
        System.out.println("调用了公有的，String参数的show1():s=" + s);
    }

    protected void show2() {
        System.out.println("调用了受保护的，无参的show2");
    }

    void show3() {
        System.out.println("调用了默认的，无参的show3()");

    }

    private String show4(int index) {
        System.out.println("调用了私有的，并且有返回值的，int参数的show4()：index=" + index);
        return "show4 result";
    }
    @Override
    public String toString() {
        return "ReflectTarget{" +
                "name='" + name + '\'' +
                ", index=" + index +
                ", type=" + type +
                ", targetInfo='" + targetInfo + '\'' +
                '}';
    }

    ReflectTarget(String str) {
        System.out.println("默认的构造方法s=" + str);
    }

    public ReflectTarget() {
        System.out.println("调用了共有的无参构造方法。。。");

    }

    //    有一个参数的构造函数
    public ReflectTarget(char name) {
        System.out.println("调用了带有一个参数的构造函数，参数值为+" + name);
    }

    public ReflectTarget(String name, int index) {
        System.out.println("调用了带有多个参数的构造方法，参数值为【目标名】：" + name + "【序号】" + index);
    }

    protected ReflectTarget(Boolean n) {
        System.out.println("受保护的构造方法n：" + n);
    }

    private ReflectTarget(int index) {
        System.out.println("私有的构造方法index:" + index);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

//        第一种方式获取Class对象
        ReflectTarget reflectTarget = new ReflectTarget();
        Class aClass = reflectTarget.getClass();
        System.out.println("1st:"+aClass.getName());
//        第二种方式获取对象

        Class<ReflectTarget> reflectTargetClass = ReflectTarget.class;
        System.out.println("2nd:"+reflectTargetClass.getName());

//        判断第一种方式获取的class对象和第二种方式获取的是否是同一个
        System.out.println(reflectTargetClass == aClass);

//        第三种方式获取Class对象
        Class<?> aClass1 = Class.forName("demo.pattern.factory.reflect.ReflectTarget");
        System.out.println("3rd:" + aClass1.getName());
        System.out.println(reflectTargetClass == aClass1);

    }
}
