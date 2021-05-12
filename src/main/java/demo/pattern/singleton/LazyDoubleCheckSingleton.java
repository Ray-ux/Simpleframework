package demo.pattern.singleton;

/**
 * 懒汉模式
 * 再用到时才创建实例
 * @author 张烈文
 */
public class LazyDoubleCheckSingleton {


    private static  volatile LazyDoubleCheckSingleton instance = null;


    private LazyDoubleCheckSingleton() {

    }

    public static LazyDoubleCheckSingleton getInstance() {

        if (instance == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (instance == null) {
                    /**
                     * 1.分配对象内存空间 memory=allocate()
                     * 2.初始化对象 instance(memory)
                     * 3.设置instance指向刚分配的内存地址,此时instance!=null
                     */
                    instance = new LazyDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

}
