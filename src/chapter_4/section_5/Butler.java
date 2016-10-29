package chapter_4.section_5;
//import jcsp.lang.*;
// Processes and channels. Dining philosophers

/**
 * The Butler process makes sure that at most N-1(i.e.,four here)philosophers are seated at any
 * given time.It does this by enabling both enter and exit messages if there are enough seats,but
 * only exit messages otherwise.Because Alternative operates on arrays of alternatives,this
 * requires a bit of manipulation to set up.(Some other utilities in JCSP could be used to simplify this.)
 * The exit channels are placed before the enter channels in the chans array so that the proper
 * channel will be read no matter which Alternative is used. The fairSelect is employed
 * here to ensure that the same four philosophers are not always chosen if a fifth is also trying to enter.
 */
class Butler implements jcsp.lang.CSProcess {

    private final jcsp.lang.AltingChannelInput[] enters;
    private final jcsp.lang.AltingChannelInput[] exits;

    Butler(jcsp.lang.AltingChannelInput[] e, jcsp.lang.AltingChannelInput[] x) {
        enters = e;
        exits = x;
    }

    public void run() {
        int seats = enters.length;
        int nseated = 0;

        // set up arrays for select
        jcsp.lang.AltingChannelInput[] chans = new jcsp.lang.AltingChannelInput[2 * seats];
        for (int i = 0; i < seats; ++i) {
            chans[i] = exits[i];
            chans[seats + i] = enters[i];
        }

        jcsp.lang.Alternative either = new jcsp.lang.Alternative(chans);
        jcsp.lang.Alternative exit = new jcsp.lang.Alternative(exits);

        for (; ; ) {
            // if max number are seated, only allow exits
            jcsp.lang.Alternative alt = (nseated < seats - 1) ? either : exit;

            int i = alt.fairSelect();
            chans[i].read();

            // if i is in first half of array, it is an exit message
            if (i < seats)
                --nseated;
            else
                ++nseated;
        }
    }
}
