package chapter_2.section_4;

// used in Splitting Synchronization para
class AdjustableLoc {

    protected double x;
    protected double y;

    public AdjustableLoc(double initX, double initY) {
        x = initX;
        y = initY;
    }

    public synchronized double x() {
        return x;
    }

    public synchronized double y() {
        return y;
    }

    public synchronized void adjust() {
        x = longCalculation1();
        y = longCalculation2();
    }

    protected double longCalculation1() {
        return 1; /* ... */
    }

    protected double longCalculation2() {
        return 2; /* ... */
    }

}
