package chapter_2.section_4;

import chapter_0.Helper;

// "Open calls" para

/**
 * @see ServerWithStateUpdate
 * • First, update state (holding locks).
 * • Then, send messages (without holding locks).
 */
class ServerWithOpenCall {

    private double state;
    private final Helper helper = new Helper();

    private synchronized void updateState() {
        state = 2.0f; // ...; // set to some new value
    }

    public void service() {
        updateState();
        helper.operation();
    }

    public synchronized double getState() {
        return state;
    }

}
