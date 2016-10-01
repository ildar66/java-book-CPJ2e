package chapter_2.section_4;

// Splitting Synchronization para

/**
 * A set of synchronized operations might deadlock or present other liveness or lock-based performance
 * problems if they were all waiting for the single synchronization lock associated with a single object.
 * But they might be deadlock-free and/or run more efficiently if they are waiting on multiple distinct locks.
 * @see PassThroughShape
 */
class Shape {

    protected double x = 0.0;
    protected double y = 0.0;
    protected double width = 0.0;
    protected double height = 0.0;

    public synchronized double x() {
        return x;
    }

    public synchronized double y() {
        return y;
    }

    public synchronized double width() {
        return width;
    }

    public synchronized double height() {
        return height;
    }

    public synchronized void adjustLocation() {
        x = 1; // longCalculation1();
        y = 2; // longCalculation2();
    }

    public synchronized void adjustDimensions() {
        width = 3; // longCalculation3();
        height = 4; // longCalculation4();
    }

    // ...

}
