package chapter_3.section_3;

// Confinement and Nested Monitors

/**
 * @see Host
 */
class PartWithGuard {

    protected boolean cond = false;

    synchronized void await() throws InterruptedException {
        while (!cond)
            wait();
        // any other code
    }

    synchronized void signal(boolean c) {
        cond = c;
        notifyAll();
    }

}
