package chapter_3.section_1;

import java.util.Iterator;

import chapter_2.section_4.CopyOnWriteArrayList;

/*
// Handlers para
// An inverse style of conversion, of events to exceptions, is used in the java.beans package
*/
class ServiceIssuingExceptionEvent {

    // Incomplete
    // ...
    private final CopyOnWriteArrayList handlers = new CopyOnWriteArrayList();

    public void addHandler(ExceptionEventListener h) {
        handlers.add(h);
    }

    public void service() {
        // ... if ( /* failed */ )
        boolean failed = true;
        if (failed) {
            Throwable ex = new ServiceException();
            ExceptionEvent ee = new ExceptionEvent(this, ex);

            for (Iterator<?> it = handlers.iterator(); it.hasNext(); ) {
                ExceptionEventListener l = (ExceptionEventListener) (it.next());
                l.exceptionOccured(ee);
            }
        }
    }

}
