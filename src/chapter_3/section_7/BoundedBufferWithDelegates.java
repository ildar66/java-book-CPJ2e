package chapter_3.section_7;

/**
 * // Delegated actions. Bounded buffers.
 * As a further performance enhancement, notifications here use notify, since the conditions for its
 * use (discussed in § 3.2.4.2) are met: (1) Each waiting task in each helper is waiting on the same
 * logical condition (non-emptiness for take, and non-fullness for put). (2) Each notification enables
 * at most a single thread to continue — each put enables one take, and each take enables one
 * put. (3) We can re-notify to deal with interruptions.
 * And to squeeze another bit of efficiency out of this, it is simple here to (conservatively) track whether
 * there are any waiting threads, and issue notify only if there can be threads that need notifying. The
 * performance effect of this tactic varies across JVM implementations. As notify operations become
 * increasingly cheap, the minor bookkeeping overhead here to avoid calls becomes decreasingly
 * worthwhile.
 */
final class BoundedBufferWithDelegates {

    private Object[] array;
    private Exchanger putter;
    private Exchanger taker;

    public BoundedBufferWithDelegates(int capacity) throws IllegalArgumentException {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        array = new Object[capacity];
        putter = new Exchanger(capacity);
        taker = new Exchanger(0);
    }

    public void put(Object x) throws InterruptedException {
        putter.exchange(x);
    }

    public Object take() throws InterruptedException {
        return taker.exchange(null);
    }

    void removedSlotNotification(Exchanger h) { // relay
        if (h == putter)
            taker.addedSlotNotification();
        else
            putter.addedSlotNotification();
    }

    protected class Exchanger { // Inner class

        protected int ptr = 0; // circular index
        protected int slots; // number of usable slots
        protected int waiting = 0; // number of waiting threads

        Exchanger(int n) {
            slots = n;
        }

        synchronized void addedSlotNotification() {
            ++slots;
            if (waiting > 0) // unblock a single waiting thread
                notify();
        }

        Object exchange(Object x) throws InterruptedException {
            Object old = null; // return value

            synchronized (this) {
                while (slots <= 0) { // wait for slot
                    ++waiting;
                    try {
                        wait();
                    } catch (InterruptedException ie) {
                        notify();
                        throw ie;
                    } finally {
                        --waiting;
                    }
                }

                --slots; // use slot
                old = array[ptr];
                array[ptr] = x;
                ptr = (ptr + 1) % array.length; // advance position
            }

            removedSlotNotification(this); // notify of change
            return old;
        }
    }

}
