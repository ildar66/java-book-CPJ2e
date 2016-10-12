package chapter_3.section_4;

import EDU.oswego.cs.dl.util.concurrent.Semaphore;

// Bounded buffers

/**
 * The BoundedBufferWithSemaphores class is likely to run more efficiently than the
 * BoundedBufferWithStateTracking class in § 3.3.1 when there are many threads all
 * using the buffer. BoundedBufferWithSemaphores relies on two different underlying wait
 * sets. The  {@link chapter_3.section_3.BoundedBufferWithStateTracking} class gets by with only one, so any emptyto-
 * partial or full-to-partial state transition causes all waiting threads to wake up, including those
 * waiting for the other logical condition and those that will immediately rewait because some other
 * thread took the only item or filled the only available slot.
 * The BoundedBufferWithSemaphores class isolates the monitors for these two conditions.
 * This can be exploited by the underlying Semaphore implementation (see § 3.7.1) to eliminate
 * unnecessary context switching by using notify instead of notifyAll. This reduces the worstcase
 * number of wakeups from being a quadratic function of the number of invocations to being linear.
 * More generally, whenever you can isolate a condition using a semaphore, you can usually improve
 * performance as compared to notifyAll-based solutions.
 */
public class BoundedBufferWithSemaphores {

    protected final BufferArray buff;
    protected final Semaphore putPermits;
    protected final Semaphore takePermits;

    public BoundedBufferWithSemaphores(int capacity) throws IllegalArgumentException {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        buff = new BufferArray(capacity);
        putPermits = new Semaphore(capacity);
        takePermits = new Semaphore(0);
    }

    public void put(Object x) throws InterruptedException {
        putPermits.acquire();
        buff.insert(x);
        takePermits.release();
    }

    public Object take() throws InterruptedException {
        takePermits.acquire();
        Object x = buff.extract();
        putPermits.release();
        return x;
    }

    public Object poll(long msecs) throws InterruptedException {
        if (!takePermits.attempt(msecs))
            return null;
        Object x = buff.extract();
        putPermits.release();
        return x;
    }

    public boolean offer(Object x, long msecs) throws InterruptedException {
        if (!putPermits.attempt(msecs))
            return false;
        buff.insert(x);
        takePermits.release();
        return true;
    }

}
