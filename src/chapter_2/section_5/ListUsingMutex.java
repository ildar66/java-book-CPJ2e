package chapter_2.section_5;

import EDU.oswego.cs.dl.util.concurrent.Mutex;

/*
// Non-block-structured locking para
A Mutex may be used in constructions that cannot be expressed using synchronized blocks
        because the acquire/release pairs do not occur in the same method or code block.
*/
class ListUsingMutex {

    static class Node {

        Object item;
        Node next;
        Mutex lock = new Mutex(); // each node keeps its own lock

        Node(Object x, Node n) {
            item = x;
            next = n;
        }
    }

    protected Node head; // pointer to first node of list

    // Use plain synchronization to protect head field.
    // (We could instead use a Mutex here too but there is no
    // reason to do so.)

    protected synchronized Node getHead() {
        return head;
    }

    public synchronized void add(Object x) { // simple prepend

        // for simplicity here, do not allow null elements
        if (x == null)
            throw new IllegalArgumentException();

        // The use of synchronized here protects only head field.
        // The method does not need to wait out other traversers
        // who have already made it past head node.

        head = new Node(x, head);
    }

    boolean search(Object x) throws InterruptedException {
        Node p = getHead();

        if (p == null || x == null)
            return false;

        p.lock.acquire(); // Prime loop by acquiring first lock.

        // If above acquire fails due to interrupt, the method will
        // throw InterruptedException now, so there is no need for
        // further cleanup.

        for (; ; ) {
            Node nextp = null;
            boolean found;

            try {
                found = x.equals(p.item);
                if (!found) {
                    nextp = p.next;
                    if (nextp != null) {
                        try { // Acquire next lock
                            // while still holding current
                            nextp.lock.acquire();
                        } catch (InterruptedException ie) {
                            throw ie; // Note that finally clause will
                            // execute before the throw
                        }
                    }
                }
            } finally {
                p.lock.release();
            }

            if (found)
                return true;
            else if (nextp == null)
                return false;
            else
                p = nextp;
        }
    }

    // ... other similar traversal and update methods ...

}
