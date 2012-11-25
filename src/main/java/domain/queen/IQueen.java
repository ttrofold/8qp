package domain.queen;

import domain.Solution;

public interface IQueen extends Cloneable {
    boolean canAttack(int row, int column);

    boolean solve();

    boolean advance();

    Solution solution();

    IQueen clone();
}
