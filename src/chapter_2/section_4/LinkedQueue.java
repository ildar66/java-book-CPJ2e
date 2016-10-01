package chapter_2.section_4;

// Linked data structures para

/*
Lock-splitting techniques can minimize access contention to objects serving as entry points into linked
   data structures, by finding a middle ground between the extreme strategies of fully synchronizing the
   entry classes (which can limit concurrency) and fully synchronizing all the linked node objects being
   controlled (which can be inefficient and can lead to liveness problems).

    There are no universally applicable recipes for splitting synchronization in classes
    controlling access to linked structures, but the following class illustrates some common tactics.
*/
public class LinkedQueue {

    protected Node head = new Node(null);
    protected Node last = head;

    protected final Object pollLock = new Object();
    protected final Object putLock = new Object();

    public void put(Object x) {
        Node node = new Node(x);
        synchronized (putLock) { // insert at end of list
            synchronized (last) {
                last.next = node; // extend list
                last = node;
            }
        }
    }

    public Object poll() { // returns null if empty
        synchronized (pollLock) {
            synchronized (head) {
                Object x = null;
                Node first = head.next; // get to first real node
                if (first != null) {
                    x = first.object;
                    first.object = null; // forget old object
                    head = first; // first becomes new head
                }
                return x;
            }
        }
    }

    static class Node { // local node class for queue

        Object object;
        Node next = null;

        Node(Object x) {
            object = x;
        }
    }

}
