package domain.queen;

import domain.Solution;

public class PhantomQueen implements IQueen {

    public static final PhantomQueen INSTANCE = instance();

    private static PhantomQueen instance;

    private static PhantomQueen instance() {
        if(instance == null) {
            return instance = new PhantomQueen();
        }
        return instance;
    }

    private PhantomQueen() {}

    @Override
    public boolean canAttack(int row, int column) {
        return false;
    }

    @Override
    public boolean solve() {
        return true;
    }

    @Override
    public boolean advance() {
        return false;
    }

    @Override
    public Solution solution() {
        return new Solution();
    }

    @Override
    public IQueen clone() {
        try {
            return (IQueen)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
