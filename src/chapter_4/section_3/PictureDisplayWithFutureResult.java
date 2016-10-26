package chapter_4.section_3;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 * // Callables
 * The FutureResult class can be used directly to support generic Futures or as a utility in
 * constructing more specialized versions. As an example of direct use:
 */
class PictureDisplayWithFutureResult { // Code sketch

    void displayBorders() {
    }

    void displayCaption() {
    }

    void displayImage(byte[] b) {
    }

    void cleanup() {
    }

    private final Renderer renderer = new StandardRenderer();

    // ...

    public void show(final URL imageSource) {

        try {
            FutureResult futurePic = new FutureResult();
            Runnable command = futurePic.setter(new Callable<Object>() {

                public Object call() {
                    return renderer.render(imageSource);
                }
            });
            new Thread(command).start();

            displayBorders();
            displayCaption();

            displayImage(((Pic) (futurePic.get())).getImage());
        } catch (InterruptedException ex) {
            cleanup();
            return;
        } catch (InvocationTargetException ex) {
            cleanup();
            return;
        }
    }
}
