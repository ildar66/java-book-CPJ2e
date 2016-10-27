package chapter_4.section_4;

import EDU.oswego.cs.dl.util.concurrent.FJTask;

/**
 * // Fork/Join. Linking subtasks
 * Stretching the Fib example a bit, the FibVL class illustrates one way to set this up. This style of
 * solution is not especially useful here, but is applicable in contexts in which a dynamic number of
 * subtasks are created, possibly across different methods. Notice that the subtasks here are joined in the
 * opposite order in which they were created. Since the processing order of results does not matter here,
 * we use the simplest possible linking algorithm (prepending), which happens to reverse the order of
 * tasks during traversal. This strategy applies whenever the accumulation step is commutative and
 * associative with respect to results, so tasks can be processed in any order. If the order did matter, we
 * would need to adjust list construction or traversal accordingly.
 */
class FibVL extends FJTask {

    static final int sequentialThreshold = 13; // for tuning

    volatile int number; // as before
    final FibVL next; // embedded linked list of sibling tasks

    FibVL(int n, FibVL list) {
        number = n;
        next = list;
    }

    int seqFib(int n) {
        if (n <= 1)
            return n;
        else
            return seqFib(n - 1) + seqFib(n - 2);
    }

    public void run() {
        int n = number;
        if (n <= sequentialThreshold)
            number = seqFib(n);
        else {
            FibVL forked = null; // list of subtasks

            forked = new FibVL(n - 1, forked); // prepends to list
            forked.fork();

            forked = new FibVL(n - 2, forked);
            forked.fork();

            number = accumulate(forked);
        }
    }

    // Traverse list, joining each subtask and adding to result
    int accumulate(FibVL list) {
        int r = 0;
        for (FibVL f = list; f != null; f = f.next) {
            f.join();
            r += f.number;
        }
        return r;
    }
}
