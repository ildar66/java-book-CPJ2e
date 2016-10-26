package chapter_4.section_3;

import java.net.URL;

/**
 * // Futures
 * For illustration, AsynchRenderer uses explicit waiting and notification operations based on a
 * ready flag rather than relying on Thread.join.
 */
class AsynchRenderer implements Renderer {

    private final Renderer renderer = new StandardRenderer();

    static class FuturePic implements Pic { // inner class

        private Pic pic = null;
        private boolean ready = false;

        synchronized void setPic(Pic p) {
            pic = p;
            ready = true;
            notifyAll();
        }

        public synchronized byte[] getImage() {
            while (!ready)
                try {
                    wait();
                } catch (InterruptedException e) {
                    return null;
                }
            return pic.getImage();
        }
    }

    public Pic render(final URL src) {
        final FuturePic p = new FuturePic();
        new Thread(new Runnable() {

            public void run() {
                p.setPic(renderer.render(src));
            }
        }).start();
        return p;
    }
}
