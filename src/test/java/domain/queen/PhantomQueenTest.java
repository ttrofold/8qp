package domain.queen;

import domain.AbstractMockTest;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PhantomQueenTest extends AbstractMockTest {

    @Test
    public void
    shouldProvideNotNullSolution() {
        assertNotNull(PhantomQueen.INSTANCE.solution());
    }


    @Test
    public void
    whenWithPhantomNeighbourShouldCheckPhantomsSolutionBeforeProceedingToItself() {
        assertTrue(new Queen(0, 0, PhantomQueen.INSTANCE).solve());
    }
}
