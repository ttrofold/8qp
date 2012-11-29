package connector;

import org.junit.Test;
import tabula.board.LayeredBoard;

import static org.junit.Assert.assertTrue;

public class ConnectorTest {

    @Test
    public void
    connectsFenStringWithTabulaPane() {
        assertTrue(Connector.connect("7Q/6Q1/5Q2/4Q3/3Q4/2Q5/1Q6/Q7") instanceof LayeredBoard);
    }
}
