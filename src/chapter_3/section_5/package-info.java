/**
 * Created by User on 14.10.2016.
 * Joint Actions section
 * So far, this chapter has confined itself mainly to discussions of guarded actions that rely on the state of
 * a single object.
 * Joint action frameworks provide a more general setting to attack more general design problems.
 * From a high-level design perspective, joint actions are atomic guarded methods that involve
 * conditions and actions among multiple, otherwise independent participant objects.
 * They can be described abstractly as atomic methods involving two or more objects:
 * void jointAction(A a, B b) { // Pseudocode
 * WHEN (canPerformAction(a, b))
 * performAction(a, b);
 * }
 */
package chapter_3.section_5;