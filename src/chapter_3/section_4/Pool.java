package chapter_3.section_4;

import java.util.ArrayList;
import java.util.HashSet;

import EDU.oswego.cs.dl.util.concurrent.Semaphore;

// Resource pools

/**
 * Semaphores are specialized counters, and so are natural choices for concurrency control in many
 * classes involving counts. For example, pool classes of various kinds normally keep counts of resource
 * items (e.g., file descriptors, printers, buffers, large graphical objects) that clients can check out and
 * later check back in.
 */
public class Pool { // Incomplete

    protected ArrayList items = new ArrayList();
    protected HashSet busy = new HashSet();

    protected final Semaphore available;

    public Pool(int n) {
        available = new Semaphore(n);
        initializeItems(n);
    }

    public Object getItem() throws InterruptedException {
        available.acquire();
        return doGet();
    }

    public void returnItem(Object x) {
        if (doReturn(x))
            available.release();
    }

    protected synchronized Object doGet() {
        Object x = items.remove(items.size() - 1);
        busy.add(x); // put in set to check returns
        return x;
    }

    protected synchronized boolean doReturn(Object x) {
        if (busy.remove(x)) {
            items.add(x); // put back into available item list
            return true;
        } else
            return false;
    }

    protected void initializeItems(int n) {
        // Somehow create the resource objects
        // and place them in items list.
    }

}
