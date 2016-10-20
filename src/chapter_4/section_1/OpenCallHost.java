package chapter_4.section_1;

import common.Helper;

// Open Calls

/**
 * The main design force here is latency. If a Host is busy servicing requests, then it cannot accept new
 * ones. This adds response time to new requests from Clients, reducing overall service availability.
 * Here, even if the helper.handle call is relatively time-consuming, the Host object will still be
 * able to accept new requests from clients running in different threads. The request acceptance rate is
 * bounded only by the time it takes to update local state.
 */
public class OpenCallHost { // Generic code sketch

    protected long localState;
    protected final Helper helper = new Helper();

    protected synchronized void updateState() {
        localState = 2; // ...;
    }

    public void req() {
        updateState();
        helper.handle();
    }

}
