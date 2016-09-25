package chapter_1.section_4;

import chapter_0.OverflowException;
import chapter_0.UnderflowException;

// Before/after techniques may be applied to check invariants around the transferWater operation.
public interface Tank {

    float getCapacity();

    float getVolume();

    void transferWater(float amount) throws OverflowException, UnderflowException;
}
