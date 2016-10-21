package chapter_4.section_1;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

// Event-driven tasks
/*
Many IO-based tasks are initially written in a session-based style (see § 2.3.1), continuously pulling
        commands from sockets and processing them. For example:
*/
class SessionTask implements Runnable {    // generic code sketch

    static final int BUFFSIZE = 1024;

    protected final Socket socket;
    protected final InputStream input;

    SessionTask(Socket s) throws IOException {
        socket = s;
        input = socket.getInputStream();
    }

    void processCommand(byte[] b, int n) {
    }

    void cleanup() {
    }

    public void run() { // Normally run in a new thread
        byte[] commandBuffer = new byte[BUFFSIZE];
        try {
            for (; ; ) {
                int bytes = input.read(commandBuffer, 0, BUFFSIZE);
                if (bytes != BUFFSIZE)
                    break;
                processCommand(commandBuffer, bytes);
            }
        } catch (IOException ex) {
            cleanup();
        } finally {
            try {
                input.close();
                socket.close();
            } catch (IOException ignore) {
            }
        }
    }

}
/*
    To enable many sessions to be handled without using many threads, the tasks first must be refactored
        into an event-driven style, where an event here signifies IO availability. In this style, a session consists
        of possibly many executions of its event-triggered task(s), each of which is invoked when input
        becomes available.*/
