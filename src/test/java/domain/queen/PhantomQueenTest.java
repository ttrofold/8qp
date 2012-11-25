package domain.queen;

import domain.AbstractMockTest;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class PhantomQueenTest extends AbstractMockTest {

    @Test
    public void
    shouldProvideNotNullSolution() {
        assertNotNull(PhantomQueen.INSTANCE.solution());
    }
}
