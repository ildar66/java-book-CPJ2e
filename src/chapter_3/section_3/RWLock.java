package chapter_3.section_3;

import EDU.oswego.cs.dl.util.concurrent.ReadWriteLock;
import EDU.oswego.cs.dl.util.concurrent.Sync;

/**
 * // Subclassing. Readers and Writers
 * This class or its subclasses may also be repackaged to support the ReadWriteLock interface
 * discussed in § 2.5.2. This can be done using inner classes. (A similar strategy is used by the
 * common.concurrent versions of ReadWriteLock, which also include some optimizations
 * discussed in § 3.7 to minimize unnecessary notifications.) For example:
 */
class RWLock extends ReadWrite implements ReadWriteLock { // Incomplete

    class RLock implements Sync {

        public void acquire() throws InterruptedException {
            beforeRead();
        }

        public void release() {
            afterRead();
        }

        public boolean attempt(long msecs) throws InterruptedException {
            return beforeRead(msecs);
        }
    }

    class WLock implements Sync {

        public void acquire() throws InterruptedException {
            beforeWrite();
        }

        public void release() {
            afterWrite();
        }

        public boolean attempt(long msecs) throws InterruptedException {
            return beforeWrite(msecs);
        }
    }

    protected final RLock rlock = new RLock();
    protected final WLock wlock = new WLock();

    public Sync readLock() {
        return rlock;
    }

    public Sync writeLock() {
        return wlock;
    }

    public boolean beforeRead(long msecs) throws InterruptedException {
        return true;
        // ... time-out version of beforeRead ...
    }

    public boolean beforeWrite(long msecs) throws InterruptedException {
        return true;
        // ... time-out version of beforeWrite ...
    }

    protected void doRead() {
    }

    protected void doWrite() {
    }

}
