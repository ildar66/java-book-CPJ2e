package chapter_2.section_4;

// Splitting locks para

/**
 * @see Shape
 * The basic steps for splitting locks are similar to those for splitting objects:
 * • For each independent subset of functionality, declare a final object, say lock, initialized
 *   in the constructor for the Host class and never reassigned:
 *      o The lock object can be of any subclass of class Object. If it will not be used for
 *          any other purpose, it might as well be of class Object itself.
 *      o If a subset is uniquely associated with some existing object uniquely referenced from
 *          a field, you may use that object as the lock.
 *      o One of these locks can be associated with the Host object (this) itself.
 * • Declare all methods corresponding to each subset as unsynchronized, but surround all code
 *      with synchronized(lock) { ... }.
 */
 class LockSplitShape { // Incomplete

    protected double x = 0.0;
    protected double y = 0.0;
    protected double width = 0.0;
    protected double height = 0.0;

    protected final Object locationLock = new Object();
    protected final Object dimensionLock = new Object();

    public double x() {
        synchronized (locationLock) {
            return x;
        }
    }

    public double y() {
        synchronized (locationLock) {
            return y;
        }
    }

    public void adjustLocation() {
        synchronized (locationLock) {
            x = 1; // longCalculation1();
            y = 2; // longCalculation2();
        }
    }

    // and so on

}
