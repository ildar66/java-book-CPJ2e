package chapter_4.section_1;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// Triggering

/**
 * When the events driving each event-driven task are relatively infrequent, a large number of tasks can
 * be processed by a small number of worker threads. The simplest case occurs when the number of
 * worker threads is exactly one. Here, the worker thread repeatedly polls a list of open sockets to see if
 * they have any input available (via InputStream.available) or have encountered other IOrelated
 * status changes. If so, the worker executes the associated run method.
 * This style of worker thread differs from the ones in § 4.1.4.1 in that, rather than pulling tasks from a
 * blocking queue and blindly running them, the worker must repeatedly check a list of registered tasks
 * to see if any can be run. It removes each task from the list only when it claims to have completed.
 * One generic form is:
 */
public class PollingWorker implements Runnable {    // Incomplete

    private List tasks = new LinkedList(); // ...;
    private long sleepTime = 100; // ...;

    void register(IOEventTask t) {
        tasks.add(t);
    }

    void deregister(IOEventTask t) {
        tasks.remove(t);
    }

    public void run() {
        try {
            for (; ; ) {
                for (Iterator it = tasks.iterator(); it.hasNext(); ) {
                    IOEventTask t = (IOEventTask) (it.next());
                    if (t.done())
                        deregister(t);
                    else {
                        boolean trigger;
                        try {
                            trigger = (t.input().available() > 0);
                        } catch (IOException ex) {
                            trigger = true; // trigger if exception on check
                        }
                        if (trigger)
                            t.run();
                    }
                }
                Thread.sleep(sleepTime);
            }
        } catch (InterruptedException ie) {
        }
    }

}
