package chapter_4.section_3;

import java.io.IOException;

// Interfaces
interface FileReaderClient {

    void readCompleted(String filename, byte[] data);

    void readFailed(String filename, IOException ex);
}
