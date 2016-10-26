package chapter_4.section_3;

import java.net.URL;

/**
 * // Futures
 * For illustration, @see {@link AsynchRenderer} uses explicit waiting and notification operations based on a
 * ready flag rather than relying on Thread.join.
 * Applications relying on this class can be written in a simple fashion:
 */
class PicturAppWithFuture { // Code sketch

    private final Renderer renderer = new AsynchRenderer();

    void displayBorders() {
    }

    void displayCaption() {
    }

    void displayImage(byte[] b) {
    }

    void cleanup() {
    }

    public void show(final URL imageSource) {
        Pic pic = renderer.render(imageSource);

        displayBorders(); // do other things ...
        displayCaption();

        byte[] im = pic.getImage();
        if (im != null)
            displayImage(im);
        else {
        } // deal with assumed rendering failure
    }
}
