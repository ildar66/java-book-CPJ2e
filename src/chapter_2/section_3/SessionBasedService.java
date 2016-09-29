package chapter_2.section_3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/*
* Sessions para
*
* Many hand-off sequences are structured as sessions in which some public entry method constructs
    objects that will be confined to a sequence of operations comprising a service. This entry method
    should also be responsible for any cleanup operations required upon completion of the sequence.
    For example:
*/
class SessionBasedService {

    // ...
    public void service() {
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
            } // ignore exception in close
        }
    }

    void handleIOFailure() {
    }

    void doService(OutputStream s) throws IOException {
        s.write(0);
        // ... possibly more handoffs ...
    }

}
