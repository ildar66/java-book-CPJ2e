package chapter_4.section_1;

import common.Helper;

// Open Calls

/**
 * The main design force here is latency. If a Host is busy servicing requests, then it cannot accept new
 * ones. This adds response time to new requests from Clients, reducing overall service availability.
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
