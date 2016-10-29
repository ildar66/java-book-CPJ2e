package chapter_4.section_5;

//import jcsp.lang.*;

/**
 * // Processes and channels. Dining philosophers
 * Communicating Sequential Processes (CSP) provides both a formal
 * approach to concurrency and an associated set of design techniques
 */

class Fork implements jcsp.lang.CSProcess {

    private final jcsp.lang.AltingChannelInput[] fromPhil;

    Fork(jcsp.lang.AltingChannelInput l, jcsp.lang.AltingChannelInput r) {
        fromPhil = new jcsp.lang.AltingChannelInput[] {l, r};
    }

    public void run() {
        jcsp.lang.Alternative alt = new jcsp.lang.Alternative(fromPhil);

        for (; ; ) {
            int i = alt.select();   // await message from either
            fromPhil[i].read();     // pick up
            fromPhil[i].read();     // put down
        }

    }
}
