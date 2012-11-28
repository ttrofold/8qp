package domain;

import domain.queen.PhantomQueen;
import domain.queen.Queen;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class SolutionManagerTest extends AbstractMockTest {

    @Test
    public void
    shouldTransformPlacementsToSolutions() {
        List<Solution> solutions = SolutionManager.transformPlacementToSolution(new LinkedList<Queen>(){{add(new Queen(3, 7,
                new Queen(1, 6, new Queen(7, 5, new Queen(5, 4, new Queen(0, 3, new Queen(2, 2, new Queen(4, 1,
                        new Queen(6, 0, PhantomQueen.INSTANCE)))))))));}});

        assertEquals(new LinkedList<Solution>() {{add(new Solution(new LinkedList<Integer>() {{
            add(6); add(4); add(2); add(0); add(5); add(7); add(1); add(3);
        }}));}}, solutions);
    }

    @Test
    public void
    shouldTransformMultiplePlacementsIntoSolutions() {
        List<Solution> solutions = SolutionManager.transformPlacementToSolution(new LinkedList<Queen>(){{add(new Queen(3, 7,
                new Queen(1, 6, new Queen(7, 5, new Queen(5, 4, new Queen(0, 3, new Queen(2, 2, new Queen(4, 1,
                        new Queen(6, 0, PhantomQueen.INSTANCE)))))))));
        add(new Queen(5, 7,
                    new Queen(3, 6, new Queen(6, 5, new Queen(0, 4, new Queen(2, 3, new Queen(4, 2, new Queen(1, 1,
                            new Queen(7, 0, PhantomQueen.INSTANCE)))))))));}});

        assertEquals(new LinkedList<Solution>() {{add(new Solution(new LinkedList<Integer>() {{
            add(6); add(4); add(2); add(0); add(5); add(7); add(1); add(3);
        }})); add(new Solution(new LinkedList<Integer>(){{
            add(7); add(1); add(4); add(2); add(0); add(6); add(3); add(5);}}));}}, solutions);
    }

    @Test
    public void
    shouldHandleNoSolutionException() {
        List<Solution> solutions = SolutionManager.transformPlacementToSolution(new LinkedList<Queen>(){{add(new Queen(7, 7,
                new Queen(7, 6, new Queen(7, 5, new Queen(7, 4, new Queen(7, 3, new Queen(7, 2, new Queen(7, 1,
                        new Queen(7, 0, PhantomQueen.INSTANCE)))))))));}});

        assertTrue(solutions.isEmpty());
    }

    @Test
    public void
    shouldFilterNoSolutionCasesWhenMultiplePlacements() {
        List<Solution> solutions = SolutionManager.transformPlacementToSolution(new LinkedList<Queen>(){{add(new Queen(3, 7,
                new Queen(1, 6, new Queen(7, 5, new Queen(5, 4, new Queen(0, 3, new Queen(2, 2, new Queen(4, 1,
                        new Queen(6, 0, PhantomQueen.INSTANCE)))))))));
            add(new Queen(7, 7,
                    new Queen(7, 6, new Queen(7, 5, new Queen(7, 4, new Queen(7, 3, new Queen(7, 2, new Queen(7, 1,
                            new Queen(7, 0, PhantomQueen.INSTANCE)))))))));}});

        assertEquals(new LinkedList<Solution>() {{add(new Solution(new LinkedList<Integer>() {{
            add(6); add(4); add(2); add(0); add(5); add(7); add(1); add(3);
        }}));}}, solutions);
    }

    @Test
    public void
    allShouldFilterRepeatingSolutions() {
        List<Solution> solutions = SolutionManager.all(new LinkedList<Queen>(){{add(new Queen(3, 7,
                new Queen(1, 6, new Queen(7, 5, new Queen(5, 4, new Queen(0, 3, new Queen(2, 2, new Queen(4, 1,
                        new Queen(6, 0, PhantomQueen.INSTANCE)))))))));
            add(new Queen(3, 7,
                    new Queen(1, 6, new Queen(7, 5, new Queen(5, 4, new Queen(0, 3, new Queen(2, 2, new Queen(4, 1,
                            new Queen(6, 0, PhantomQueen.INSTANCE)))))))));}});

        assertEquals(new LinkedList<Solution>() {{add(new Solution(new LinkedList<Integer>() {{
            add(6); add(4); add(2); add(0); add(5); add(7); add(1); add(3);
        }}));}}, solutions);
    }

    @Test
    public void
    shouldReturn92Solutions() {
        assertEquals(92, SolutionManager.all(QueenManager.generateAllPlacements()).size());
    }

    @Test
    public void
    distinctShouldMarkTurn90() {
        List<Solution> solutions = SolutionManager.distinct(new LinkedList<Queen>(){{add(new Queen(3, 7,
                new Queen(0, 6, new Queen(4, 5, new Queen(7, 4, new Queen(5, 3, new Queen(2, 2, new Queen(6, 1,
                        new Queen(1, 0, PhantomQueen.INSTANCE)))))))));
            add(new Queen(3, 7,
                    new Queen(0, 6, new Queen(4, 5, new Queen(2, 4, new Queen(0, 3, new Queen(5, 2, new Queen(7, 1,
                            new Queen(1, 0, PhantomQueen.INSTANCE)))))))));
            }});

        assertEquals(2, solutions.size());

        Solution firstSolution = solutions.get(0);
        Solution secondSolution = solutions.get(1);

        List<Integer> firstT90 = firstSolution.getMetadata(Solution.MetaKey.T90);
        List<Integer> secondT90 = secondSolution.getMetadata(Solution.MetaKey.T90);
        assertEquals(0, secondT90.size());
        assertEquals(1, firstT90.size());
        assertTrue(firstT90.get(0).equals(1));
    }
    @Test
    public void
    distinctShouldMarkTurn180() {
        List<Solution> solutions = SolutionManager.distinct(new LinkedList<Queen>(){{add(new Queen(3, 7,
                new Queen(0, 6, new Queen(4, 5, new Queen(7, 4, new Queen(5, 3, new Queen(2, 2, new Queen(6, 1,
                        new Queen(1, 0, PhantomQueen.INSTANCE)))))))));
            add(new Queen(6, 7,
                    new Queen(1, 6, new Queen(5, 5, new Queen(2, 4, new Queen(0, 3, new Queen(3, 2, new Queen(7, 1,
                            new Queen(4, 0, PhantomQueen.INSTANCE)))))))));
        }});

        assertEquals(2, solutions.size());

        Solution firstSolution = solutions.get(0);
        Solution secondSolution = solutions.get(1);

        List<Integer> firstT180 = firstSolution.getMetadata(Solution.MetaKey.T180);
        List<Integer> secondT180 = secondSolution.getMetadata(Solution.MetaKey.T180);
        assertEquals(1, secondT180.size());
        assertEquals(1, firstT180.size());
        assertTrue(firstT180.get(0).equals(1));
        assertTrue(secondT180.get(0).equals(0));
    }

    @Test
    public void
    distinctShouldMarkTurn270() {
        List<Solution> solutions = SolutionManager.distinct(new LinkedList<Queen>(){{add(new Queen(3, 7,
                new Queen(0, 6, new Queen(4, 5, new Queen(7, 4, new Queen(5, 3, new Queen(2, 2, new Queen(6, 1,
                        new Queen(1, 0, PhantomQueen.INSTANCE)))))))));
            add(new Queen(6, 7,
                    new Queen(0, 6, new Queen(2, 5, new Queen(7, 4, new Queen(5, 3, new Queen(3, 2, new Queen(1, 1,
                            new Queen(4, 0, PhantomQueen.INSTANCE)))))))));
        }});

        assertEquals(2, solutions.size());

        Solution firstSolution = solutions.get(0);
        Solution secondSolution = solutions.get(1);

        List<Integer> firstT270 = firstSolution.getMetadata(Solution.MetaKey.T270);
        List<Integer> secondT270 = secondSolution.getMetadata(Solution.MetaKey.T270);
        assertEquals(0, secondT270.size());
        assertEquals(1, firstT270.size());
        assertTrue(firstT270.get(0).equals(1));
    }

    @Test
    public void
    distinctShouldMarkReflect0() {
        List<Solution> solutions = SolutionManager.distinct(new LinkedList<Queen>(){{add(new Queen(3, 7,
                new Queen(0, 6, new Queen(4, 5, new Queen(7, 4, new Queen(5, 3, new Queen(2, 2, new Queen(6, 1,
                        new Queen(1, 0, PhantomQueen.INSTANCE)))))))));
            add(new Queen(1, 7,
                    new Queen(6, 6, new Queen(2, 5, new Queen(5, 4, new Queen(7, 3, new Queen(4, 2, new Queen(0, 1,
                            new Queen(3, 0, PhantomQueen.INSTANCE)))))))));
        }});

        assertEquals(2, solutions.size());

        Solution firstSolution = solutions.get(0);
        Solution secondSolution = solutions.get(1);

        List<Integer> firstR0 = firstSolution.getMetadata(Solution.MetaKey.R0);
        List<Integer> secondR0 = secondSolution.getMetadata(Solution.MetaKey.R0);
        assertEquals(1, secondR0.size());
        assertEquals(1, firstR0.size());
        assertTrue(firstR0.get(0).equals(1));
        assertTrue(secondR0.get(0).equals(0));
    }


    @Test
    public void
    distinctShouldMarkReflect90() {
        List<Solution> solutions = SolutionManager.distinct(new LinkedList<Queen>(){{add(new Queen(3, 7,
                new Queen(0, 6, new Queen(4, 5, new Queen(7, 4, new Queen(5, 3, new Queen(2, 2, new Queen(6, 1,
                        new Queen(1, 0, PhantomQueen.INSTANCE)))))))));
            add(new Queen(4, 7,
                    new Queen(1, 6, new Queen(3, 5, new Queen(5, 4, new Queen(7, 3, new Queen(2, 2, new Queen(0, 1,
                            new Queen(6, 0, PhantomQueen.INSTANCE)))))))));        }});

        assertEquals(2, solutions.size());

        Solution firstSolution = solutions.get(0);
        Solution secondSolution = solutions.get(1);

        List<Integer> firstR90 = firstSolution.getMetadata(Solution.MetaKey.R90);
        List<Integer> secondR90 = secondSolution.getMetadata(Solution.MetaKey.R90);
        assertEquals(1, secondR90.size());
        assertEquals(1, firstR90.size());
        assertTrue(firstR90.get(0).equals(1));
        assertTrue(secondR90.get(0).equals(0));
    }

    @Test
    public void
    distinctShouldMarkReflect180() {
        List<Solution> solutions = SolutionManager.distinct(new LinkedList<Queen>(){{add(new Queen(3, 7,
                new Queen(0, 6, new Queen(4, 5, new Queen(7, 4, new Queen(5, 3, new Queen(2, 2, new Queen(6, 1,
                        new Queen(1, 0, PhantomQueen.INSTANCE)))))))));
            add(new Queen(4, 7,
                    new Queen(7, 6, new Queen(3, 5, new Queen(0, 4, new Queen(2, 3, new Queen(5, 2, new Queen(1, 1,
                            new Queen(6, 0, PhantomQueen.INSTANCE)))))))));        }});

        assertEquals(2, solutions.size());

        Solution firstSolution = solutions.get(0);
        Solution secondSolution = solutions.get(1);

        List<Integer> firstR180 = firstSolution.getMetadata(Solution.MetaKey.R180);
        List<Integer> secondR180 = secondSolution.getMetadata(Solution.MetaKey.R180);
        assertEquals(1, secondR180.size());
        assertEquals(1, firstR180.size());
        assertTrue(firstR180.get(0).equals(1));
        assertTrue(secondR180.get(0).equals(0));
    }

    @Test
    public void
    distinctShouldMarkReflect270() {
        List<Solution> solutions = SolutionManager.distinct(new LinkedList<Queen>(){{add(new Queen(3, 7,
                new Queen(0, 6, new Queen(4, 5, new Queen(7, 4, new Queen(5, 3, new Queen(2, 2, new Queen(6, 1,
                        new Queen(1, 0, PhantomQueen.INSTANCE)))))))));
            add(new Queen(1, 7,
                    new Queen(7, 6, new Queen(5, 5, new Queen(0, 4, new Queen(2, 3, new Queen(4, 2, new Queen(6, 1,
                            new Queen(3, 0, PhantomQueen.INSTANCE)))))))));        }});

        assertEquals(2, solutions.size());

        Solution firstSolution = solutions.get(0);
        Solution secondSolution = solutions.get(1);

        List<Integer> firstR270 = firstSolution.getMetadata(Solution.MetaKey.R270);
        List<Integer> secondR270 = secondSolution.getMetadata(Solution.MetaKey.R270);
        assertEquals(1, secondR270.size());
        assertEquals(1, firstR270.size());
        assertTrue(firstR270.get(0).equals(1));
        assertTrue(secondR270.get(0).equals(0));
    }
}
