package chapter_3.section_1;

// Handlers para
class ExceptionEvent extends java.util.EventObject {

    public final Throwable theException;

    public ExceptionEvent(Object src, Throwable ex) {
        super(src);
        theException = ex;
    }
}
