package chapter_3.section_4;

import EDU.oswego.cs.dl.util.concurrent.BrokenBarrierException;
import EDU.oswego.cs.dl.util.concurrent.Rendezvous;

/*
 * // Exchangers
 *      The following FillAndEmpty class gives a glimpse of the additional exception-handling
 * obligations required with exchangers. Because the protocol is symmetric, cancellation or time-out of
 * one party in the midst of an attempted exchange must lead to an exception (here,
 * BrokenBarrierException) in the other party. In the example below, this is handled simply
 * by returning from the run method.
 *      A more realistic version would entail further cleanup, including additional adjustments to deal
 * with incompletely filled or emptied buffers upon termination, as well as
 * to deal with IO exceptions and end-of-file conditions surrounding the readByte method.
 */
class FillAndEmpty {

    // Incomplete
    static final int SIZE = 1024; // buffer size, for demo
    protected Rendezvous exchanger = new Rendezvous(2);

    protected byte readByte() {
        return 1; /* ... */
    }

    protected void useByte(byte b) { /* ... */
    }

    public void start() {
        new Thread(new FillingLoop()).start();
        new Thread(new EmptyingLoop()).start();
    }

    class FillingLoop implements Runnable { // inner class

        public void run() {
            byte[] buffer = new byte[SIZE];
            int position = 0;

            try {
                for (; ; ) {

                    if (position == SIZE) {
                        buffer = (byte[]) (exchanger.rendezvous(buffer));
                        position = 0;
                    }

                    buffer[position++] = readByte();
                }
            } catch (BrokenBarrierException ex) {
            } // die
            catch (InterruptedException ie) {
            } // die
        }
    }

    class EmptyingLoop implements Runnable { // inner class

        public void run() {
            byte[] buffer = new byte[SIZE];
            int position = SIZE; // force exchange first time through

            try {
                for (; ; ) {

                    if (position == SIZE) {
                        buffer = (byte[]) (exchanger.rendezvous(buffer));
                        position = 0;
                    }

                    useByte(buffer[position++]);
                }
            } catch (BrokenBarrierException ex) {
            } // die
            catch (InterruptedException ex) {
            } // die
        }
    }

}
