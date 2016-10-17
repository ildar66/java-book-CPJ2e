package chapter_1.section_4;

import common.OverflowException;
import common.UnderflowException;

// Adapters can be used to provide before/after control merely by wrapping the delegated call within the control actions.
class AdaptedTank implements Tank {

    protected final Tank delegate;

    public AdaptedTank(Tank t) {
        delegate = t;
    }

    @Override
    public float getCapacity() {
        return delegate.getCapacity();
    }

    @Override
    public float getVolume() {
        return delegate.getVolume();
    }

    protected void checkVolumeInvariant() throws AssertionError {
        float v = getVolume();
        float c = getCapacity();
        if (!(v >= 0.0 && v <= c)) {
            throw new AssertionError();
        }
    }

    @Override
    public synchronized void transferWater(float amount) throws OverflowException, UnderflowException {
        checkVolumeInvariant();

        try {
            delegate.transferWater(amount);
        } catch (OverflowException ex) {
            throw ex;
        } catch (UnderflowException ex) {
            throw ex;
        } finally {
            checkVolumeInvariant();
        }
    }

}
