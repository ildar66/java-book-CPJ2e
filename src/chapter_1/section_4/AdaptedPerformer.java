package chapter_1.section_4;

// Adapter class that translates away the incompatibilities
// (names of their methods might be slightly different from those defined in the interface).
class AdaptedPerformer implements Runnable {

    private final Performer adaptee;

    public AdaptedPerformer(Performer p) {
        adaptee = p;
    }

    @Override
    public void run() {
        adaptee.perform();
    }

}
