package chapter_3.section_1;

import java.io.IOException;
import java.net.Socket;

// Retry para
/*
    You can contain local failure to the current method, rather than throwing exceptions back to clients,
    if you have reason to believe that retrying an action will succeed.
*/
public class ClientUsingSocket {

    // Code sketch
    int portnumber = 1234;
    String server = "gee";

    // ...
    Socket retryUntilConnected() throws InterruptedException {
        // first delay is randomly chosen between 5 and 10secs
        long delayTime = 5000 + (long) (Math.random() * 5000);
        for (; ; ) {
            try {
                return new Socket(server, portnumber);
            } catch (IOException ex) {
                Thread.sleep(delayTime);
                delayTime = delayTime * 3 / 2 + 1; // increase 50%
            }
        }
    }
}
