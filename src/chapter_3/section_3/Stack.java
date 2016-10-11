package chapter_3.section_3;

// Layering Guards
class Stack { // Fragments

    public synchronized boolean isEmpty() {
        return false; /* ... */
    }

    public synchronized void push(Object x) { /* ... */
    }

    public synchronized Object pop() throws StackEmptyException {
        if (isEmpty())
            throw new StackEmptyException();
        else
            return null;
    }
}
