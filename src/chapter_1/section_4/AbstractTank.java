package chapter_1.section_4;

import chapter_0.OverflowException;
import chapter_0.UnderflowException;

// abstract class that automates the control sequence via an application of the Template Method pattern
abstract class AbstractTank implements Tank {

    protected void checkVolumeInvariant() throws AssertionError {
        // ... identical to AdaptedTank version ...
    }

    @Override
    public synchronized void transferWater(float amount) throws OverflowException, UnderflowException {
        // identical to AdaptedTank version except for inner call:
        checkVolumeInvariant();

        try {
            doTransferWater(amount);
        } catch (OverflowException ex) {
            throw ex;
        } catch (UnderflowException ex) {
            throw ex;
        } finally {
            checkVolumeInvariant();
        }
    }

    protected abstract void doTransferWater(float amount) throws OverflowException, UnderflowException;

}
