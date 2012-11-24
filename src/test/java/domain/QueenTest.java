package domain;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

import static junit.framework.Assert.*;

public class QueenTest {

    private static final int AT_SOME_COLUMN = 3;
    private Queen NULL_NEIGHBOUR = null;
    private static final int AT_SOME_ROW = 4;

    Mockery context = new JUnit4Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };
    Queen mockQueen = context.mock(Queen.class);

    @Test(expected=IllegalArgumentException.class)
    public void
    queenIsNotInitWithRowLessThan0() {
        new Queen(-2, AT_SOME_COLUMN, NULL_NEIGHBOUR);
    }

    @Test(expected=IllegalArgumentException.class)
    public void
    queenIsNotInitWithRowGreaterThan7() {
        new Queen(8, AT_SOME_COLUMN, NULL_NEIGHBOUR);
    }

    @Test
    public void
    queenIsInitWithLegalRow() {
        assertEquals(2,
                new Queen(2, AT_SOME_COLUMN, NULL_NEIGHBOUR)
                        .getRow());
    }

    @Test(expected=IllegalArgumentException.class)
    public void
    queenIsNotInitWithColumnLessThan0() {
        new Queen(AT_SOME_ROW, -3, NULL_NEIGHBOUR);
    }

    @Test(expected=IllegalArgumentException.class)
    public void
    queenIsNotInitWithColumnGreaterThan7() {
        new Queen(AT_SOME_ROW, 9, NULL_NEIGHBOUR);
    }

    @Test
    public void
    queenIsInitWithLegalColumn() {
        assertEquals(1,
                new Queen(AT_SOME_ROW, 1, NULL_NEIGHBOUR)
                        .getColumn());
    }

    @Test
    public void
    queenIsInitWithNotNullNeighbour() {
        assertNotNull(new Queen(AT_SOME_ROW, AT_SOME_COLUMN, mockQueen)
                .getNeighbour());
    }

    @Test
    public void
    canAttackIfInTheSameRow() {
        assertTrue(new Queen(AT_SOME_ROW, AT_SOME_COLUMN, NULL_NEIGHBOUR)
                .canAttack(AT_SOME_ROW, AT_SOME_COLUMN + 1));
    }

    @Test
    public void
    canAttackOnTheSameDiagonalABitLower() {
        assertTrue(new Queen(AT_SOME_ROW, AT_SOME_COLUMN, NULL_NEIGHBOUR)
                .canAttack(AT_SOME_ROW + 2, AT_SOME_COLUMN + 2));
    }

    @Test
    public void
    canAttackOnTheSameDiagonalABitHigher() {
        assertTrue(new Queen(AT_SOME_ROW, AT_SOME_COLUMN, NULL_NEIGHBOUR)
                .canAttack(AT_SOME_ROW - 2, AT_SOME_COLUMN - 2));
    }

    @Test
    public void
    canAttackIfNeighbourCanAttack() {
        context.checking(new Expectations() {
            {
                one(mockQueen).canAttack(AT_SOME_ROW - 1, AT_SOME_COLUMN + 3);
                will(returnValue(true));
            }
        });

        assertTrue(new Queen(AT_SOME_ROW, AT_SOME_COLUMN, mockQueen)
                .canAttack(AT_SOME_ROW - 1, AT_SOME_COLUMN + 3));
    }

    @Test
    public void
    cannotAttackIfCannotAttackItselfAndHasNoNeighbour() {
        assertFalse(new Queen(AT_SOME_ROW, AT_SOME_COLUMN, NULL_NEIGHBOUR)
                .canAttack(AT_SOME_ROW - 1, AT_SOME_COLUMN + 3));
    }

    @Test
    public void
    ifNoNeighborCanAttackThenSolutionIsFound() {
        context.checking(new Expectations() {
            {
                exactly(2).of(mockQueen).canAttack(AT_SOME_ROW, AT_SOME_COLUMN);
                will(returnValue(false));
            }
        });

        assertTrue(new Queen(AT_SOME_ROW, AT_SOME_COLUMN, mockQueen).solve());
    }

    @Test
    public void
    ifNeighbourCanAttackShouldAdvance() {
        context.checking(new Expectations() {
            {
                exactly(2).of(mockQueen).canAttack(AT_SOME_ROW, AT_SOME_COLUMN);
                will(onConsecutiveCalls(
                        returnValue(true),
                        returnValue(false)));

                // In this test, we are not interested on consecutive calls
            }
        });
        final boolean[] flag = new boolean[1];

        flag[0] = false;
        new Queen(AT_SOME_ROW, AT_SOME_COLUMN, mockQueen) {
            @Override
            public boolean advance() {
                flag[0] = true;
                return false;
            }
        }.solve();

        assertTrue(flag[0]);
    }

    @Test
    public void
    whileNeighbourCanAttackShouldAdvance() {
        context.checking(new Expectations() {
            {
                exactly(3).of(mockQueen).canAttack(AT_SOME_ROW, AT_SOME_COLUMN);
                will(onConsecutiveCalls(
                        returnValue(true),
                        returnValue(true),
                        returnValue(false)));
            }
        });

        final int[] flag = new int[1];

        flag[0] = 0;

        new Queen(AT_SOME_ROW, AT_SOME_COLUMN, mockQueen) {
            @Override
            public boolean advance() {
                flag[0]++;
                return true;
            }
        }.solve();

        assertEquals(2, flag[0]);
    }

    @Test
    public void
    ifNeighbourCanAttackButCannotAdvanceThenCNoSolution() {
        context.checking(new Expectations() {
            {
                exactly(3).of(mockQueen).canAttack(AT_SOME_ROW, AT_SOME_COLUMN);
                will(onConsecutiveCalls(
                        returnValue(true),
                        returnValue(true),
                        returnValue(true)));
            }
        });

        final int[] invocationCount = new int[1];

        invocationCount[0] = 0;

        assertFalse(new Queen(AT_SOME_ROW, AT_SOME_COLUMN, mockQueen) {
            @Override
            public boolean advance() {
                invocationCount[0]++;
                return invocationCount[0] < 3;
            }
        }.solve());
    }

    @Test
    public void
    canAdvanceIfStillNotInTheLowestRow() {
        assertTrue(new Queen(AT_SOME_ROW, AT_SOME_COLUMN, NULL_NEIGHBOUR).advance());
    }

    @Test
    public void
    cannotAdvanceFromTheLowestRowIfHasNoNeighbour() {
        assertFalse(new Queen(7, AT_SOME_COLUMN, NULL_NEIGHBOUR).advance());
    }
}
