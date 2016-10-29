package chapter_4.section_5;
//import jcsp.lang.*;
// Processes and channels. Dining philosophers

class College implements jcsp.lang.CSProcess {

    final static int N = 5;

    private final jcsp.lang.CSProcess action;

    College() {
        jcsp.lang.One2OneChannel[] lefts = jcsp.lang.One2OneChannel.create(N);
        jcsp.lang.One2OneChannel[] rights = jcsp.lang.One2OneChannel.create(N);
        jcsp.lang.One2OneChannel[] enters = jcsp.lang.One2OneChannel.create(N);
        jcsp.lang.One2OneChannel[] exits = jcsp.lang.One2OneChannel.create(N);

        Butler butler = new Butler(enters, exits);

        Philosopher[] phils = new Philosopher[N];
        for (int i = 0; i < N; ++i)
            phils[i] = new Philosopher(lefts[i], rights[i], enters[i], exits[i]);

        Fork[] forks = new Fork[N];
        for (int i = 0; i < N; ++i)
            forks[i] = new Fork(rights[(i + 1) % N], lefts[i]);

        action = new jcsp.lang.Parallel(new jcsp.lang.CSProcess[] {butler, new jcsp.lang.Parallel(phils),
                                                                   new jcsp.lang.Parallel(forks)});
    }

    public void run() {
        action.run();
    }

    public static void main(String[] args) {
        new College().run();
    }

}