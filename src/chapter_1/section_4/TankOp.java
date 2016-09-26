package chapter_1.section_4;

import chapter_0.OverflowException;
import chapter_0.UnderflowException;

// use in MethodAdapter
interface TankOp {

    void op() throws OverflowException, UnderflowException;

}
