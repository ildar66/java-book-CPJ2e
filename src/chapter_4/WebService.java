package chapter_4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * As an initial example, here is one way to approach a common thread-based design, a web service.
 * Here, a running WebService is a "daemon process" ACTOR-STYLE thread — it continuously interacts
 * with its environment by listening for new incoming requests. But invocations to
 * handler.process are issued in a TASK-BASED manner — a new task is set in motion to handle
 * each incoming request. Here, for the sake of concise illustration, the request is simply a number, and
 * the handler just returns the negation of the number back to the client.
 */
public class WebService implements Runnable {

    static final int PORT = 1040; // just for demo
    Handler handler = new Handler();

    public void run() {
        try {
            ServerSocket socket = new ServerSocket(PORT);
            for (; ; ) {
                final Socket connection = socket.accept();
                new Thread(new Runnable() {

                    public void run() {
                        handler.process(connection);
                    }
                }).start();
            }
        } catch (Exception e) {
        } // die
    }

    public static void main(String[] args) {
        new Thread(new WebService()).start();
    }
}

class Handler {

    void process(Socket s) {
        DataInputStream in = null;
        DataOutputStream out = null;
        try {
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
            int request = in.readInt();
            int result = -request; // return negation to client
            out.writeInt(result);
        } catch (IOException ex) {
        } // fall through

        finally { // clean up
            try {
                if (in != null)
                    in.close();
            } catch (IOException ignore) {
            }
            try {
                if (out != null)
                    out.close();
            } catch (IOException ignore) {
            }
            try {
                s.close();
            } catch (IOException ignore) {
            }
        }
    }

}
