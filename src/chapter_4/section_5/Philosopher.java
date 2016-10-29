package chapter_4.section_5;

//import jcsp.lang.*;
// Processes and channels. Dining philosophers
class Philosopher implements jcsp.lang.CSProcess {

    private final jcsp.lang.ChannelOutput leftFork;
    private final jcsp.lang.ChannelOutput rightFork;
    private final jcsp.lang.ChannelOutput enter;
    private final jcsp.lang.ChannelOutput exit;

    Philosopher(jcsp.lang.ChannelOutput l, jcsp.lang.ChannelOutput r, jcsp.lang.ChannelOutput e,
                jcsp.lang.ChannelOutput x) {
        leftFork = l;
        rightFork = r;
        enter = e;
        exit = x;
    }

    public void run() {

        for (; ; ) {

            think();

            enter.write(null); // get seat
            leftFork.write(null); // pick up left
            rightFork.write(null); // pick up right

            eat();

            leftFork.write(null); // put down left
            rightFork.write(null); // put down right
            exit.write(null); // leave seat

        }

    }

    private void eat() {
    }

    private void think() {
    }

}
