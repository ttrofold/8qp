package domain.manager;

import domain.Solution;

import java.util.LinkedList;
import java.util.List;

public class MetadataManager {
    public static String convert(Solution solution) {
        StringBuffer s = new StringBuffer();
        for(Solution.MetaKey key : Solution.MetaKey.values()) {
            if(!solution.getMetadata(key).isEmpty()) {
                s.append(key.name()).append(": ").append(solution.getMetadata(key)).append("\n");
            }
        }
        return s.toString();
    }

    public static List<String> all(List<Solution> solutions) {
        List<String> metadata = new LinkedList<String>();

        for(Solution solution : solutions) {
            metadata.add(MetadataManager.convert(solution));
        }

        return metadata;
    }

    // Untested method, using the tested API.
    public static List<String> external() {
        return MetadataManager.all(SolutionManager.distinct(QueenManager.generateAllPlacements()));
    }
}
