package chapter_4.section_3;

// Scheduling Services
class DiskWriteTask extends DiskTask {

    DiskWriteTask(int c, byte[] b) {
        super(c, b);
    }

    void access() throws Failure { /* ... raw write ... */
    }
}
