package domain;

public class Queen {
    private int row;
    private int column;
    private Queen neighbour;

    public Queen(int row, int column, Queen neighbour) {
        checkIncomingProperty(row, "row");
        this.row = row;
        checkIncomingProperty(column, "column");
        this.column = column;
        this.neighbour = neighbour;
        solve();

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

    public Queen getNeighbour() {
        return neighbour;
    }

    public boolean canAttack(int row, int column) {
        if(row == this.row) {
            return true;
        } else if (row - this.row == column - this.column) {
            return true;
        }
        return neighbour == null ? false : neighbour.canAttack(row, column);
    }

    public boolean solve() {
        if(neighbour == null) {
            return true;
        }
        while(neighbour.canAttack(row, column)) {
            advance();
        }

        return true;
    }

    public boolean advance() {
        return false;
    }
}