package chapter_3.section_2;

/*
// Notifications
*
The simplest way to arrange that blocked threads eventually recheck conditions is to insert
  notifyAll in methods that cause relevant state changes. In turn, the simplest way to do this is to
  define utility methods that encapsulate assignment, issuing a notification upon any change in value.
For example,here is a first pass at BoundedCounter:

    NOTE that within synchronized methods, the ORDER in which a "notifyAll" is placed does not matter.
    No awakened threads will be able to continue until the synchronization lock is released.
    Just as a matter of style, most people put notifications last in method bodies.
*/

public class SimpleBoundedCounter {

    static final long MIN = 0; // minimum allowed value

    static final long MAX = 10; // maximum allowed value

    protected long count = MIN;

    public synchronized long count() {
        return count;
    }

    public synchronized void inc() throws InterruptedException {
        awaitUnderMax();
        setCount(count + 1);
    }

    public synchronized void dec() throws InterruptedException {
        awaitOverMin();
        setCount(count - 1);
    }

    protected void setCount(long newValue) { // PRE: lock held
        count = newValue;
        notifyAll(); // wake up any thread depending on new value
    }

    protected void awaitUnderMax() throws InterruptedException {
        while (count == MAX)
            wait();
    }

    protected void awaitOverMin() throws InterruptedException {
        while (count == MIN)
            wait();
    }

}
