package chapter_3.section_3;

import java.util.Hashtable;

// Conflict Sets. Implementation
/*
One policy choice is:
        • A retrieve operation should not run concurrently with a store operation since the
        store might be in the process of adding exactly the item requested, in which case you don't
        want to return a failure indication.
        • Two or more retrieve operations should not execute at the same time, since one may be
        in the process of removing the item requested by the others.
*/
public class Inventory {

    protected final Hashtable<String, Object> items = new Hashtable<>();
    protected final Hashtable<String, String> suppliers = new Hashtable<>();

    // execution state tracking variables:
    protected int storing = 0; // number of in-progress stores
    protected int retrieving = 0; // number of retrieves

    // ground actions:
    protected void doStore(String description, Object item, String supplier) {
        items.put(description, item);
        suppliers.put(supplier, description);
    }

    protected Object doRetrieve(String description) {
        Object x = items.get(description);
        if (x != null)
            items.remove(description);
        return x;
    }

    public void store(String description, Object item, String supplier) throws InterruptedException {

        synchronized (this) { // Before-action
            while (retrieving != 0)
                // don't overlap with retrieves
                wait();
            ++storing; // record exec state
        }

        try {
            doStore(description, item, supplier); // Ground action
        } finally { // After-action
            synchronized (this) { // signal retrieves
                if (--storing == 0) // only necessary when hit zero
                    notifyAll();
            }
        }
    }

    public Object retrieve(String description) throws InterruptedException {

        synchronized (this) { // Before-action
            // wait until no stores or retrieves
            while (storing != 0 || retrieving != 0)
                wait();
            ++retrieving;
        }

        try {
            return doRetrieve(description); // ground action
        } finally {
            synchronized (this) { // After-action
                if (--retrieving == 0)
                    notifyAll();
            }
        }
    }

}
