package domain.manager;

import domain.FENTranslator;
import domain.Solution;

import java.util.LinkedList;
import java.util.List;

public class FENManager {
    public static List<String> distinct() {
        return fenStrings(SolutionManager.distinct(QueenManager.generateAllPlacements()));
    }

    private static List<String> fenStrings(List<Solution> solutions) {
        List<String> fenStrings = new LinkedList<String>();
        for(Solution solution : solutions) {

            FENTranslator translator = new FENTranslator(solution.getSolutionList());
            fenStrings.add(translator.fen());
        }

        return fenStrings;
    }

    public static List<String> unique() {
        return fenStrings(SolutionManager.unique(QueenManager.generateAllPlacements()));
    }
}
