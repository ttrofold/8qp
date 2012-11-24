package domain.queen;

public class Queen implements IQueen {
    private int row;
    private int column;
    private IQueen neighbour;

    public Queen(int row, int column, IQueen neighbour) {
        checkIncomingProperty(row, "row");
        this.row = row;
        checkIncomingProperty(column, "column");
        this.column = column;
        this.neighbour = neighbour;
    }

    private void checkIncomingProperty(int property, String name) {
        if(property < 0 || property > 7) {
            throw new IllegalArgumentException("the "+ name + " of queen should be 0 to 7");
        }
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public IQueen getNeighbour() {
        return neighbour;
    }

    @Override
    public boolean canAttack(int row, int column) {
        if(row == this.row) {
            return true;
        } else if (row - this.row == column - this.column) {
            return true;
        }
        return neighbour.canAttack(row, column);
    }

    @Override
    public boolean solve() {
        while(neighbour.canAttack(row, column)) {
            if(!advance()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean advance() {
        if(row < 7) {
            row++;
            return true;
        }
        if(neighbour.advance()) {
            row = 0;
            return true;
        }

        return false;
    }
}
