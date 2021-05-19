### 自研简易版SpringIOC&AOP

##### 知识点

- 设计模式
- 反射机制
- 熟悉注解

###### 设计模式
[简单工厂模式](https://github.com/Ray-ux/Simpleframework/tree/master/src/main/java/demo/pattern/factory/simple) </br>
优点：

- 可以对创建的对象进行“加工”，对客户端隐藏相关细节 

缺点：

- 违反开闭原则（对修改关闭，对扩展开放），新增|删除子类都需要去修改工厂类的代码
- 创建对象的逻辑复杂|创建对象过多而造成代码臃肿

[工厂方法模式](https://github.com/Ray-ux/Simpleframework/tree/master/src/main/java/demo/pattern/factory/method) </br>
优点：

- 遵循开闭原则
- 对客户端隐藏对象的创建细节
- 遵循单一职责

缺点：

- 添加子类的时候“拖家带口”，还需要添加工厂类
- 只支持同一类产品的创建

[抽象工厂模式](https://github.com/Ray-ux/Simpleframework/tree/master/src/main/java/demo/pattern/factory/abstractf) </br>
优点：

- 解决了工厂方法模式只支持生成一种产品的弊端
- 新增一个产品族，只需要增加一个新的具体工厂，不需要修改代码

缺点：

- 同一个产品族添加新产品时依旧违背开闭原则，增加系统的复杂性

[单例模式]() </br>

- [枚举饿汉](https://github.com/Ray-ux/Simpleframework/blob/master/src/main/java/demo/pattern/singleton/EnumStarvingSingleton.java)

- [双重检查懒汉](https://github.com/Ray-ux/Simpleframework/blob/master/src/main/java/demo/pattern/singleton/LazyDoubleCheckSingleton.java)

问题：

单例模式真的安全？
````java
public class StarvingSingleton {

    private static final StarvingSingleton instance = new StarvingSingleton();

    private StarvingSingleton() {}

    public static StarvingSingleton getInstance() {
        return instance;
    }
}
````
虽然我们将上面的饿汉模式的构造函数设为了私有，但是我们仍然可以通过反射来新建一个新的实例

````java
import demo.pattern.singleton.StarvingSingleton;

import java.lang.reflect.Constructor;

public class SingletonDemo {
    public static void main(String[] args) {
        Class<?> starvingSingleton = Class.forName("StarvingSingleton");
        Constructor<?> declaredConstructor = starvingSingleton.getDeclaredConstructor();
//        通过该设置我们打破了私有构造函数
        declaredConstructor.setAccessible(true);
        Object o = declaredConstructor.newInstance();
        System.out.println(o == StarvingSingleton.getInstance()); //结果为false
    }
}
````
解决：

用枚举实现饿汉

```java
public class EnumStarvingSingleton {
    private EnumStarvingSingleton() { }
    public static EnumStarvingSingleton getInstance() {
        return ContainerHolder.HOLDER.instance;
    }
    //枚举类型是在类加载的时候就被创建出来
    private enum  ContainerHolder{
        HOLDER;
        private EnumStarvingSingleton instance;
        ContainerHolder() {
            instance = new EnumStarvingSingleton();
        }
    }
}

//测试：
  //      外部类通过反射获取EnumStarvingSingleton的类对象然后创建实例
public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //通过类名调用getInstance方法
        System.out.println(EnumStarvingSingleton.getInstance());
//        通过反射获取到EnumStarvingSingleton的实例
        Class<?> aClass = EnumStarvingSingleton.class;
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
//        将获取到的私有构造函数,设置为强制允许外部访问
        declaredConstructor.setAccessible(true);
        EnumStarvingSingleton  enumStarvingSingleton = (EnumStarvingSingleton) declaredConstructor.newInstance();
        //通过EnumStarvingSingleton实例来调用getInstance方法
        System.out.println(enumStarvingSingleton.getInstance());
}
```
为什么通过枚举不能被反射获取到呢？
```java
//我们通过反射来获取试试
public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //获取枚举类对象
        Class<ContainerHolder> containerHolderClass = ContainerHolder.class;
//获取枚举类的构造函数
        Constructor<ContainerHolder> declaredConstructor = containerHolderClass.getDeclaredConstructor(String.class,int.class);
        declaredConstructor.setAccessible(true);
//        创建对象
        System.out.println(declaredConstructor.newInstance("ray",1));

}
```
报错：

![1620993794463](C:\Users\张烈文\AppData\Roaming\Typora\typora-user-images\1620993794463.png)


### 回归正题
IoC具备的最基本功能：
- 解析配置
- 定位与注册对象
- 注入对象
- 提供通用的工具类

需要实现的点：

![大致流程](C:\Users\张烈文\AppData\Roaming\Typora\typora-user-images\1620653293751.png)

- [创建注解](https://github.com/Ray-ux/Simpleframework/tree/master/src/main/java/org/simpleframework/core/annotation) 

- [提取标记对象](https://github.com/Ray-ux/Simpleframework/blob/master/src/main/java/org/simpleframework/util/ClassUtil.java)

- [实现容器](https://github.com/Ray-ux/Simpleframework/blob/master/src/main/java/org/simpleframework/core/BeanContainer.java)

- [依赖注入](https://github.com/Ray-ux/Simpleframework/tree/master/src/main/java/org/simpleframework/inject)