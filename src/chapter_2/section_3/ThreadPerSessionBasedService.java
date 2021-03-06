package chapter_2.section_3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

//Confinement Within Threads
class ThreadPerSessionBasedService { // fragments

    // ...
    public void service() {
        Runnable r = new Runnable() {

            public void run() {
                OutputStream output = null;
                try {
                    output = new FileOutputStream("...");
                    doService(output);
                } catch (IOException e) {
                    handleIOFailure();
                } finally {
                    try {
                        if (output != null)
                            output.close();
                    } catch (IOException ignore) {
                    }
                }
            }
        };
        new Thread(r).start();
    }

    void handleIOFailure() {
    }

    void doService(OutputStream s) throws IOException {
        s.write(0);
        // ... possibly more hand-offs ...
    }

}
