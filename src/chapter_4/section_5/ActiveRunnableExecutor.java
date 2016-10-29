package chapter_4.section_5;

import EDU.oswego.cs.dl.util.concurrent.Channel;

/**
 * //Further, the use of Runnables as messages leads to a boring but universal (at least in some senses)
 * form of active object: a minor variant of a common worker thread design that also conforms to the
 * initial abstract characterization of active objects as interpreters in § 1.2.4
 */
class ActiveRunnableExecutor extends Thread {

    Channel me = null; // ... // used for all incoming messages

    public void run() {
        try {
            for (; ; ) {
                ((Runnable) (me.take())).run();
            }
        } catch (InterruptedException ie) {
        } // die
    }
}
