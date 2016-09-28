package chapter_2.section_2;

/*
    Recover from deadlocks and other liveness failures motivates the use of other exclusion techniques:
    Resource ordering technique
*/
class Cell2 {    // Do not use

    private long value;

    synchronized long getValue() {
        return value;
    }

    synchronized void setValue(long v) {
        value = v;
    }

    public void swapValue(Cell2 other) {
        if (other == this) // alias check
            return;
        else if (System.identityHashCode(this) < System.identityHashCode(other))
            this.doSwapValue(other);
        else
            other.doSwapValue(this);
    }

    protected synchronized void doSwapValue(Cell2 other) {
        // same as original public version:
        long t = getValue();
        long v = other.getValue();
        setValue(v);
        other.setValue(t);
    }

    // slightly faster version
    protected synchronized void doSwapValueV2(Cell2 other) {
        synchronized (other) {
            long t = value;
            value = other.value;
            other.value = t;
        }
    }

}
