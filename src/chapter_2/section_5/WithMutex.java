package chapter_2.section_5;

import EDU.oswego.cs.dl.util.concurrent.Mutex;

/**
 * Method adapters para:
 *
 * Better structure and discipline surrounding locks can be arranged via any of the before/after patterns
 * discussed in § 1.4. For example, the use of method adapters supports definition of generic wrappers
 * that can run any code within any lock. A wrapper can be defined either as method of a class using
 * locking or as a separate utility class.
 * An example of the latter is: see {@link ParticleUsingWrapper}
 */
public class WithMutex {

    private final Mutex mutex;

    public WithMutex(Mutex m) {
        mutex = m;
    }

    public void perform(Runnable r) throws InterruptedException {
        mutex.acquire();
        try {
            r.run();
        } finally {
            mutex.release();
        }
    }

}
