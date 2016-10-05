/*
 * Created by User on 05.10.2016.
 *
 * Dealing with Failure section
 *
 * Methods may throw exceptions (as well as set status indicators or issue notifications) when they have
 * detected that their intended effects or postconditions cannot be attained.
 * There are six general responses to such failed actions:
        * abrupt termination,
        * continuation (ignoring failures),
        * rollback,
        * rollforward,
        * retry, and
        * delegation to handlers.
 * Abrupt termination and continuation are the two most extreme responses.
 * Rollback and roll-forward are intermediate options that ensure that objects maintain consistent states.
 * Retries locally contain failure points. Delegation allows cooperative
 * responses to failure across objects and activities.
 */
package chapter_3.section_1;