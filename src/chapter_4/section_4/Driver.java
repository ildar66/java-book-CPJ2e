package chapter_4.section_4;

// Barriers
class Driver {

    // ...
    int granularity = 1;

    void compute(Problem problem) throws Exception {
        int n = problem.size / granularity;
        CyclicBarrier barrier = new CyclicBarrier(n);
        Thread[] threads = new Thread[n];

        // create
        for (int i = 0; i < n; ++i)
            threads[i] = new Thread(new Segment(barrier));

        // trigger
        for (int i = 0; i < n; ++i)
            threads[i].start();

        // await termination
        for (int i = 0; i < n; ++i)
            threads[i].join();
    }
}
