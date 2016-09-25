package chapter_1.section_4;

// define an exception to throw if the invariant fails
public class AssertionError extends Error {

    public AssertionError() {
        super();
    }

    public AssertionError(String message) {
        super(message);
    }
}
