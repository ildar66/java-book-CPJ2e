package chapter_4.section_3;

// Scheduling Services
interface Disk {

    void read(int cylinderNumber, byte[] buffer) throws Failure;

    void write(int cylinderNumber, byte[] buffer) throws Failure;
}
