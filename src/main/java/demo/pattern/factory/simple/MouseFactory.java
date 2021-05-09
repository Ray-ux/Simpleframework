package demo.pattern.factory.simple;

import demo.pattern.factory.entity.DellMouse;
import demo.pattern.factory.entity.HpMouse;
import demo.pattern.factory.entity.Mouse;


/**
 * 需要创建的对象较少
 * 客户端不关心对象的创建
 *
 * 优点：可以对创建的对象进行“加工”，对客户端隐藏相关细节
 * 缺点：因创建逻辑复杂或创建对象过多而造成代码臃肿
 * 缺点：新增，删除子类均会违反开闭原则
 * */
public class MouseFactory {

    public static Mouse createMouse(int type) {
        switch (type) {
            case 0:
                return new DellMouse();
            case 1:
                return new HpMouse();
            default:
                return new HpMouse();
        }
    }

    public static void main(String[] args) {
        Mouse mouse = MouseFactory.createMouse(0);
        mouse.sayHi();
    }
}
