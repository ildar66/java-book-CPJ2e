package chapter_2.section_4;

/**
 * // Atomic commitment para
 * There are several common minor variations in how the commit method is defined. For example, the
 * version usually named "compareAndSwap" returns the current value, which may be either the new
 * or the old value depending on whether the operation committed successfully. The increasing
 * popularity of optimistic techniques in systems-level concurrent programming is in part due to (and in
 * part the cause of) the fact that most modern processors include an efficient built-in
 * compareAndSwap instruction or one of its variants. While these are not directly accessible from
 * the Java programming language, it is in principle possible for optimizing compilers to map constructs
 * to use such instructions. (Even if not, optimistic updates are still efficient.)
 */
class Optimistic { // Generic code sketch

    private State state; // reference to representation object

    private synchronized State getState() {
        return state;
    }

    private synchronized boolean commit(State assumed, State next) {
        if (state == assumed) {
            state = next;
            return true;
        } else
            return false;
    }

}
