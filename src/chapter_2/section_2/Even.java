package chapter_2.section_2;

/*
 * Without locking, the desired postcondition
 * may fail due to a storage conflict when two or more threads execute the next method of the same Even object.
*/
class Even {// Do not use

    private int n = 0;

    public int next() {// POST?: next is always even
        ++n;
        ++n;
        return n;
    }
}
