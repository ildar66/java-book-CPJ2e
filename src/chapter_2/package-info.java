/**
 * Created by User on 26.09.2016.
 * Exclusion chapter:
 *
 * Programming techniques and design patterns achieve exclusion by preventing multiple threads from concurrently modifying
 *      or acting upon object representations. All approaches rely on one or more of three basic strategies:
 *
 * Eliminating:
 *      the need for some or all exclusion control by ensuring that methods never modify an object's representation,
 *      so that the object cannot enter inconsistent states.
 *
 * Dynamically:
 *      ensuring that only one thread at a time can access object state, by protecting objects with locks and related constructs.
 *
 * Structurally:
 *      ensuring that only one thread (or only one thread at a time) can ever use a given object, by hiding or restricting access to it.
 */
package chapter_2;