package domain.queen;

import domain.AbstractMockTest;

public abstract class AbstractQueenTest extends AbstractMockTest {
    protected static final int AT_SOME_COLUMN = 3;
    protected static final int AT_SOME_ROW = 4;
    protected IQueen NULL_NEIGHBOUR = new PhantomQueen();
    IQueen mockQueen = context.mock(IQueen.class);
}
