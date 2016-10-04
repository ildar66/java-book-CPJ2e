package chapter_2.section_5;

import EDU.oswego.cs.dl.util.concurrent.Mutex;

/*
// Reorderings para:

    Back-off techniques may be used as safeguards in designs employing lock ordering techniques
    in which there are relatively rare exceptions to a given locking hierarchy.
    In these cases, code requiring multiple locks can try one ordering, and if it fails,
    release all locks and try a different ordering.
*/
class CellUsingReorderedBackoff {

    private long value;
    private final Mutex mutex = new Mutex();

    private static boolean trySwap(CellUsingReorderedBackoff a, CellUsingReorderedBackoff b) throws InterruptedException {
        boolean success = false;

        if (a.mutex.attempt(0)) {
            try {
                if (b.mutex.attempt(0)) {
                    try {
                        long t = a.value;
                        a.value = b.value;
                        b.value = t;
                        success = true;
                    } finally {
                        b.mutex.release();
                    }
                }
            } finally {
                a.mutex.release();
            }
        }

        return success;

    }

    void swapValue(CellUsingReorderedBackoff other) {
        if (this == other)
            return; // alias check required
        try {
            while (!trySwap(this, other) && !trySwap(other, this))
                Thread.sleep(100);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
