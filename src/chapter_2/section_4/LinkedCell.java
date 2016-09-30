package chapter_2.section_4;

/**
 * For example, consider a LinkedCell class in which each cell contains a reference
 * to a successor cell, and for which we require that successor cell references be fixed upon construction.
 * This is a common requirement for cells serving as Lisp-style lists. Methods and sections of methods
 * solely involving the successor need not be synchronized, which speeds up traversal. For clarity and
 * emphasis, the methods here use recursion; in practice you would probably use iteration instead:
 */
public class LinkedCell {

    protected int value;
    protected final LinkedCell next;

    public LinkedCell(int v, LinkedCell t) {
        value = v;
        next = t;
    }

    public synchronized int value() {
        return value;
    }

    public synchronized void setValue(int v) {
        value = v;
    }

    public int sum() { // add up all element values
        return (next == null) ? value() : value() + next.sum();
    }

    public boolean includes(int x) { // search for x
        return (value() == x) ? true : (next == null) ? false : next.includes(x);
    }
/*
    //Again note that an object remains locked when a synchronized method calls an unsynchronized
    //one. So it would not avoid synchronization to write sum as:

    synchronized int ineffectivelyUnsynchedSum() { // bad idea
        return value + nextSum(); // synch still held on call
    }

    int nextSum() {
        return (next == null) ? 0 : next.sum();
    }
*/

}
