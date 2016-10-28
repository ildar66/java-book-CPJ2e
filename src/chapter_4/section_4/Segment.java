package chapter_4.section_4;

// Barriers
class Segment implements Runnable { // Code sketch

    final CyclicBarrier bar; // shared by all segments

    Segment(CyclicBarrier b) {
        bar = b;
    }

    void update() {
    }

    public void run() {
        // ...
        try {
            for (int i = 0; i < 10 /* iterations */; ++i) {
                update();
                bar.barrier();
            }
        } catch (InterruptedException ie) {
        }
        // ...
    }
}
