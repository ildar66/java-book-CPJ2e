package chapter_3.section_5;

/**
 * // Example section
 * Without further care, the resulting code would be deadlock-prone. This problem is intrinsic in
 * symmetrical joint actions in which changes in either object could lead to an action. Here, both a
 * savings account and a checking account can start their deposit sequences at the same time. We need a
 * way to break the cycle that could lead to both being blocked while trying to invoke each other's
 * methods. (Note that deadlock would never occur if we require only that the action take place when
 * checking balances decrease. This would in turn lead to a simpler solution all around.)
 * For illustration, potential deadlock is addressed here in a common (although of course not universally
 * applicable) fashion, via a simple untimed back-off protocol. The tryTransfer method uses a
 * boolean utility class supporting a testAndSet method that atomically sets its value to true and
 * reports its previous value. (Alternatively, the attempt method of a Mutex could be used here.)
 */
public class TSBoolean {

    private boolean value = false;

    // set to true; return old value
    public synchronized boolean testAndSet() {
        boolean oldValue = value;
        value = true;
        return oldValue;
    }

    public synchronized void clear() {
        value = false;
    }

}
