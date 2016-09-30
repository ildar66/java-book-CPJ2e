package chapter_2.section_4;

import chapter_0.Helper;

// "Open calls" para

/**
 * It is still possible to use open calls here even if the helper reference field is mutable;
 * for example, using block synchronization:
 */
class ServerWithAssignableHelper {

    private double state;
    private Helper helper = new Helper();

    synchronized void setHelper(Helper h) {
        helper = h;
    }

    public void service() {
        Helper h;
        synchronized (this) {
            state = 2.0f; // ...
            h = helper;
        }
        h.operation();
    }

    public synchronized void synchedService() { // see below
        service();
    }

}
