package chapter_3.section_2;

/**
 * Monitor Mechanics para.
 * @see package-info.java
 * Here is one possible outcome of three threads invoking methods on a common x. Notice that even
 * though T1 began waiting before T2, T2 resumed before T1. It could have been otherwise; there are no
 * guarantees.
 */
class X {

    synchronized void w() throws InterruptedException {
        before();
        wait();
        after();
    }

    synchronized void n() {
        notifyAll();
    }

    void before() {
    }

    void after() {
    }

}
