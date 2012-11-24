package domain.queen;

import domain.Solution;

public class PhantomQueen implements IQueen {
    @Override
    public boolean canAttack(int row, int column) {
        return false;
    }

    @Override
    public boolean solve() {
        return false;
    }

    @Override
    public boolean advance() {
        return false;
    }

    @Override
    public Solution solution() {
        return null;
    }
}
