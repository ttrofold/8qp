package domain.queen;

import domain.Solution;
import domain.exceptions.NoSolutionException;

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

            // This moment is crucial, while it seems to be logical to return plain boolean,
            // instead of returning the result of invocation to solve(), which may seem redundant,
            // doing so will break queen collaboration to obtain a solution, as the the neighbour
            // queen doesn't initiate the search for a new solution - advancing a neighbour may put
            // it under the threat of attack.
            return solve();
        }
        if(neighbour.advance()) {
            row = 0;
            return true;
        }

        return false;
    }

    public Solution solution() {
        if(!solve()) {
            throw new NoSolutionException();
        }

        Solution solution = neighbour.solution();
        solution.augment(row);
        return solution;
    }
}
