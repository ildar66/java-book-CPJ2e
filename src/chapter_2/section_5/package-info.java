/**
 * Created by User on 03.10.2016.
 * Using Lock Utilities section
 *
 * Built-in synchronized methods and blocks suffice for many lock-based applications, but they
 * have the following limitations:
 * • There is no way to back off from an attempt to acquire a lock if it is already held, to give up
 *      after waiting for a specified time, or to cancel a lock attempt after an interrupt.
 *      This can make it difficult to recover from liveness problems.
 * • There is no way to alter the semantics of a lock, for example with respect to reentrancy, read
 *      versus write protection, or fairness.
 * • There is no access control for synchronization. Any method can perform
 *      synchronized(obj) for any accessible object, thus leading to potential denial-ofservice
 *      problems caused by the holding of needed locks.
 * • Synchronization within methods and blocks limits use to strict block-structured locking. For
 *      example, you cannot acquire a lock in one method and release it in another.
 */
package chapter_2.section_5;