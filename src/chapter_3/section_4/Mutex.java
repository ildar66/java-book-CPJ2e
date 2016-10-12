package chapter_3.section_4;

import EDU.oswego.cs.dl.util.concurrent.Semaphore;
import EDU.oswego.cs.dl.util.concurrent.Sync;

/**
 * Created by User on 12.10.2016.
 * Mutual exclusion locks
 */
class Mutex implements Sync {

    private Semaphore s = new Semaphore(1);

    public void acquire() throws InterruptedException {
        s.acquire();
    }

    public void release() {
        s.release();
    }

    public boolean attempt(long ms) throws InterruptedException {
        return s.attempt(ms);
    }
}
