/*
 * Created by User on 19.10.2016.
 * Creating Threads chapter.
 *
 * It is impossible to categorize all the ways to exploit the functionality associated with threads. But two
 general approaches can be distinguished by their points of view on the statement:
 new Thread(aRunnable).start();
 Is this a fancy way to invoke a method (i.e., a Runnable's run method), or is it a way to create a
 fancy object (i.e., a new instance of class Thread)? Clearly it is both, but focusing on one aspect
 versus the other leads to two approaches to using threads that were implicit in discussions in Chapter
 1:
    Task-based Here, the main reason to use a thread is to asynchronously invoke a method that performs
 some task. The task might range from a single method to an entire session. Thread-based techniques
 can support message-passing schemes that escape the limitations of pure procedural calls. Task-based
 designs are seen in event frameworks, parallel computation, and IO-intensive systems.
    Actor-based Here, the main reason to use a thread is to create and set into motion a new autonomous,
 active, process-like object. This object may in turn react to external events, interact with other actors,
 and so on. Actor-based designs are seen in reactive, control, and distributed systems. They are also the
 focus of most formal approaches to concurrency.
 */
package chapter_4;