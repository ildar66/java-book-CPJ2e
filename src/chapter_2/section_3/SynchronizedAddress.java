package chapter_2.section_3;

/*
* Subclassing para
*
    When instances of a given class are always intended to be confined within others, there is no reason to
    synchronize their methods. But when some instances are confined and some are not, the safest practice
    is to synchronize them appropriately, even though locking is not required in all usage contexts.
*/
class SynchronizedAddress extends Address {

    // ...
    public synchronized String getStreet() {
        return super.getStreet();
    }

    public synchronized void setStreet(String s) {
        super.setStreet(s);
    }

    public synchronized void printLabel(java.io.OutputStream s) {
        super.printLabel(s);
    }

}
