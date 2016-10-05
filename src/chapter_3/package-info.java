/*
 * Created by User on 05.10.2016.
 * State Dependence chapter
 *
 * There are two general approaches to the design and implementation of any state-dependent action, that
 * stem from liveness-first versus safety-first design perspectives:
 *
 *      - Optimistic try-and-see methods can always be tried when invoked, but do not always succeed, and
 *      thus may have to deal with failure.
 *
 *      - Conservative check-and-act methods refuse to proceed unless preconditions hold. When preconditions
 *      do hold, the actions always succeed.
 */
package chapter_3;