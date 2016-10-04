package chapter_2.section_5;

import EDU.oswego.cs.dl.util.concurrent.Mutex;

/*
// Back-offs para
The attempt method is useful for recovery from deadlocks and other liveness problems involving
    multiple locks.When you cannot ensure that lockups are impossible(as is the case for at least some
    components in most open systems), you can routinely use attempt instead of acquire,
    providing a heuristic time-out value(for example a few seconds)to indicate possible lockups,and
    then take evasive action upon failure.
*/

public class CellUsingBackoff {

    private long value;
    private final Mutex mutex = new Mutex();

    void swapValue(CellUsingBackoff other) {
        if (this == other)
            return; // alias check required
        for (; ; ) {
            try {
                mutex.acquire();

                try {
                    if (other.mutex.attempt(0)) {
                        try {
                            long t = value;
                            value = other.value;
                            other.value = t;
                            return;
                        } finally {
                            other.mutex.release();
                        }
                    }
                } finally {
                    mutex.release();
                }

                Thread.sleep(100);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

}
