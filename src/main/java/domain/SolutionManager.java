package domain;

import domain.exceptions.NoSolutionException;
import domain.queen.Queen;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SolutionManager {
    public static List<Solution> transformPlacementToSolution(List<Queen> queens) {
        List<Solution> solutions = new LinkedList<Solution>();
        for(Queen queen : queens) {
            try {
                solutions.add(queen.solution());
            } catch(NoSolutionException ignored) {}
        }
        return solutions;
    }

    public static List<Solution> all(List<Queen> queens) {
        Set<Solution> filteredSolutions = new HashSet<Solution>(transformPlacementToSolution(queens));

        return new LinkedList<Solution>(filteredSolutions);
    }
}
