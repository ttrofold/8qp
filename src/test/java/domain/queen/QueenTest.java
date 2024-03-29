package domain.queen;

import domain.Solution;
import domain.exceptions.NoSolutionException;
import org.jmock.Expectations;
import org.jmock.api.Invocation;
import org.jmock.lib.action.CustomAction;
import org.junit.Test;

import static junit.framework.Assert.*;

public class QueenTest extends AbstractQueenTest {

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
    canAttackOnTheSameDiagonalABitLowerToTheRight() {
        assertTrue(new Queen(AT_SOME_ROW, AT_SOME_COLUMN, NULL_NEIGHBOUR)
                .canAttack(AT_SOME_ROW + 2, AT_SOME_COLUMN + 2));
    }

    @Test
    public void
    canAttackOnTheSameDiagonalABitHigherToTheRight() {
        assertTrue(new Queen(AT_SOME_ROW, AT_SOME_COLUMN, NULL_NEIGHBOUR)
            .canAttack(AT_SOME_ROW  - 2, AT_SOME_COLUMN + 2));
    }

    @Test
    public void
    canAttackOnTheSameDiagonalABitHigherToTheLeft() {
        assertTrue(new Queen(AT_SOME_ROW, AT_SOME_COLUMN, NULL_NEIGHBOUR)
                .canAttack(AT_SOME_ROW - 2, AT_SOME_COLUMN - 2));
    }

    @Test
    public void
    canAttackOnTheSameDiagonalABitLowerToTheLeft() {
        assertTrue(new Queen(AT_SOME_ROW, AT_SOME_COLUMN, NULL_NEIGHBOUR)
                .canAttack(AT_SOME_ROW + 2, AT_SOME_COLUMN - 2));
    }

    @Test
    public void
    canAttackIfNeighbourCanAttack() {
        checking(new Expectations() {
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
        expectNeighbourSolvedItsProblem();

        checking(new Expectations() {
            {
                one(mockQueen).canAttack(AT_SOME_ROW, AT_SOME_COLUMN);
                will(returnValue(false));
            }
        });

        assertTrue(new Queen(AT_SOME_ROW, AT_SOME_COLUMN, mockQueen).solve());
    }

    @Test
    public void
    ifNeighbourCanAttackShouldAdvance() {
        expectNeighbourSolvedItsProblem();

        checking(new Expectations() {
            {
                exactly(1).of(mockQueen).canAttack(AT_SOME_ROW, AT_SOME_COLUMN);
                will(onConsecutiveCalls(
                        returnValue(true)));

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
        expectNeighbourSolvedItsProblem();

        checking(new Expectations() {
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
        expectNeighbourSolvedItsProblem();

        checking(new Expectations() {
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

    @Test
    public void
    ifCannotAdvanceSelfButNeighbourCanAdvanceThenCanAdvance() {
        expectNeighbourSolvedItsProblem();

        checking(new Expectations() {
            {
                one(mockQueen).advance();
                will(returnValue(true));

                // Fix for the broken test in response to neighbourAlso... in QueenIntegrationTest
                // In this test, we are not interested in subsequent interaction and thus allowing
                allowing(mockQueen).canAttack(0, AT_SOME_COLUMN);
            }
        });
        assertTrue(new Queen(7, AT_SOME_COLUMN, mockQueen).advance());
    }

    @Test
    public void
    ifCannotAdvanceSelfButNeighbourCanAdvanceThenCanAdvanceToThe0thRow() {
        expectNeighbourSolvedItsProblem();

        context.checking(new Expectations() {
            {
                one(mockQueen).advance();
                will(returnValue(true));

                // Fix for the broken test in response to neighbourAlso... in QueenIntegrationTest
                // In this test, we are not interested in subsequent interaction and thus allowing
                allowing(mockQueen).canAttack(0, AT_SOME_COLUMN);
            }
        });

        Queen queen = new Queen(7, AT_SOME_COLUMN, mockQueen);
        queen.advance();

        assertEquals(0, queen.getRow());
    }

    @Test(expected = NoSolutionException.class)
    public void
    shouldThrowExceptionIfAskedForSolutionWhenThereIsNoSolution() {
        new Queen(7, AT_SOME_COLUMN, NULL_NEIGHBOUR) {
            @Override
            public boolean solve() {
                return false;
            }
        }.solution();
    }

    @Test
    public void
    shouldProvideSolutionWhenThereIsOne() {
        assertTrue(new Queen(7, AT_SOME_COLUMN, NULL_NEIGHBOUR) {
            @Override
            public boolean solve() {
                return true;
            }
        }.solution() != null);
    }

    @Test
    public void
    shouldAskForNeighboursSolutionBeforeReturningOfItself() {
        context.checking(new Expectations() {
            {
               one(mockQueen).solution();

                // Added to fix the test after having added the implementation for the following test
                will(returnValue(new Solution()));
            }
        });

        new Queen(AT_SOME_ROW, AT_SOME_COLUMN, mockQueen) {
            @Override
            public boolean solve() {
                return true;
            }
        }.solution();
    }

    @Test
    public void
    shouldAugmentNeighboursSolutionWithItsRow() {

        checking(new Expectations() {
            {
                one(mockQueen).solution();
                will(returnValue(new Solution()));
            }
        });

        Solution solution = new Queen(AT_SOME_ROW, AT_SOME_COLUMN, mockQueen){
            @Override
            public boolean solve() {
                return true;
            }
        }.solution();

        assertEquals(1, solution.getSize());
        assertEquals(Integer.valueOf(AT_SOME_ROW), solution.get(0));
    }

    @Test
    public void
    shouldFirstCheckItsNeighbourSolvedHerProblem() {
        checking(new Expectations() {
            {
                one(mockQueen).solve();

                // This test is not interested in canAttack invocations, thus allowing
                allowing(mockQueen).canAttack(AT_SOME_ROW, AT_SOME_COLUMN);
            }
        });

        new Queen(AT_SOME_ROW, AT_SOME_COLUMN, mockQueen).solve();
    }

    @Test
    public void
    shouldCheckIfHasSolutionBeforeProceedingWithSolution() {
        final boolean[] flag = new boolean[1];

        checking(new Expectations() {
            {
                one(mockQueen).solution();
                will(new CustomAction("") {
                    @Override
                    public Object invoke(Invocation invocation) throws Throwable {
                        // Here we set the flag to true, to assert the order of invocation
                        flag[0] = true;
                        return new Solution();
                    }
                });
            }
        });
        new Queen(AT_SOME_ROW, AT_SOME_COLUMN, mockQueen) {
            @Override
            public boolean solve() {
                assertFalse(flag[0]);
                return true;
            }
        }.solution();

        assertTrue(flag[0]);
    }

    private void expectNeighbourSolvedItsProblem() {
        checking(new Expectations() {
            {
                // Fix
                one(mockQueen).solve();
                will(returnValue(true));
            }
        });
    }
}
