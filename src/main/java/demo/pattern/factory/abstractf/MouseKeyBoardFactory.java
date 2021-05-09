package demo.pattern.factory.abstractf;

import demo.pattern.factory.entity.KeyBoard;
import demo.pattern.factory.entity.Mouse;

public interface MouseKeyBoardFactory {
    Mouse createMouse();

    KeyBoard createKeyBoard();

}
