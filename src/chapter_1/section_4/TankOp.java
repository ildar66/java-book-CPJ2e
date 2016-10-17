package chapter_1.section_4;

import common.OverflowException;
import common.UnderflowException;

// use in MethodAdapter
interface TankOp {

    void op() throws OverflowException, UnderflowException;

}
