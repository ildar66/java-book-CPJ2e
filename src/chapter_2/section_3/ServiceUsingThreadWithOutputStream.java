package chapter_2.section_3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

//Thread-specific fields para
class ServiceUsingThreadWithOutputStream {

    // ...
    public void service() throws IOException {
        OutputStream output = new FileOutputStream("...");
        Runnable r = new Runnable() {

            public void run() {
                try {
                    doService();
                } catch (IOException e) {
                }
            }
        };
        new ThreadWithOutputStream(r, output).start();
    }

    void doService() throws IOException {
        ThreadWithOutputStream.current().getOutput().write(0);
    }

}
