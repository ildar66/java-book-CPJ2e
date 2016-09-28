package chapter_2.section_2;

/*
    The Java Memory Model para.

    In a purely sequential language, the method check could never return false.
    This holds even though compilers, run-time systems, and hardware might process this code
    in a way that you might not intuitively expect.

    Things are different in concurrent programming. Here, it is entirely possible for check to be called
    in one thread while set is being executed in another, in which case the check might be "spying" on
    the optimized execution of set. And if any of the above manipulations occur, it is possible for
    check to return false. For example, as detailed below, check could read a value for the long
    b that is neither 0 nor -1, but instead a half-written in-between value. Also, out-of-order execution of
    the statements in set may cause check to read b as -1 but then read a as still 0.
*/
class SetCheck {

    private int a = 0;
    private long b = 0;

    void set() {
        a = 1;
        b = -1;
    }

    boolean check() {
        return ((b == 0) || (b == -1 && a == 1));
    }

}
