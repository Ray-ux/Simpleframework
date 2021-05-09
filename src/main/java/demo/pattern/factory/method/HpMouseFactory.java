package demo.pattern.factory.method;

import demo.pattern.factory.entity.HpMouse;
import demo.pattern.factory.entity.Mouse;

public class HpMouseFactory implements MouseFactory{
    @Override
    public Mouse createMouse() {
        return new HpMouse();
    }
}
