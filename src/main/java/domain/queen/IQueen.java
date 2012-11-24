package domain.queen;

import domain.Solution;

public interface IQueen {
    boolean canAttack(int row, int column);

    boolean solve();

    boolean advance();

    Solution solution();
}
