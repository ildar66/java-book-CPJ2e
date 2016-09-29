/**
 * Created by User on 28.09.2016.
 * Confinement section
 * Confinement employs encapsulation techniques to structurally guarantee that at most one activity at a
 * time can possibly access a given object. This statically ensures that the accessibility of a given
 * object is unique to a single thread without needing to rely on dynamic locking on each access.
 * The main tactic is to define methods and classes that establish leak-proof ownership domains
 * guaranteeing that only one thread, or one thread at a time, can ever access a confined object.
 *
 * There are four categories to check to see if a reference r to an object x can escape
 * from a method m executing within some activity:
     • m passes r as an argument in a method invocation or object constructor.
     • m passes r as the return value from a method invocation.
     • m records r in some field that is accessible from another activity (in the most flagrant case,
     static fields that are accessible anywhere).
     • m releases (in any of the above ways) another reference that can in turn be traversed to access r.
 */
package chapter_2.section_3;