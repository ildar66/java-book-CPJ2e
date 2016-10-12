package chapter_3.section_3;

/**
 * // Confinement and Nested Monitors
 *
 * Guarded suspension makes sense when you believe that other threads can eventually unblock a
 * wait. But here, the Host class structurally precludes other threads from executing code that could
 * do so. Problems here stem from the fact that any thread waiting in a wait set retains all of its locks
 * except that of the object in whose wait set it was placed.
 *
 * For example, suppose that in thread T a call is made to host.rely causing it to block within part.
 * The lock to host is retained while T is blocked: no other thread will ever get a chance to unblock
 * it via host.set
 * @see OwnedPartWithGuard
 */
class Host {

    protected final PartWithGuard part = new PartWithGuard();

    synchronized void rely() throws InterruptedException {
        part.await();
    }

    synchronized void set(boolean c) {
        part.signal(c);
    }

}
