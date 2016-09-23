package chapter_1.section_2;

import chapter_0.OverflowException;
import chapter_0.UnderflowException;

/**
 * Code sketch
 */
class WaterTank {

    final float capacity;
    float currentVolume = 0.0f;
    WaterTank overflow;

    WaterTank(float cap) {
        capacity = cap;
        //...
    }

    void addWater(float amount) throws OverflowException {}

    void removeWater(float amount) throws UnderflowException {}
}
