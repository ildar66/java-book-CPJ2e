package chapter_2.section_2;

// Deadlock
//Although fully synchronized atomic objects are always safe, threads using them are not always live.
class Cell {// Do not use

    private long value;

    synchronized long getValue() {
        return value;
    }

    synchronized void setValue(long v) {
        value = v;
    }

    synchronized void swapValue(Cell other) {
        long t = getValue();
        long v = other.getValue();
        setValue(v);
        other.setValue(t);
    }

   /* More generally, deadlock is possible when two or more objects are mutually accessible from two or
    more threads, and each thread holds one lock while trying to obtain another lock already held by
    another thread.*/

}
