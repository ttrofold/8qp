package domain.queen;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;

public abstract class AbstractQueenTest {
    protected static final int AT_SOME_COLUMN = 3;
    protected static final int AT_SOME_ROW = 4;
    protected IQueen NULL_NEIGHBOUR = new PhantomQueen();Mockery context = new JUnit4Mockery();
    IQueen mockQueen = context.mock(IQueen.class);
}
