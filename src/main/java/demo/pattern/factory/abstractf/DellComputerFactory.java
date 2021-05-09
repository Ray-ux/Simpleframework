package demo.pattern.factory.abstractf;

import demo.pattern.factory.entity.DellKeyBoard;
import demo.pattern.factory.entity.DellMouse;
import demo.pattern.factory.entity.KeyBoard;
import demo.pattern.factory.entity.Mouse;

public class DellComputerFactory implements MouseKeyBoardFactory{
    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }

    @Override
    public KeyBoard createKeyBoard() {
        return new DellKeyBoard();
    }
}
