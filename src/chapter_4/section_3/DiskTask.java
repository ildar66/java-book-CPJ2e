package chapter_4.section_3;

import EDU.oswego.cs.dl.util.concurrent.Latch;

// Scheduling Services
abstract class DiskTask implements Runnable {

    protected final int cylinder; // read/write parameters
    protected final byte[] buffer;
    protected Failure exception = null; // to relay out
    protected DiskTask next = null; // for use in queue
    protected final Latch done = new Latch(); // status indicator

    DiskTask(int c, byte[] b) {
        cylinder = c;
        buffer = b;
    }

    abstract void access() throws Failure; // read or write

    public void run() {
        try {
            access();
        } catch (Failure ex) {
            setException(ex);
        } finally {
            done.release();
        }
    }

    void awaitCompletion() throws InterruptedException {
        done.acquire();
    }

    synchronized Failure getException() {
        return exception;
    }

    synchronized void setException(Failure f) {
        exception = f;
    }
}
