package chapter_1.section_4;

import common.OverflowException;
import common.UnderflowException;

/**
 * Created by User on 25.09.2016.
 * In the normal case, when the intercepted before/after versions of methods have the same names and
 * usages as base versions, subclassing can be a simpler alternative to the use of Adapters.
 */

class SubclassedTank extends TankImpl {

    protected void checkVolumeInvariant() throws AssertionError {
        // ... identical to AdaptedTank version ...
    }

    public synchronized void transferWater(float amount) throws OverflowException, UnderflowException {
        // identical to AdaptedTank version except for inner call:
        checkVolumeInvariant();
        try {
            super.transferWater(amount);
        } catch (OverflowException ex) {
            throw ex;
        } catch (UnderflowException ex) {
            throw ex;
        } finally {
            checkVolumeInvariant();
        }
    }
}
