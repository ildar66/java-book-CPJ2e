package chapter_2.section_4;

import chapter_0.Helper;

// used in "Open calls" para

/**
 * @see ServerWithOpenCall
 * If helper.operation takes a long
 * time, then calls to synchronized methods such as getState might block for an unacceptable
 * time waiting for the method to be available.
 */
class ServerWithStateUpdate {

    private double state;
    private final Helper helper = new Helper();

    public synchronized void service() {
        state = 2.0f; // ...; // set to some new value
        helper.operation();
    }

    public synchronized double getState() {
        return state;
    }

}
