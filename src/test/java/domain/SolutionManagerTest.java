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
}
