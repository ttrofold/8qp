package domain;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class SolutionTest extends AbstractMockTest {

    @Test
    public void
    encapsulatesListWithNumericSolution() {
        assertNotNull(new Solution().getSolutionList());
    }

    @Test
    public void
    canBeAugmentedWithNewValue() {
        Solution solution = new Solution();

        solution.augment(1);

        List<Integer> solutionList = solution.getSolutionList();
        assertEquals(1, solutionList.size());
        assertEquals(Integer.valueOf(1), solutionList.get(0));
    }
}
