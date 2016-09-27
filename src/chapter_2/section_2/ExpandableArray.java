package chapter_2.section_2;

import java.util.NoSuchElementException;

/*
    The safest (but not always the best) concurrent OO design strategy based on locking is to restrict
        attention to fully synchronized objects (also known as atomic objects)
*/
class ExpandableArray {

    protected Object[] data;
    protected int size = 0;
    // INV: 0 <= size <= data.length

    public ExpandableArray(int cap) {
        data = new Object[cap];
    }

    public synchronized int size() {
        return size;
    }

    public synchronized Object get(int i) throws NoSuchElementException {
        if (i < 0 || i >= size) {
            throw new NoSuchElementException();
        }
        return data[i];
    }

    public synchronized void add(Object x) { // add at end
        if (size == data.length) { // need a bigger array
            Object[] olddata = data;
            data = new Object[3 * (size + 1) / 2];
            System.arraycopy(olddata, 0, data, 0, olddata.length);
        }
        data[size++] = x;
    }

    public synchronized void removeLast() throws NoSuchElementException {
        if (size == 0)
            throw new NoSuchElementException();

        data[--size] = null;
    }

}
