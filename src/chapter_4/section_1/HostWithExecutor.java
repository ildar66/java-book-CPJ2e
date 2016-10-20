package chapter_4.section_1;

import common.Helper;

//Executors

/**
 * These implementations @see {@link Executor} may be used in classes such as:
 */
public class HostWithExecutor { // Generic code sketch

    protected long localState;
    protected final Helper helper = new Helper();
    protected final Executor executor;

    public HostWithExecutor(Executor e) {
        executor = e;
    }

    protected synchronized void updateState() {
        localState = 2; // ...;
    }

    public void req() {
        updateState();
        executor.execute(new Runnable() {

            public void run() {
                helper.handle();
            }
        });
    }

}
