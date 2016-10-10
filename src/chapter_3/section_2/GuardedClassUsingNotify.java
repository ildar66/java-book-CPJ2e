package chapter_3.section_2;

/*
// Single notifications
However, in some other classes, you can reduce the context-switch overhead associated with
    notifications by using a single NOTIFY rather than notifyAll. Single notifications can be used
    to improve performance when you are sure that at most one thread needs to be woken.
    This applies when:
    • All possible waiting threads are necessarily waiting for conditions relying on the same
        notifications, usually the exact same condition.
    • Each notification intrinsically enables at most a single thread to continue. Thus it would be
        useless to wake up others.
    • You can accommodate uncertainties surrounding the possibility that an interrupt and a
        notify may occur at about the same time. In this case, the one thread that was notified is
        about to terminate. You might want another thread to receive notification instead, but this is
        not automatically arranged. (The issue does not arise with notifyAll since all threads are
        woken.)
*/
class GuardedClassUsingNotify {

    protected boolean cond = false;
    protected int nWaiting = 0; // count waiting threads

    protected synchronized void awaitCond() throws InterruptedException {
        while (!cond) {
            ++nWaiting; // record fact that a thread is waiting
            try {
                wait();
            } catch (InterruptedException ie) {
                //The odd-looking catch clause seen here ensures that
                // if a cancelled thread receives a notify, it relays
                //that notification to some other waiting thread (if one exists).
                notify();
                throw ie;
            } finally {
                --nWaiting; // no longer waiting
            }
        }
    }

    protected synchronized void signalCond() {
        if (cond) { // simulate notifyAll
            for (int i = nWaiting; i > 0; --i) {
                notify();
            }
        }
    }

}
