package chapter_4.section_1;

import java.util.Date;

// Timers

/**
 * Time-based daemons can be constructed as variants of the basic worker thread design described in §
 * 4.1.4.1. For example, here are the highlights of a version that relies on an unshown priority queue
 * class (that might take a form similar to the scheduling queue illustrated in § 4.3.4) and is set up to
 * support only one worker thread:
 */
class TimerDaemon {
    // Fragments

    static class TimerTask implements Comparable {

        final Runnable command;
        final long execTime; // time to run at

        public int compareTo(Object x) {
            long otherExecTime = ((TimerTask) (x)).execTime;
            return (execTime < otherExecTime) ? -1 : (execTime == otherExecTime) ? 0 : 1;
        }

        TimerTask(Runnable r, long t) {
            command = r;
            execTime = t;
        }
    }

    // a heap or list with methods that preserve
    // ordering with respect to TimerTask.compareTo

    static class PriorityQueue {

        void put(TimerTask t) {
        }

        TimerTask least() {
            return null;
        }

        void removeLeast() {
        }

        boolean isEmpty() {
            return true;
        }
    }

    protected final PriorityQueue pq = new PriorityQueue();

    public synchronized void executeAfterDelay(Runnable r, long t) {
        pq.put(new TimerTask(r, t + System.currentTimeMillis()));
        notifyAll();
    }

    public synchronized void executeAt(Runnable r, Date time) {
        pq.put(new TimerTask(r, time.getTime()));
        notifyAll();
    }

    // wait for and then return next task to run
    protected synchronized Runnable take() throws InterruptedException {
        for (; ; ) {
            while (pq.isEmpty())
                wait();
            TimerTask t = pq.least();
            long now = System.currentTimeMillis();
            long waitTime = now - t.execTime;
            if (waitTime <= 0) {
                pq.removeLeast();
                return t.command;
            } else
                wait(waitTime);
        }
    }

    public TimerDaemon() {
        activate();
    } // only one

    void activate() {
        // same as PlainWorkerThread except using above take method
    }

}
