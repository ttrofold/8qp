package domain.queen;

import org.jmock.Expectations;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class QueenIntegrationTest extends AbstractQueenTest {

    @Test
    public void
    whenNeighbourAdvances/* in response to advance request from queen on the right,
     it recomputes its own solution*/() {
        final boolean[] flag = new boolean[1];

        flag[0] = false;
        IQueen q1 = new Queen(AT_SOME_ROW, AT_SOME_COLUMN, NULL_NEIGHBOUR) {
            @Override
            public boolean solve() {
                flag[0] = true;
                return false;
            }
        };

        new Queen(7, AT_SOME_COLUMN + 1, q1).advance();

        assertTrue(flag[0]);
    }

    @Test
    public void
    neighbourAlsoRecomputesSolution/*.. when she is asked to advance but, in turn, needs to advance herself
    and has to ask her neighbor to advance*/() {
        final boolean[] flag = new boolean[1];

        flag[0] = false;

        checking(new Expectations() {
            {
                one(mockQueen).advance();
                will(returnValue(true));
            }
        });

        IQueen q1 = new Queen(7, AT_SOME_COLUMN, mockQueen) {
            @Override
            public boolean solve() {
                flag[0] = true;
                return false;
            }
        };

        new Queen(7, AT_SOME_COLUMN + 1, q1).advance();

        assertTrue(flag[0]);
    }
}
