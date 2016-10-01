package chapter_2.section_4;

//Isolating fields para
public class Person { // Fragments

    // ...
    protected final SynchronizedInt age = new SynchronizedInt(0);

    // protected final SynchronizedBoolean isMarried = new SynchronizedBoolean(false);

    // protected final SynchronizedDouble income = new SynchronizedDouble(0.0);

    public int getAge() {
        return age.get();
    }

    public void birthday() {
        age.increment();
    }
    // ...

}
