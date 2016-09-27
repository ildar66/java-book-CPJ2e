package chapter_2.section_2;

/*
    This class represents a counter that could be used to assign global sequence numbers to objects,
    transactions, messages, etc., across different classes in an application.
*/
class LazySingletonCounter {

    private final long initial;
    private long count;

    private LazySingletonCounter() {
        initial = Math.abs(new java.util.Random().nextLong() / 2);
        count = initial;
    }

    private static LazySingletonCounter s = null;

    private static final Object classLock = LazySingletonCounter.class;

    public static LazySingletonCounter instance() {
        synchronized (classLock) {
            if (s == null)
                s = new LazySingletonCounter();
            return s;
        }
    }

    public long next() {
        synchronized (classLock) {
            return count++;
        }
    }

    public void reset() {
        synchronized (classLock) {
            count = initial;
        }
    }

}
