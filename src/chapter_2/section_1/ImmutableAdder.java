package chapter_2.section_1;

// Safety and liveness properties hold in classes possessing only final fields.
class ImmutableAdder {

    private final int offset;

    public ImmutableAdder(int a) {
        offset = a;
    }

    public int addOffset(int b) {
        return offset + b;
    }
}
