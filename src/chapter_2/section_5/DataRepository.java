package chapter_2.section_5;

import EDU.oswego.cs.dl.util.concurrent.ReadWriteLock;
import EDU.oswego.cs.dl.util.concurrent.WriterPreferenceReadWriteLock;

// Read-Write Locks para
class DataRepository { // code sketch

    protected final ReadWriteLock rw = new WriterPreferenceReadWriteLock();

    public void access() throws InterruptedException {
        rw.readLock().acquire();
        try {
            /* read data */
        } finally {
            rw.readLock().release();
        }
    }

    public void modify() throws InterruptedException {
        rw.writeLock().acquire();
        try {
            /* write data */
        } finally {
            rw.writeLock().release();
        }
    }

}
