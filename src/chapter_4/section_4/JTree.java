package chapter_4.section_4;

import EDU.oswego.cs.dl.util.concurrent.FJTask;

// Computation Trees
abstract class JTree extends FJTask {

    volatile double maxDiff; // for convergence check
}
