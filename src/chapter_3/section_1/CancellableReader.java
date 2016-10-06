package chapter_3.section_1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// Interruption(IO and resource revocation) para
/*
JVMs do not automatically interrupt IO operations.
     This imposes an additional obligation on code dealing with cancellation. If a thread may be
     performing IO, any attempt to cancel it in the midst of IO operations must be aware of the IO object
     being used and must be willing to close the IO object. If this is acceptable, you may instigate
     cancellation by both closing the IO object and interrupting the thread. For example:
*/
class CancellableReader { // Incomplete

    private Thread readerThread; // only one at a time supported
    private FileInputStream dataFile;

    public synchronized void startReaderThread() throws IllegalStateException, FileNotFoundException {
        if (readerThread != null)
            throw new IllegalStateException();
        dataFile = new FileInputStream("data");
        readerThread = new Thread(new Runnable() {

            public void run() {
                doRead();
            }
        });
        readerThread.start();
    }

    protected synchronized void closeFile() { // utility method
        if (dataFile != null) {
            try {
                dataFile.close();
            } catch (IOException ignore) {
            }
            dataFile = null;
        }
    }

    void process(int b) {
    }

    private void doRead() {
        try {
            while (!Thread.interrupted()) {
                try {
                    int c = dataFile.read();
                    if (c == -1)
                        break;
                    else
                        process(c);
                } catch (IOException ex) {
                    break; // perhaps first do other cleanup
                }
            }
        } finally {
            closeFile();
            synchronized (this) {
                readerThread = null;
            }
        }
    }

    public synchronized void cancelReaderThread() {
        if (readerThread != null)
            readerThread.interrupt();
        closeFile();
    }

}
