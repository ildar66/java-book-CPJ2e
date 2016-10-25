package chapter_4.section_3;

import java.io.IOException;

// Implementations
class FileReaderApp implements FileReaderClient { // Fragments

    protected FileReader reader = new AFileReader();

    public void readCompleted(String filename, byte[] data) {
        // ... use data ...
    }

    public void readFailed(String filename, IOException ex) {
        // ... deal with failure ...
    }

    public void actionRequiringFile() {
        reader.read("AppFile", this);
    }

    public void actionNotRequiringFile() {
    }
}
