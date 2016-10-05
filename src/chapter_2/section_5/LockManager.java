package chapter_2.section_5;

import EDU.oswego.cs.dl.util.concurrent.Sync;

// Lock Ordering Managers para
public class LockManager {

    // Code sketch
    // ...
    protected void sortLocks(Sync[] locks) { /* ... */
    }

    public void runWithinLocks(Runnable op, Sync[] locks) throws InterruptedException {
        sortLocks(locks);
        // for help in recovering from exceptions
        int lastLocked = -1;
        InterruptedException caught = null;
        try {
            for (int i = 0; i < locks.length; ++i) {
                locks[i].acquire();
                lastLocked = i;
            }
            op.run();
        } catch (InterruptedException ie) {
            caught = ie;
        } finally {
            for (int j = lastLocked; j >= 0; --j)
                locks[j].release();
            if (caught != null)
                throw caught;
        }
    }
}
