package chapter_3.section_1;

// Handlers para
class HandledService implements ServerWithException {

    final ServerWithException server = new ServerImpl();
    final ServiceExceptionHandler handler = new HandlerImpl();

    public void service() { // no throw clause
        try {
            server.service();
        } catch (ServiceException e) {
            handler.handle(e);
        }
    }
}
