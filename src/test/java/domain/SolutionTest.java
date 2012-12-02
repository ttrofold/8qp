package domain;

import domain.exceptions.SolutionException;
import general.AbstractMockTest;
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

    @Test
    public void
    metadataContains7Stores() {
        List<List<Integer>> metadata = new Solution().getMetadata();
        assertEquals(7, metadata.size());
    }

    @Test
    public void
    shouldAddToMetaT90Key() {
        Solution solution = new Solution();

        solution.addMetadata(Solution.MetaKey.T90, 1);

        List<Integer> t90Metadata = solution.getMetadata(Solution.MetaKey.T90);

        assertEquals(1, t90Metadata.size());
        assertEquals(Integer.valueOf(1), t90Metadata.get(0));
    }


    @Test
    public void
    shouldAddToMetaT180Key() {
        Solution solution = new Solution();

        solution.addMetadata(Solution.MetaKey.T180, 1);

        List<Integer> t180Metadata = solution.getMetadata(Solution.MetaKey.T180);

        assertEquals(1, t180Metadata.size());
        assertEquals(Integer.valueOf(1), t180Metadata.get(0));
    }


    @Test
    public void
    shouldAddToMetaT270Key() {
        Solution solution = new Solution();

        solution.addMetadata(Solution.MetaKey.T270, 1);

        List<Integer> t270Metadata = solution.getMetadata(Solution.MetaKey.T270);

        assertEquals(1, t270Metadata.size());
        assertEquals(Integer.valueOf(1), t270Metadata.get(0));
    }

    @Test
    public void
    shouldAddToMetaR0Key() {
        Solution solution = new Solution();

        solution.addMetadata(Solution.MetaKey.R0, 1);

        List<Integer> r0Metadata = solution.getMetadata(Solution.MetaKey.R0);

        assertEquals(1, r0Metadata.size());
        assertEquals(Integer.valueOf(1), r0Metadata.get(0));
    }

    @Test
    public void
    shouldAddToMetaR90Key() {
        Solution solution = new Solution();

        solution.addMetadata(Solution.MetaKey.R90, 1);

        List<Integer> r90Metadata = solution.getMetadata(Solution.MetaKey.R90);

        assertEquals(1, r90Metadata.size());
        assertEquals(Integer.valueOf(1), r90Metadata.get(0));
    }

    @Test
    public void
    shouldAddToMetaR180Key() {
        Solution solution = new Solution();

        solution.addMetadata(Solution.MetaKey.R180, 1);

        List<Integer> r180Metadata = solution.getMetadata(Solution.MetaKey.R180);

        assertEquals(1, r180Metadata.size());
        assertEquals(Integer.valueOf(1), r180Metadata.get(0));
    }

    @Test
    public void
    shouldAddToMetaR270Key() {
        Solution solution = new Solution();

        solution.addMetadata(Solution.MetaKey.R270, 1);

        List<Integer> r270Metadata = solution.getMetadata(Solution.MetaKey.R270);

        assertEquals(1, r270Metadata.size());
        assertEquals(Integer.valueOf(1), r270Metadata.get(0));
    }

    @Test
    public void
    addingToR270ShouldNotAddToOtherKeys() {
        Solution solution = new Solution();

        solution.addMetadata(Solution.MetaKey.R270, 1);

        List<Integer> t90Metadata = solution.getMetadata(Solution.MetaKey.T90);
        List<Integer> t180Metadata = solution.getMetadata(Solution.MetaKey.T180);
        List<Integer> t270Metadata = solution.getMetadata(Solution.MetaKey.T270);
        List<Integer> r0Metadata = solution.getMetadata(Solution.MetaKey.R0);
        List<Integer> r90Metadata = solution.getMetadata(Solution.MetaKey.R90);
        List<Integer> r180Metadata = solution.getMetadata(Solution.MetaKey.R180);
        List<Integer> r270Metadata = solution.getMetadata(Solution.MetaKey.R270);

        assertEquals(0, t90Metadata.size());
        assertEquals(0, t180Metadata.size());
        assertEquals(0, t270Metadata.size());
        assertEquals(0, r0Metadata.size());
        assertEquals(0, r90Metadata.size());
        assertEquals(0, r180Metadata.size());
        assertEquals(1, r270Metadata.size());
        assertEquals(Integer.valueOf(1), r270Metadata.get(0));
    }

    @Test
    public void
    addingToR180ShouldNotAddToOtherKeys() {
        Solution solution = new Solution();

        solution.addMetadata(Solution.MetaKey.R180, 1);

        List<Integer> t90Metadata = solution.getMetadata(Solution.MetaKey.T90);
        List<Integer> t180Metadata = solution.getMetadata(Solution.MetaKey.T180);
        List<Integer> t270Metadata = solution.getMetadata(Solution.MetaKey.T270);
        List<Integer> r0Metadata = solution.getMetadata(Solution.MetaKey.R0);
        List<Integer> r90Metadata = solution.getMetadata(Solution.MetaKey.R90);
        List<Integer> r180Metadata = solution.getMetadata(Solution.MetaKey.R180);
        List<Integer> r270Metadata = solution.getMetadata(Solution.MetaKey.R270);

        assertEquals(0, t90Metadata.size());
        assertEquals(0, t180Metadata.size());
        assertEquals(0, t270Metadata.size());
        assertEquals(0, r0Metadata.size());
        assertEquals(0, r90Metadata.size());
        assertEquals(1, r180Metadata.size());
        assertEquals(0, r270Metadata.size());
        assertEquals(Integer.valueOf(1), r180Metadata.get(0));
    }

    @Test
    public void
    addingToR90ShouldNotAddToOtherKeys() {
        Solution solution = new Solution();

        solution.addMetadata(Solution.MetaKey.R90, 1);

        List<Integer> t90Metadata = solution.getMetadata(Solution.MetaKey.T90);
        List<Integer> t180Metadata = solution.getMetadata(Solution.MetaKey.T180);
        List<Integer> t270Metadata = solution.getMetadata(Solution.MetaKey.T270);
        List<Integer> r0Metadata = solution.getMetadata(Solution.MetaKey.R0);
        List<Integer> r90Metadata = solution.getMetadata(Solution.MetaKey.R90);
        List<Integer> r180Metadata = solution.getMetadata(Solution.MetaKey.R180);
        List<Integer> r270Metadata = solution.getMetadata(Solution.MetaKey.R270);

        assertEquals(0, t90Metadata.size());
        assertEquals(0, t180Metadata.size());
        assertEquals(0, t270Metadata.size());
        assertEquals(0, r0Metadata.size());
        assertEquals(1, r90Metadata.size());
        assertEquals(0, r180Metadata.size());
        assertEquals(0, r270Metadata.size());
        assertEquals(Integer.valueOf(1), r90Metadata.get(0));
    }

    @Test
    public void
    addingToR0ShouldNotAddToOtherKeys() {
        Solution solution = new Solution();

        solution.addMetadata(Solution.MetaKey.R0, 1);

        List<Integer> t90Metadata = solution.getMetadata(Solution.MetaKey.T90);
        List<Integer> t180Metadata = solution.getMetadata(Solution.MetaKey.T180);
        List<Integer> t270Metadata = solution.getMetadata(Solution.MetaKey.T270);
        List<Integer> r0Metadata = solution.getMetadata(Solution.MetaKey.R0);
        List<Integer> r90Metadata = solution.getMetadata(Solution.MetaKey.R90);
        List<Integer> r180Metadata = solution.getMetadata(Solution.MetaKey.R180);
        List<Integer> r270Metadata = solution.getMetadata(Solution.MetaKey.R270);

        assertEquals(0, t90Metadata.size());
        assertEquals(0, t180Metadata.size());
        assertEquals(0, t270Metadata.size());
        assertEquals(1, r0Metadata.size());
        assertEquals(0, r90Metadata.size());
        assertEquals(0, r180Metadata.size());
        assertEquals(0, r270Metadata.size());
        assertEquals(Integer.valueOf(1), r0Metadata.get(0));
    }

    @Test
    public void
    addingToT270ShouldNotAddToOtherKeys() {
        Solution solution = new Solution();

        solution.addMetadata(Solution.MetaKey.T270, 1);

        List<Integer> t90Metadata = solution.getMetadata(Solution.MetaKey.T90);
        List<Integer> t180Metadata = solution.getMetadata(Solution.MetaKey.T180);
        List<Integer> t270Metadata = solution.getMetadata(Solution.MetaKey.T270);
        List<Integer> r0Metadata = solution.getMetadata(Solution.MetaKey.R0);
        List<Integer> r90Metadata = solution.getMetadata(Solution.MetaKey.R90);
        List<Integer> r180Metadata = solution.getMetadata(Solution.MetaKey.R180);
        List<Integer> r270Metadata = solution.getMetadata(Solution.MetaKey.R270);

        assertEquals(0, t90Metadata.size());
        assertEquals(0, t180Metadata.size());
        assertEquals(1, t270Metadata.size());
        assertEquals(0, r0Metadata.size());
        assertEquals(0, r90Metadata.size());
        assertEquals(0, r180Metadata.size());
        assertEquals(0, r270Metadata.size());
        assertEquals(Integer.valueOf(1), t270Metadata.get(0));
    }

    @Test
    public void
    addingToT180ShouldNotAddToOtherKeys() {
        Solution solution = new Solution();

        solution.addMetadata(Solution.MetaKey.T180, 1);

        List<Integer> t90Metadata = solution.getMetadata(Solution.MetaKey.T90);
        List<Integer> t180Metadata = solution.getMetadata(Solution.MetaKey.T180);
        List<Integer> t270Metadata = solution.getMetadata(Solution.MetaKey.T270);
        List<Integer> r0Metadata = solution.getMetadata(Solution.MetaKey.R0);
        List<Integer> r90Metadata = solution.getMetadata(Solution.MetaKey.R90);
        List<Integer> r180Metadata = solution.getMetadata(Solution.MetaKey.R180);
        List<Integer> r270Metadata = solution.getMetadata(Solution.MetaKey.R270);

        assertEquals(0, t90Metadata.size());
        assertEquals(1, t180Metadata.size());
        assertEquals(0, t270Metadata.size());
        assertEquals(0, r0Metadata.size());
        assertEquals(0, r90Metadata.size());
        assertEquals(0, r180Metadata.size());
        assertEquals(0, r270Metadata.size());
        assertEquals(Integer.valueOf(1), t180Metadata.get(0));
    }

    @Test
    public void
    addingToT90ShouldNotAddToOtherKeys() {
        Solution solution = new Solution();

        solution.addMetadata(Solution.MetaKey.T90, 1);

        List<Integer> t90Metadata = solution.getMetadata(Solution.MetaKey.T90);
        List<Integer> t180Metadata = solution.getMetadata(Solution.MetaKey.T180);
        List<Integer> t270Metadata = solution.getMetadata(Solution.MetaKey.T270);
        List<Integer> r0Metadata = solution.getMetadata(Solution.MetaKey.R0);
        List<Integer> r90Metadata = solution.getMetadata(Solution.MetaKey.R90);
        List<Integer> r180Metadata = solution.getMetadata(Solution.MetaKey.R180);
        List<Integer> r270Metadata = solution.getMetadata(Solution.MetaKey.R270);

        assertEquals(1, t90Metadata.size());
        assertEquals(0, t180Metadata.size());
        assertEquals(0, t270Metadata.size());
        assertEquals(0, r0Metadata.size());
        assertEquals(0, r90Metadata.size());
        assertEquals(0, r180Metadata.size());
        assertEquals(0, r270Metadata.size());
        assertEquals(Integer.valueOf(1), t90Metadata.get(0));
    }

    @Test
    public void
    requesMetadataFromEmptyStore() {
        Solution solution = new Solution();
        List<Integer> metadata = solution.getMetadata(Solution.MetaKey.R0);
        assertEquals(0, metadata.size());
    }
}
