package chapter_3.section_1;

// Asynchronous termination para
/*
The stop method was originally included in class Thread,but its use has since been deprecated.
        Thread.stop causes a thread to abruptly throw a ThreadDeath exception regardless of what
        it is doing.(Like interrupt,stop does not abort waits for locks or IO.But,unlike
        interrupt,it is not strictly guaranteed to abort wait,sleep,or join.)

        This can be an arbitrarily dangerous operation.Because Thread.stop generates asynchronous
        signals,activities can be terminated while they are in the midst of operations or code segments that
        absolutely must roll back or roll forward for the sake of program safety and object consistency.For a
        bare generic example,consider:

        If a Thread.stop happens to cause termination at line(*),then the object will be broken:Upon
        thread termination,it will remain in an inconsistent state because variable v is set to an illegal value.
*/

class C { // Fragments

    private int v; // invariant: v >= 0

    synchronized void f() {
        v = -1; // temporarily set to illegal value as flag
        compute(); // possible stop point (*)
        v = 1; // set to legal value
    }

    synchronized void g() {
        while (v != 0) {
            --v;
            something();
        }
    }

    void compute() {
    }

    void something() {
    }
}
