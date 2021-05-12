package demo.pattern.singleton;

/**
 * @author 张烈文
 */
public class StarvingSingleton {

    private static final StarvingSingleton instance = new StarvingSingleton();

    private StarvingSingleton() {}

    public static StarvingSingleton getInstance() {
        return instance;
    }
}
