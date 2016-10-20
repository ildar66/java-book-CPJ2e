package chapter_4.section_1;

import common.Helper;

// Thread-Per-Message

/**
 * Concurrency can be introduced into oneway messaging designs by issuing a message in its own
 * thread, as in:
 */

/**
 * This strategy improves throughput when multiple parallel tasks can run faster than a sequence of them
 * could, normally because they are either IO-bound or are compute-bound and running on a
 * multiprocessor. It can also enhance fairness and improve availability if clients need not wait for each
 * other's tasks to complete. Decisions about whether to create and start threads to perform tasks are not too different from
 * decisions about whether to create other kinds of objects or send other kinds of messages: The benefits
 * must outweigh the costs.
 * Thread-per-message designs introduce response latency because thread creation is more expensive
 * than direct method invocation.
 */
public class ThreadPerMessageHost { // Generic code sketch

    protected long localState;
    protected final Helper helper = new Helper();

    protected synchronized void updateState() {
        localState = 2; // ...;
    }

    public void req() {
        updateState();
        new Thread(new Runnable() {

            public void run() {
                helper.handle();
            }
        }).start();
    }

}

