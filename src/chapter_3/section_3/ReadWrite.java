package chapter_3.section_3;

// Subclassing. Readers and Writers

/**
 * Count of waiting readers is not really necessary in this version, since no
 * policy depends on its value. However, its presence allows you to adjust policies by changing the
 * predicates in the "allowReader" and "allowWriter" methods to rely on them in some way. For
 * example, you might alter the conditionals to give preference to whichever count is greater.
 */
abstract class ReadWrite {

    protected int activeReaders = 0; // threads executing read
    protected int activeWriters = 0; // always zero or one

    protected int waitingReaders = 0; // threads not yet in read
    protected int waitingWriters = 0; // same for write

    protected abstract void doRead(); // implement in subclasses

    protected abstract void doWrite();

    public void read() throws InterruptedException {
        beforeRead();
        try {
            doRead();
        } finally {
            afterRead();
        }
    }

    public void write() throws InterruptedException {
        beforeWrite();
        try {
            doWrite();
        } finally {
            afterWrite();
        }
    }

    protected boolean allowReader() {
        return waitingWriters == 0 && activeWriters == 0;
    }

    protected boolean allowWriter() {
        return activeReaders == 0 && activeWriters == 0;
    }

    protected synchronized void beforeRead() throws InterruptedException {
        ++waitingReaders;
        while (!allowReader()) {
            try {
                wait();
            } catch (InterruptedException ie) {
                --waitingReaders; // roll back state
                throw ie;
            }
        }
        --waitingReaders;
        ++activeReaders;
    }

    protected synchronized void afterRead() {
        --activeReaders;
        notifyAll();
    }

    protected synchronized void beforeWrite() throws InterruptedException {
        ++waitingWriters;
        while (!allowWriter()) {
            try {
                wait();
            } catch (InterruptedException ie) {
                --waitingWriters;
                throw ie;
            }
        }
        --waitingWriters;
        ++activeWriters;
    }

    protected synchronized void afterWrite() {
        --activeWriters;
        notifyAll();
    }

}
