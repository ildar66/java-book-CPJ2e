package chapter_3.section_3;

// Layering Guards
class WaitingStack extends Stack {

    public synchronized void push(Object x) {
        super.push(x);
        notifyAll();
    }

    public synchronized Object waitingPop() throws InterruptedException {

        while (isEmpty()) {
            wait();
        }

        try {
            return super.pop();
        } catch (StackEmptyException cannothappen) {
            // only possible if pop contains a programming error
            throw new Error("Internal implementation error");
        }
    }

}
