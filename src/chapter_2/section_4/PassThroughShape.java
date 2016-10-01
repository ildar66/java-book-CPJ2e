package chapter_2.section_4;

/**
 * @see Shape
 * Splitting classes to reduce granularity is a straightforward exercise in class refactoring:
 *
 * • Partition some functionality of a Host class into another class, say Helper.
 * • In the Host class, declare a final unique field referencing a helper that is initialized to a
 *      new Helper in the constructor. (In other words, strictly confine each helper in its host.)
 * • In the Host class, forward all appropriate methods to the Helper as open calls, using
 *      unsynchronized methods. This works because the methods are stateless with respect to the Host class.
 */
class PassThroughShape {

    protected final AdjustableLoc loc = new AdjustableLoc(0, 0);
    protected final AdjustableDim dim = new AdjustableDim(0, 0);

    public double x() {
        return loc.x();
    }

    public double y() {
        return loc.y();
    }

    public double width() {
        return dim.width();
    }

    public double height() {
        return dim.height();
    }

    public void adjustLocation() {
        loc.adjust();
    }

    public void adjustDimensions() {
        dim.adjust();
    }

}