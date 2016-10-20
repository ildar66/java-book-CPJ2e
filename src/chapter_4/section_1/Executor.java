package chapter_4.section_1;

// Executors

/**
 * The coding style seen in class @see {@link ThreadPerMessageHost} can become a problem because of
 * its direct reliance on class Thread. Such usages can make it more difficult to adjust thread
 * initialization parameters, as well as thread-specific data (see § 2.3.2) used across an application.
 * This can be avoided by creating an interface, say:
 */
interface Executor {

    void execute(Runnable r);
}
