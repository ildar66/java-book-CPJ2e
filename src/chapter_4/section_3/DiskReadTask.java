package chapter_4.section_3;

// Scheduling Services
class DiskReadTask extends DiskTask {

    DiskReadTask(int c, byte[] b) {
        super(c, b);
    }

    void access() throws Failure { /* ... raw read ... */
    }
}
