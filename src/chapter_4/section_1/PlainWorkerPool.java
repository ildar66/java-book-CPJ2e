package chapter_4.section_1;

import EDU.oswego.cs.dl.util.concurrent.Channel;

// Worker Threads Executor implementation

/*
 * Lightweight executable frameworks fill the gap between open calls and thread-per-message designs.
 * They apply when you need to introduce limited concurrency, at the expense of some usage
 * restrictions, in order to maximize (or at least improve) throughput and minimize average latencies.
 *
 * Entering a Runnable into a queue is likely to be faster than creating a new Thread object.
 * And because you can control the number of worker threads, you can minimize chances of resource
 * exhaustion and reduce contextswitching overhead.
 *
 * Explicit queuing also permits greater flexibility in tuning execution semantics.
 * For example, you can implement Channels as priority queues that order tasks with more deterministic
 * control than is guaranteed by Thread.setPriority.
 */
public class PlainWorkerPool implements Executor {

    protected final Channel workQueue;

    public void execute(Runnable r) {
        try {
            workQueue.put(r);
        } catch (InterruptedException ie) { // postpone response
            Thread.currentThread().interrupt();
        }
    }

    public PlainWorkerPool(Channel ch, int nworkers) {
        workQueue = ch;
        for (int i = 0; i < nworkers; ++i)
            activate();
    }

    protected void activate() {
        Runnable runLoop = new Runnable() {

            public void run() {
                try {
                    for (; ; ) {
                        Runnable r = (Runnable) (workQueue.take());
                        r.run();
                    }
                } catch (InterruptedException ie) {
                } // die
            }
        };
        new Thread(runLoop).start();
    }

}
