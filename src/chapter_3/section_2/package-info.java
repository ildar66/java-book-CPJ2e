/*
 * Created by User on 06.10.2016.
 * Guarded Methods section
 *
 * Conservative check-and-act methods refuse to perform actions unless they can ensure that these
    actions will succeed, in part by first checking their preconditions. The three basic flavors reflect policy
    decisions surrounding failed preconditions:

 * Balking. Throwing an exception if the precondition fails. The exceptions thrown are conceptually
    different from those seen in optimistic methods: here, an exception indicates refusal, not failure. But
    these exceptions usually have the same consequences to clients.

 * Guarded suspension. Suspending the current method invocation (and its associated thread) until the
    precondition becomes true.

 * Time-outs. The range of cases falling between balking and suspension, where an upper bound is
    placed on how long to wait for the precondition to become true.
 */

/*

MONITOR MECHANICS:

WAIT. A wait invocation results in the following actions:
    • If the current thread has been interrupted, then the method exits immediately, throwing an
        InterruptedException. Otherwise, the current thread is blocked.

    • The JVM places the thread in the internal and otherwise inaccessible wait set associated with
        the target object.

    • The synchronization lock for the target object is released, but all other locks held by the
        thread are retained. A full release is obtained even if the lock is re-entrantly held due to nested
        synchronized calls on the target object. Upon later resumption, the lock status is fully restored.

NOTIFY. A notify invocation results in the following actions:
    • If one exists, an arbitrarily chosen thread, say T, is removed by the JVM from the internal
      wait set associated with the target object. There is no guarantee about which waiting thread
      will be selected when the wait set contains more than one thread — see § 3.4.1.5.

    • T must re-obtain the synchronization lock for the target object, which will always cause it to
      block at least until the thread calling notify releases the lock. It will continue to block if
      some other thread obtains the lock first.

    • T is then resumed from the point of its wait.

NOTIFYALL. A notifyAll works in the same way as notify except that the steps occur (in effect,
            simultaneously) for all threads in the wait set for the object. However,
            because they must acquire the lock, threads continue one at a time.

INTERRUPT. If Thread.interrupt is invoked for a thread suspended in a wait, the same
    notify mechanics apply, except that after re-acquiring the lock, the method throws an
    InterruptedException and the thread's interruption status is set to false. If an
    interrupt and a notify occur at about the same time, there is no guarantee about which action
    has precedence, so either result is possible. (Future revisions of JLS may introduce deterministic
    guarantees about these outcomes.)

TIMED WAITS. The timed versions of the wait method, wait(long msecs) and wait(long
    msecs, int nanosecs), take arguments specifying the desired maximum time to remain in the
    wait set. They operate in the same way as the untimed version except that if a wait has not been
    notified before its time bound, it is released automatically. There is no status indication differentiating
    waits that return via notifications versus time-outs. Counterintuitively, wait(0) and wait(0, 0)
    both have the special meaning of being equivalent to an ordinary untimed wait().

    A timed wait may resume an arbitrary amount of time after the requested bound due to thread
    contention, scheduling policies, and timer granularities. (There is no guarantee about granularity. Most
    JVM implementations have observed response times in the 1-20ms range for arguments less than
    1ms.)
 */
package chapter_3.section_2;