package chapter_3.section_4;

import EDU.oswego.cs.dl.util.concurrent.Latch;

/**
 * // Latches @see {@link Game}
 */
class Player implements Runnable { // Code sketch

    // ...
    protected final Latch startSignal;

    Player(Latch l) {
        startSignal = l;
    }

    public void run() {
        try {
            startSignal.acquire();
            play();
        } catch (InterruptedException ie) {
            return;
        }
    }

    void play() {
    }
    // ...
}
