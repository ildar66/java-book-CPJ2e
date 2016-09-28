/**
 * Created by User on 28.09.2016.
 * Confinement section
 * Confinement employs encapsulation techniques to structurally guarantee that at most one activity at a
 * time can possibly access a given object. This statically ensures that the accessibility of a given
 * object is unique to a single thread without needing to rely on dynamic locking on each access.
 * The main tactic is to define methods and classes that establish leak-proof ownership domains
 * guaranteeing that only one thread, or one thread at a time, can ever access a confined object.
 */
package chapter_2.section_3;