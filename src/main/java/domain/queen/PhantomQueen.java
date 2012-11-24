package domain.queen;

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
}
