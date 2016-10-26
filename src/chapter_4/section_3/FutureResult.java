package chapter_4.section_3;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;

/**
 * // Callables
 * The FutureResult class maintains methods to get the result Object that is returned, or the
 * Exception that is thrown by a Callable. Unlike our Pic versions where all failures were just
 * indicated via null values, it deals with interruptions more honestly by throwing exceptions back to
 * clients attempting to obtain results.
 * To differentiate properly between exceptions encountered in the service versus those encountered
 * trying to execute the service, exceptions thrown by the Callable are repackaged using
 * java.lang.reflect.InvocationTargetException, a general-purpose class for
 * wrapping one exception inside another.
 * Also, for the sake of generality, the FutureResult does not itself create threads. Instead, it
 * supports method setter that returns a Runnable that users can then execute within a thread or
 * any other code Executor.
 */
class FutureResult { // Fragments

    protected Object value = null;
    protected boolean ready = false;
    protected InvocationTargetException exception = null;

    public synchronized Object get() throws InterruptedException, InvocationTargetException {

        while (!ready)
            wait();

        if (exception != null)
            throw exception;
        else
            return value;
    }

    public Runnable setter(final Callable<?> function) {
        return new Runnable() {

            public void run() {
                try {
                    set(function.call());
                } catch (Throwable e) {
                    setException(e);
                }
            }
        };
    }

    synchronized void set(Object result) {
        value = result;
        ready = true;
        notifyAll();
    }

    synchronized void setException(Throwable e) {
        exception = new InvocationTargetException(e);
        ready = true;
        notifyAll();
    }

    // ... other auxiliary and convenience methods ...

}
