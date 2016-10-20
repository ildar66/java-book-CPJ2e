package chapter_4.section_1;

/**
 * // Executors
 * @see HostWithExecutor
 * Interface @see {@link Executor} can be implemented with classes such as:
 */
public class PlainThreadExecutor implements Executor {

    public void execute(Runnable r) {
        new Thread(r).start();
    }
}
