package demo.pattern.factory.abstractf;

import demo.pattern.factory.entity.HpKeyBoard;
import demo.pattern.factory.entity.HpMouse;
import demo.pattern.factory.entity.KeyBoard;
import demo.pattern.factory.entity.Mouse;

public class HpComputerFactory implements MouseKeyBoardFactory {

    @Override
    public Mouse createMouse() {

        return new HpMouse();
    }

    @Override
    public KeyBoard createKeyBoard() {
        return new HpKeyBoard();
    }

}
