package chapter_3.section_4;

import EDU.oswego.cs.dl.util.concurrent.Latch;

//Latches
/*
Extended forms of latches include countdowns, which allow acquire to proceed when a fixed
        number of releases occur, not just one. Latches, countdowns, and other simple utilities built on
        top of them can be used to coordinate responses to conditions involving:
 * Completion indicators. For example, to force a set of threads to wait until some other activity
        completes.
 * Timing thresholds. For example, to trigger a set of threads at a certain date.
 * Event indications. For example, to trigger processing that cannot occur until a certain packet is
        received or button is clicked.
 * Error indications. For example, to trigger a set of threads to proceed with global shut-down tasks.
*/
class Game {

    // ...
    void begin(int nplayers) {
        Latch startSignal = new Latch();

        for (int i = 0; i < nplayers; ++i)
            new Thread(new Player(startSignal)).start();

        startSignal.release();
    }

}
