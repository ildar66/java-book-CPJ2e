package chapter_3.section_7;

// Specific Notifications. FIFOSemaphore
/*
Design steps
 The main design steps are specializations of those described in § 3.7.2.1. Create or modify a class, say
 Host, as follows:
 • For each thread or set of threads that needs specific notification, create an object serving as a
        monitor. These monitors may be arranged in arrays or other collections, or dynamically
        created during execution.
 • Set up bookkeeping in the classes serving as monitors to manage waiting and notification
        operations and their interactions with time-out and interruption policies. As shown in the
        WaitNode class in § 3.7.3.2, this usually entails maintaining a released field to
        remember if a waiting thread has been released due to notification, interruption, or time-out.
        These classes may then support methods, say doWait, doTimedWait, doNotify, and
        doNotifyAll, that perform reliable waiting and notification and deal with interrupts and
        time-outs in the desired fashion. If you cannot add bookkeeping to the classes serving as
        monitors, then these matters need to be addressed in the Host class methods.
 • In each Host method in which tasks are to be suspended, use monitor.doWait() with
        the appropriate monitor object. This code must avoid nested monitor problems by ensuring
        that the wait is performed within code regions that are not synchronized on the host object.
                The simplest and most desirable form is:
             • boolean needToWait; // to remember value after synch exit
             • synchronized (this) {
             • needToWait = ...;
             • if (needToWait)
             • enqueue(monitor); // or any similar bookkeeping
             • }
             • if (needToWait) monitor.doWait();
  • In each method in which tasks are to be resumed, use monitor.doNotify(), also
        handling the consequences of time-out or interruption.
*/
public class FIFOSemaphore extends Semaphore {

    protected final WaitQueue queue = new WaitQueue();

    public FIFOSemaphore(long initialPermits) {
        super(initialPermits);
    }

    public void acquire() throws InterruptedException {
        if (Thread.interrupted())
            throw new InterruptedException();

        WaitNode node = null;

        synchronized (this) {
            if (permits > 0) { // no need to queue
                --permits;
                return;
            } else {
                node = new WaitNode();
                queue.enq(node);
            }
        }

        // must release lock before node wait

        node.doWait();

    }

    public synchronized void release() {
        for (; ; ) { // retry until success
            WaitNode node = queue.deq();

            if (node == null) { // queue is empty
                ++permits;
                return;
            } else if (node.doNotify())
                return;

            // else node was already released due to
            // interruption or time-out, so must retry
        }
    }

    // Queue node class. Each node serves as a monitor.

    protected static class WaitNode {

        boolean released = false;
        WaitNode next = null;

        synchronized void doWait() throws InterruptedException {
            try {
                while (!released)
                    wait();
            } catch (InterruptedException ie) {

                if (!released) { // Interrupted before notified
                    // Suppress future notifications:
                    released = true;
                    throw ie;
                } else { // Interrupted after notified
                    // Ignore exception but propagate status:
                    Thread.currentThread().interrupt();
                }

            }
        }

        synchronized boolean doNotify() { // return true if notified

            if (released) // was interrupted or timed out
                return false;
            else {
                released = true;
                notify();
                return true;
            }
        }

        synchronized boolean doTimedWait(long msecs) throws InterruptedException {
            return true;
            // similar
        }
    }

    // Standard linked queue class.
    // Used only when holding Semaphore lock.

    protected static class WaitQueue {

        protected WaitNode head = null;
        protected WaitNode last = null;

        protected void enq(WaitNode node) {
            if (last == null)
                head = last = node;
            else {
                last.next = node;
                last = node;
            }
        }

        protected WaitNode deq() {
            WaitNode node = head;
            if (node != null) {
                head = node.next;
                if (head == null)
                    last = null;
                node.next = null;
            }
            return node;
        }
    }

}
