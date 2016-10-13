package chapter_3.section_4;
//Synchronous channels

import EDU.oswego.cs.dl.util.concurrent.Channel;
import EDU.oswego.cs.dl.util.concurrent.Semaphore;

public class SynchronousChannel implements Channel {

    protected Object item = null; // to hold while in transit

    protected final Semaphore putPermit;
    protected final Semaphore takePermit;
    protected final Semaphore taken;

    public SynchronousChannel() {
        putPermit = new Semaphore(1);
        takePermit = new Semaphore(0);
        taken = new Semaphore(0);
    }

    public void put(Object x) throws InterruptedException {
        putPermit.acquire();
        item = x;
        takePermit.release();

        // Must wait until signalled by taker
        InterruptedException caught = null;
        for (; ; ) {
            try {
                taken.acquire();
                break;
            } catch (InterruptedException ie) {
                caught = ie;
            }
        }

        if (caught != null)
            throw caught; // can now rethrow
    }

    public Object take() throws InterruptedException {
        takePermit.acquire();
        Object x = item;
        item = null;
        putPermit.release();
        taken.release();
        return x;
    }

    @Override
    public boolean offer(Object item, long msecs) throws InterruptedException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Object poll(long msecs) throws InterruptedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object peek() {
        // TODO Auto-generated method stub
        return null;
    }

}
