package chapter_3.section_7;

/**
 * // Delegated actions. Collapsing classes
 * Synchronization splitting of all kinds can be accomplished in two ways. In the case of lock-splitting (§
 * 2.4.2), you can either create new helper classes and forward operations from the host, or you can just
 * keep the methods in the host but invoke them under synchronization of Objects that conceptually
 * represent the different helpers.
 * The same principle holds when splitting state-dependent actions. Rather than delegating actions to
 * helpers, you can keep the methods in the host class, adding Objects that conceptually represent the
 * helpers. Objects used solely for synchronization serve as locks. Those used for waiting and
 * notification serve as monitors — places to put threads that need to wait and be notified.
 * Combining helpers into a host class makes the host class more complex but also potentially more
 * efficient, due to short-circuited method calls and the like. Performing such simplifications along the
 * way, we can define a more concise, slightly more efficient, and surely more frightening version of
 * BoundedBuffer:
 */
public class BoundedBufferWithMonitorObjects {

    private final Object[] array; // the elements

    private int putPtr = 0; // circular indices
    private int takePtr = 0;

    private int emptySlots; // slot counts
    private int usedSlots = 0;

    private int waitingPuts = 0; // counts of waiting threads
    private int waitingTakes = 0;

    private final Object putMonitor = new Object();
    private final Object takeMonitor = new Object();

    public BoundedBufferWithMonitorObjects(int capacity) throws IllegalArgumentException {
        if (capacity <= 0)
            throw new IllegalArgumentException();

        array = new Object[capacity];
        emptySlots = capacity;
    }

    public void put(Object x) throws InterruptedException {
        synchronized (putMonitor) {
            while (emptySlots <= 0) {
                ++waitingPuts;
                try {
                    putMonitor.wait();
                } catch (InterruptedException ie) {
                    putMonitor.notify();
                    throw ie;
                } finally {
                    --waitingPuts;
                }
            }
            --emptySlots;
            array[putPtr] = x;
            putPtr = (putPtr + 1) % array.length;
        }
        synchronized (takeMonitor) { // directly notify
            ++usedSlots;
            if (waitingTakes > 0)
                takeMonitor.notify();
        }
    }

    public Object take() throws InterruptedException {
        Object old = null;
        synchronized (takeMonitor) {
            while (usedSlots <= 0) {
                ++waitingTakes;
                try {
                    takeMonitor.wait();
                } catch (InterruptedException ie) {
                    takeMonitor.notify();
                    throw ie;
                } finally {
                    --waitingTakes;
                }
            }
            --usedSlots;
            old = array[takePtr];
            array[takePtr] = null;
            takePtr = (takePtr + 1) % array.length;
        }
        synchronized (putMonitor) {
            ++emptySlots;
            if (waitingPuts > 0)
                putMonitor.notify();
        }
        return old;
    }

}
