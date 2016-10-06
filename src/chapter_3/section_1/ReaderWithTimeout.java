package chapter_3.section_1;

import java.io.IOException;
import java.io.InputStream;

// Interruption(IO and resource revocation) para
public class ReaderWithTimeout { // Generic code sketch

    // ...
    void process(int b) {
    }

    void attemptRead(InputStream stream, long timeout) throws Exception {
        long startTime = System.currentTimeMillis();
        try {
            for (; ; ) {
                if (stream.available() > 0) {
                    int c = stream.read();
                    if (c != -1)
                        process(c);
                    else
                        break; // eof
                } else {
                    try {
                        Thread.sleep(100); // arbitrary back-off time
                    } catch (InterruptedException ie) {
                        /* ... quietly wrap up and return ... */
                    }
                    long now = System.currentTimeMillis();
                    if (now - startTime >= timeout) {
                        /* ... fail ... */
                    }
                }
            }
        } catch (IOException ex) { /* ... fail ... */
        }
    }

}
