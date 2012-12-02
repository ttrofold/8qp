package domain.queen;

import general.AbstractMockTest;

public abstract class AbstractQueenTest extends AbstractMockTest {
    protected static final int AT_SOME_COLUMN = 3;
    protected static final int AT_SOME_ROW = 4;
    protected IQueen NULL_NEIGHBOUR = PhantomQueen.INSTANCE;
    IQueen mockQueen = context.mock(IQueen.class);
}
