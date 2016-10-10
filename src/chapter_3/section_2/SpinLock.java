package chapter_3.section_2;
/*
    // Busy Waits NOTE
    Implementing guards via waiting and notification methods is nearly always superior to using an
    optimistic-retry-style busy-wait "spinloop" of the form:
       protected void busyWaitUntilCond() {
        while (!cond)
            Thread.yield();
      }
    Busy-waits can waste an unbounded amount of CPU time spinning uselessly.
    Busy-waits have drawbacks that make them poor choices for implementing most guarded actions. The
    contrasts between the two techniques help explain why suspension-based waiting and notification
    methods are defined as they are.

    The main exceptions are those cases in which you somehow know that the condition must become
    true within some very short, bounded amount of time. In such cases, the time wasted spinning might
    be less than the time required to suspend and resume threads.
*/

class SpinLock {
    // Avoid needing to use this

    private volatile boolean busy = false;

    synchronized void release() {
        busy = false;
    }

    void acquire() throws InterruptedException {
        int itersBeforeYield = 100; // 100 is arbitrary
        int itersBeforeSleep = 200; // 200 is arbitrary
        long sleepTime = 1; // 1msec is arbitrary
        int iters = 0;
        for (; ; ) {
            if (!busy) { // test-and-test-and-set
                synchronized (this) {
                    if (!busy) {
                        busy = true;
                        return;
                    }
                }
            }

            if (iters < itersBeforeYield) { // spin phase
                ++iters;
            } else if (iters < itersBeforeSleep) { // yield phase
                ++iters;
                Thread.yield();
            } else { // back-off phase
                Thread.sleep(sleepTime);
                sleepTime = 3 * sleepTime / 2 + 1; // 50% is arbitrary
            }
        }
    }

}
