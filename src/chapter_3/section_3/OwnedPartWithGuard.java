package chapter_3.section_3;

/// Confinement and Nested Monitors

/**
 * In other cases, where Part methods must access locked Host state @see {@link Host}, you can redefine Part classes to use
 * an extended form of hierarchical containment locking employing the Host as the monitor.
 * For example
 */
class OwnedPartWithGuard { // Code sketch

    protected boolean cond = false;
    final Object lock;

    OwnedPartWithGuard(Object owner) {
        lock = owner;
    }

    void await() throws InterruptedException {
        synchronized (lock) {
            while (!cond)
                lock.wait();
            // ...
        }
    }

    void signal(boolean c) {
        synchronized (lock) {
            cond = c;
            lock.notifyAll();
        }
    }

}
