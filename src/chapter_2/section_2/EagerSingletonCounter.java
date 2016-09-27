package chapter_2.section_2;

/*
    So, unless initialization is both very expensive and rarely needed, it is usually
        preferable to take the simpler approach of declaring a singleton as a static final field
*/
class EagerSingletonCounter {

    private final long initial;
    private long count;

    private EagerSingletonCounter() {
        initial = Math.abs(new java.util.Random().nextLong() / 2);
        count = initial;
    }

    private static final EagerSingletonCounter s = new EagerSingletonCounter();

    public static EagerSingletonCounter instance() {
        return s;
    }

    public synchronized long next() {
        return count++;
    }

    public synchronized void reset() {
        count = initial;
    }

}
