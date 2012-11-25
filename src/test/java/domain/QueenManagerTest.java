package domain;

import domain.queen.PhantomQueen;
import domain.queen.Queen;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.*;

public class QueenManagerTest extends AbstractMockTest {

    @Test
    public void
    canGenerateAllPossiblePermutationsOf0To7() {
        List<List<Integer>> result = QueenManager.generateAllPermutations();
        assertEquals(8 * 7 * 6 * 5 * 4 * 3 * 2 * 1, result.size());

        assertTrue(result.contains(new LinkedList<Integer>()
        {{add(1); add(0); add(2); add(3); add(4); add(5); add(6); add(7);}}));

        assertTrue(result.contains(new LinkedList<Integer>()
        {{add(6); add(0); add(2); add(3); add(4); add(1); add(5); add(7);}}));

        assertTrue(result.contains(new LinkedList<Integer>()
        {{add(1); add(0); add(2); add(3); add(7); add(5); add(6); add(4);}}));
    }

    @Test
    public void
    canGenerateAllPossibleQueenNonAttackingPlacements() {
        List<Queen> placements = QueenManager.generateAllPlacements();

        assertNotNull(placements);

        assertTrue(placements.contains(new Queen(3, 7,
                new Queen(1, 6, new Queen(7, 5, new Queen(5, 4, new Queen(0, 3, new Queen(2, 2, new Queen(4, 1,
                        new Queen(6, 0, PhantomQueen.INSTANCE))))))))));
    }
}
