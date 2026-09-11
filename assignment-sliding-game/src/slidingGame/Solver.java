package slidingGame;

import java.util.*;

/**
 * A class that implements a breadth-first search algorithm for finding the
 * Configurations for which the isSolution predicate holds
 */
public class Solver {
	// A queue for maintaining states that are not visited yet.
	private final Queue<Configuration> toExamine;
    private final Set<Configuration> visited;


        public Solver(Configuration initial) {
            toExamine = new PriorityQueue<>();
            visited = new HashSet<>();
            toExamine.add(initial);
			visited.add(initial);
        }
	/**
	 * A skeleton implementation of the solver
	 *
	 * @return a string representation of the solution
	 */
	public String solve() {
        while (!toExamine.isEmpty()) {
            Configuration next = toExamine.remove();
            if (next.isSolution()) {
                return next.pathFromRoot().stream()
                        .map(Configuration::toString)
                    .collect(java.util.stream.Collectors.joining("\n")) + "\n";
            } else {
                next.successors().stream()
                        .filter(visited::add)
                        .forEach(toExamine::add);
            }
        }
        return "Failure!";
    }

}
