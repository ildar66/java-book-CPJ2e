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
package chapter_3.section_2;