package chapter_4.section_3;

import java.net.URL;

/**
 * // Joining Threads
 * While it is common practice, the use of unsynchronized (or direct) access of internal result fields
 * as seen in the waiter object is a bit delicate. Since access is not synchronized, correctness relies on
 * the fact that both thread termination and the join method intrinsically employ synchronized
 * methods or blocks
 */
class PictureApp { // Code sketch

    // ...
    private final Renderer renderer = new StandardRenderer();

    void displayBorders() {
    }

    void displayCaption() {
    }

    void displayImage(byte[] b) {
    }

    void cleanup() {
    }

    public void show(final URL imageSource) {

        class Waiter implements Runnable {

            private Pic result = null;

            Pic getResult() {
                return result;
            }

            public void run() {
                result = renderer.render(imageSource);
            }
        }

        Waiter waiter = new Waiter();
        Thread t = new Thread(waiter);
        t.start();

        displayBorders(); // do other things
        displayCaption(); // while rendering

        try {
            t.join();
        } catch (InterruptedException e) {
            cleanup();
            return;
        }

        Pic pic = waiter.getResult();
        if (pic != null)
            displayImage(pic.getImage());
        else {
        }
        // ... deal with assumed rendering failure
    }
}
