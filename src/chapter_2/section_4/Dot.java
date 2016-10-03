package chapter_2.section_4;

/**
 * //Copy-on-Write para
 * Note that synchronization of some form is required here. Even though the point representation objects
 * are immutable, the loc reference is mutable. While synchronization of the accessor method
 * location might be loosened in accord with the considerations in § 2.4.1, the shiftX method
 * must be synchronized (or perhaps otherwise modified) in order to preclude multiple concurrent
 * executions in which different versions of loc are obtained when accessing loc.x() and loc.y().
 */
class Dot {

    protected ImmutablePoint loc;

    public Dot(int x, int y) {
        loc = new ImmutablePoint(x, y);
    }

    public synchronized ImmutablePoint location() {
        return loc;
    }

    protected synchronized void updateLoc(ImmutablePoint newLoc) {
        loc = newLoc;
    }

    public void moveTo(int x, int y) {
        updateLoc(new ImmutablePoint(x, y));
    }

    public synchronized void shiftX(int delta) {
        updateLoc(new ImmutablePoint(loc.x() + delta, loc.y()));
    }

}
