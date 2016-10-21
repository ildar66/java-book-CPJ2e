package chapter_4.section_1;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Event-driven tasks @ see {@link PollingWorker}
 */

/* Event-driven IO tasks are similar in form to GUI event handlers. A session-based design can be
        converted into an event-driven form by:
        • Isolating the basic per-command functionality in a reworked task run method that reads one
        command and performs the associated action.
        • Defining the run method so that it can be repeatedly triggered whenever input is available to
        be read (or an IO exception occurs).
        • Manually maintaining completion status so that the per-event action is no longer triggered
        when the session finishes, normally because the input has been exhausted or the connection
        has been closed.
        For example:*/
class IOEventTask implements Runnable { // generic code sketch

    static final int BUFFSIZE = 1024;

    protected final Socket socket;
    protected final InputStream input;
    protected volatile boolean done = false; // latches true

    IOEventTask(Socket s) throws IOException {
        socket = s;
        input = socket.getInputStream();
    }

    void processCommand(byte[] b, int n) {
    }

    void cleanup() {
    }

    public void run() { // trigger only when input available
        if (done)
            return;

        byte[] commandBuffer = new byte[BUFFSIZE];
        try {
            int bytes = input.read(commandBuffer, 0, BUFFSIZE);
            if (bytes != BUFFSIZE)
                done = true;
            else
                processCommand(commandBuffer, bytes);
        } catch (IOException ex) {
            cleanup();
            done = true;
        } finally {
            if (!done)
                return;
            try {
                input.close();
                socket.close();
            } catch (IOException ignore) {
            }
        }
    }

    // Accessor methods needed by triggering agent:
    boolean done() {
        return done;
    }

    InputStream input() {
        return input;
    }

}
