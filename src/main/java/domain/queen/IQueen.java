package domain.queen;

public interface IQueen {
    boolean canAttack(int row, int column);

    boolean solve();

    boolean advance();
}
