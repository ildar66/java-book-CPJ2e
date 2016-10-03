package chapter_2.section_4;

/**
 * //Copy-on-Write para
 * @see Dot
 */
class ImmutablePoint {

    private final int x;
    private final int y;

    public ImmutablePoint(int initX, int initY) {
        x = initX;
        y = initY;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

}
