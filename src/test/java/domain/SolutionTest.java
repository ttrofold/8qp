package domain;

import org.junit.Test;

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

        assertEquals(1, solution.getSize());
        assertEquals(Integer.valueOf(1), solution.get(0));
    }
}
