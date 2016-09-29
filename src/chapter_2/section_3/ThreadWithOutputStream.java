package chapter_2.section_3;

import java.io.OutputStream;

// Thread-specific fields para
/*
In addition to receiving confined references along call chains, the method invocations executing
   within a single thread can access the Thread object representing the thread they are running in, and
   any further information traversable from there.
   The static method Thread.currentThread() can be called from any method
   and returns the Thread object of the caller.
*/
class ThreadWithOutputStream extends Thread {

    private OutputStream output;

    ThreadWithOutputStream(Runnable r, OutputStream s) {
        super(r);
        output = s;
    }

    static ThreadWithOutputStream current() throws ClassCastException {
        return (ThreadWithOutputStream) (currentThread());
    }

    static OutputStream getOutput() {
        return current().output;
    }

    static void setOutput(OutputStream s) {
        current().output = s;
    }

}
