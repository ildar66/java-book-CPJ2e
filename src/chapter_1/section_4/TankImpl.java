package chapter_1.section_4;

import common.OverflowException;
import common.UnderflowException;

/**
 * Created by User on 25.09.2016.
 * some Tank implementation
 */
class TankImpl implements Tank {

    @Override
    public float getCapacity() {
        return 1.0f;
    }

    @Override
    public float getVolume() {
        return 1.0f;
    }

    @Override
    public void transferWater(float amount)
            throws OverflowException, UnderflowException {
        //...
    }
}
