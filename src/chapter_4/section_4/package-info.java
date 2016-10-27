/*
 * Created by User on 26.10.2016.
 * Parallel Decomposition section.
 * We focus on three families of task-based designs, fork/join parallelism, computation trees, and
 * barriers:
 *
 *  Fork/Join
 *  Fork/join decomposition relies on parallel versions of divide-and-conquer techniques familiar in
    sequential algorithm design. Solutions take the form:
        pseudoclass Solver { // Pseudocode
            // ...
            Result solve(Param problem) {
                if (problem.size <= BASE_CASE_SIZE)
                    return directlySolve(problem);
                else {
                    Result l, r;
                    IN-PARALLEL {
                        l = solve(lefthalf(problem));
                        r = solve(rightHalf(problem));
                    }
                    return combine(l, r);
                }
            }
        }
 */
package chapter_4.section_4;