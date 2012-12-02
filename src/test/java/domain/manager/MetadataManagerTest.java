package domain.manager;

import domain.Solution;
import general.AbstractMockTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class MetadataManagerTest extends AbstractMockTest {

    private Solution solution1;
    private Solution solution2;

    private String metadata1;
    private String metadata2;
    @Before
    public void
    set() {
        solution1 = new Solution();
        solution1.addMetadata(Solution.MetaKey.R0, 2);
        solution1.addMetadata(Solution.MetaKey.R90, 3);
        solution1.addMetadata(Solution.MetaKey.R180, 5);
        solution1.addMetadata(Solution.MetaKey.R270, 8);
        solution1.addMetadata(Solution.MetaKey.T90, 18);
        solution1.addMetadata(Solution.MetaKey.T180, 4);
        solution1.addMetadata(Solution.MetaKey.T270, 7);

        solution2 = new Solution();
        solution2.addMetadata(Solution.MetaKey.T90, 4);
        solution2.addMetadata(Solution.MetaKey.T90, 11);
        solution2.addMetadata(Solution.MetaKey.R0, 3);

        metadata1 = "T90: [18]\nT180: [4]\nT270: [7]\nR0: [2]\nR90: [3]\nR180: [5]\nR270: [8]\n";
        metadata2 = "T90: [4, 11]\nR0: [3]\n";
    }

    @Test
    public void
    canConvertSolutionMetadataToReadableMetadata() {
        String metadata = MetadataManager.convert(solution1);

        assertEquals(metadata1, metadata);
    }

    @Test
    public void
    multipleValuesAtKey() {

        String metadata = MetadataManager.convert(solution2);
        assertEquals(metadata2, metadata);
    }

    @Test
    public void
    canConvertListOfSolutions() {
        List<Solution> solutions = new LinkedList<Solution>();
        solutions.add(solution1);
        solutions.add(solution2);

        List<String> result = MetadataManager.all(solutions);

        assertEquals(Arrays.asList(metadata1, metadata2), result);
    }
}
