package chapter_1.section_4;

import chapter_0.OverflowException;
import chapter_0.UnderflowException;

// some concrete implementation AbstractTank
public class ConcreteTank extends AbstractTank {

    protected final float capacity;
    protected float volume;

    public ConcreteTank(float capacity, float volume) {
        this.capacity = capacity;
        this.volume = volume;
    }

    @Override
    public float getCapacity() {
        return capacity;
    }

    @Override
    public float getVolume() {
        return volume;
    }

    @Override
    protected void doTransferWater(float amount) throws OverflowException, UnderflowException {
        // ... implementation code ...
    }

}
