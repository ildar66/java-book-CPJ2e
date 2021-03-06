package chapter_2.section_4;

// ised in Splitting Synchronization para
class AdjustableDim {

    protected double width;
    protected double height;

    public AdjustableDim(double initW, double initH) {
        width = initW;
        height = initH;
    }

    public synchronized double width() {
        return width;
    }

    public synchronized double height() {
        return height;
    }

    public synchronized void adjust() {
        width = longCalculation3();
        height = longCalculation4();
    }

    protected double longCalculation3() {
        return 3; /* ... */
    }

    protected double longCalculation4() {
        return 4; /* ... */
    }

}
