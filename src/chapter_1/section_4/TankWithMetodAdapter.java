package chapter_1.section_4;

import chapter_0.OverflowException;
import chapter_0.UnderflowException;

/*
 * Method adapters are much more common in applications where instances must
 * be registered and/or passed around among multiple objects before being executed
*/
class TankWithMetodAdapter {

    // ...
    protected void checkVolumeInvariant() throws java.lang.AssertionError {//or ? checkVolumeInvariant(TankOp cmd)
        // ... identical to AdaptedTank version ...
    }

    protected void runWithinBeforeAfterChecks(TankOp cmd) throws OverflowException, UnderflowException {
        // identical to AdaptedTank.transferWater
        // except for inner call:

        // ...
        //try {
        cmd.op();
        // ...
    }

    protected void doTransferWater(float amount) throws OverflowException, UnderflowException {
        // ... implementation code ...
    }

    public synchronized void transferWater(final float amount) throws OverflowException, UnderflowException {

        runWithinBeforeAfterChecks(new TankOp() {

            public void op() throws OverflowException, UnderflowException {
                doTransferWater(amount);
            }
        });
    }

}
