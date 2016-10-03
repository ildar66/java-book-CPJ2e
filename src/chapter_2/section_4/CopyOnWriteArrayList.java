package chapter_2.section_4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
// Internal copy-on-write para
While there are other good choices for the underlying data structure (including the special-purpose
        tree-based structure used in java.awt.EventMulticaster, and more elaborate structures
        maintaining edit-records from a common base), an array-based copy-on-write collection class is
        suitable for most applications. Traversal via iterators is not only fast but also avoids
        ConcurrentModificationExceptions that can occur in some other approaches to traversal

This class would be horribly inefficient if used in contexts involving frequent modifications of large
collections, but it is well suited for most multicast applications
*/
class CopyOnWriteArrayList { // Incomplete

    protected Object[] array = new Object[0];

    protected synchronized Object[] getArray() {
        return array;
    }

    public synchronized void add(Object element) {
        int len = array.length;
        Object[] newArray = new Object[len + 1];
        System.arraycopy(array, 0, newArray, 0, len);
        newArray[len] = element;
        array = newArray;
    }

    public Iterator<Object> iterator() {
        return new Iterator<Object>() {

            protected final Object[] snapshot = getArray();
            protected int cursor = 0;

            public boolean hasNext() {
                return cursor < snapshot.length;
            }

            public Object next() {
                try {
                    return snapshot[cursor++];
                } catch (IndexOutOfBoundsException ex) {
                    throw new NoSuchElementException();
                }
            }

            public void remove() {
            }

        };
    }

}
