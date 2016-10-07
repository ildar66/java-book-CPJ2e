package chapter_3.section_2;

/*
//Guarded Waits
    To ensure that guards are implemented correctly, it is sometimes helpful to encapsulate each guard in
        its own method. For a generic example:
*/

class GuardedClass { // Generic code sketch

    protected boolean cond = false;

    // PRE: lock held
    protected void awaitCond() throws InterruptedException {
        while (!cond) {
            wait();
        }
    }

    public synchronized void guardedAction() {
        try {
            awaitCond();
        } catch (InterruptedException ie) {
            // fail
        }

        // actions
    }

}
