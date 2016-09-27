package chapter_2.section_2;

/*
    Simpler yet, if there is no compelling reason to rely on instances, you can instead define and use a
        version with all static methods,
*/
class StaticCounter {

    private static final long initial = Math.abs(new java.util.Random().nextLong() / 2);
    private static long count = initial;

    private StaticCounter() {
    } // disable instance construction

    public static synchronized long next() {
        return count++;
    }

    public static synchronized void reset() {
        count = initial;
    }
}
