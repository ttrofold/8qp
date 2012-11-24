package domain;

import domain.exceptions.SolutionException;
import org.jmock.Expectations;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class SolutionTest extends AbstractMockTest {

    List<Integer> listMock = context.mock(List.class);

    @Test
    public void
    encapsulatesListWithNumericSolution() {
        expectFilledList();

        assertNotNull(new Solution(listMock).getSolutionList());
    }

    @Test
    public void
    canBeAugmentedWithNewValue() {
        Solution solution = new Solution();

        solution.augment(1);

        assertEquals(1, solution.getSize());
        assertEquals(Integer.valueOf(1), solution.get(0));
    }

    @Test(expected = SolutionException.class)
    public void
    canNotGetSolutionListUntilItIsFilled() {
        Solution solution = new Solution();

        solution.augment(1);

        solution.getSolutionList();
    }

    @Test(expected = SolutionException.class)
    public void
    cannotAugmentListPast8() {
        expectFilledList();

        new Solution(listMock).augment(1);
    }

    @Test(expected = SolutionException.class)
    public void
    cannotInitWithListWithSizePast8() {
         checking(new Expectations() {
             {
                 one(listMock).size();
                 will(returnValue(9));
             }
         });

        new Solution(listMock);
    }

    private void expectFilledList() {
        checking(new Expectations() {
            {
                exactly(2).of(listMock).size();
                will(returnValue(8));
            }
        });
    }

    @Test
    public void
    canRotateSolutionBy90() {
        List<Integer> list = new LinkedList<Integer>() {{
                add(1); add(5); add(3); add(2); add(4); add(7); add(0); add(6);
        }};
        assertEquals(
                new Solution(
                        new LinkedList<Integer>() {{
                                add(1); add(7); add(4); add(5); add(3); add(6); add(0); add(2);}}),
                new Solution(list).rotateBy90());
    }

    @Test
    public void
    canRotateSolutionBy180() {
        List<Integer> list = new LinkedList<Integer>() {{
            add(1); add(5); add(3); add(2); add(4); add(7); add(0); add(6);
        }};
        assertEquals(
                new Solution(
                        new LinkedList<Integer>() {{
                            add(1); add(7); add(0); add(3); add(5); add(4); add(2); add(6);}}),
                new Solution(list).rotateBy180());
    }


    @Test
    public void
    canRotateSolutionBy270() {
        List<Integer> list = new LinkedList<Integer>() {{
            add(1); add(5); add(3); add(2); add(4); add(7); add(0); add(6);
        }};
        assertEquals(
                new Solution(
                        new LinkedList<Integer>() {{
                            add(5); add(7); add(1); add(4); add(2); add(3); add(0); add(6);}}),
                new Solution(list).rotateBy270());
    }

    @Test
    public void
    canReflect() {
        List<Integer> list = new LinkedList<Integer>() {{
            add(1); add(5); add(3); add(2); add(4); add(7); add(0); add(6);
        }};
        assertEquals(
                new Solution(
                        new LinkedList<Integer>() {{
                            add(6); add(0); add(7); add(4); add(2); add(3); add(5); add(1);}}),
                new Solution(list).reflect());
    }
}
