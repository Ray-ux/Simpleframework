package demo.pattern.factory.method;

import demo.pattern.factory.entity.DellMouse;
import demo.pattern.factory.entity.Mouse;
import demo.pattern.factory.method.MouseFactory;

public class DellMouseFactory implements MouseFactory {
    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }
}
