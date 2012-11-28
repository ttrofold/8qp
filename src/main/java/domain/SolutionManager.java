package domain;

import domain.exceptions.NoSolutionException;
import domain.queen.Queen;

import java.util.LinkedHashSet;
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
        Set<Solution> filteredSolutions = new LinkedHashSet<Solution>(transformPlacementToSolution(queens));

        return new LinkedList<Solution>(filteredSolutions);
    }

    public static List<Solution> distinct(LinkedList<Queen> queens) {
        List<Solution> all = new LinkedList<Solution>(SolutionManager.all(queens));

        for(Solution solution: all) {
            for(Solution solution0 : all) {
                if(solution.rotateBy90().equals(solution0)) {
                    solution.addMetadata(Solution.MetaKey.T90, all.lastIndexOf(solution0));
                }
                if(solution.rotateBy180().equals(solution0)) {
                    solution.addMetadata(Solution.MetaKey.T180, all.lastIndexOf(solution0));
                }
                if(solution.rotateBy270().equals(solution0)) {
                    solution.addMetadata(Solution.MetaKey.T270, all.lastIndexOf(solution0));
                }
                if(solution.reflect().equals(solution0)) {
                    solution.addMetadata(Solution.MetaKey.R0, all.lastIndexOf(solution0));
                }
                if(solution.reflect().rotateBy90().equals(solution0)) {
                    solution.addMetadata(Solution.MetaKey.R90, all.lastIndexOf(solution0));
                }
                if(solution.reflect().rotateBy180().equals(solution0)) {
                    solution.addMetadata(Solution.MetaKey.R180, all.lastIndexOf(solution0));
                }
                if(solution.reflect().rotateBy270().equals(solution0)) {
                    solution.addMetadata(Solution.MetaKey.R270, all.lastIndexOf(solution0));
                }
            }
        }
        return all;
    }

    public static List<Solution> unique(List<Queen> queens) {
        LinkedList<Solution> all = new LinkedList<Solution>(SolutionManager.all(queens));
        List<Solution> f = new LinkedList<Solution>();
        for(Solution s : all) {
            if(!f.contains(s.rotateBy90())
                    && !f.contains(s.rotateBy180())
                    && !f.contains(s.rotateBy270())
                    && !f.contains(s.reflect())
                    && !f.contains(s.rotateBy90().reflect())
                    && !f.contains(s.rotateBy180().reflect())
                    && !f.contains(s.rotateBy270().reflect()))
                f.add(s);
        }
        return f;
    }
}
