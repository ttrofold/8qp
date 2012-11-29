/*
 * Copyright (c) 2010. Roger W. Grantham
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package tabula.board;

import tabula.Coloring;
import tabula.pieces.Chessman;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;


/**
 * Composed of 64 squares, this class represents a chess board.
 *
 * @author Roger Grantham
 * @since Jan 17, 2009 at 12:18:44 AM
 */

public class ChessBoard extends JPanel implements BoardTranslator {

    private final Color light;
    private final Color dark;
    private Coloring    orientation;

    private final Map<Coordinate, Square> squares = new HashMap<Coordinate, Square>(64);

    private final List<Coordinate> aFile = new ArrayList<Coordinate>();
    private final List<Coordinate> hFile = new ArrayList<Coordinate>();


    /**
     * Builds a board whose <var>light</var> squares are of the specified color, whose <var>dark</var>
     * squares are of the specified color, and which is oriented with square <code>a1</code> in the lower
     * left corner when a {@linkplain tabula.Coloring#LIGHT LIGHT} <var>orientation</var>
     * is specified and <code>h8</code> when {@linkplain tabula.Coloring#DARK DARK} is specified.
     *
     * @param light       color of the light squares
     * @param dark        color of the dark squares
     * @param orientation orient the board as if for the player of the white pieces or the black pieces.
     */

    public ChessBoard(Color light, Color dark, Coloring orientation) {
        super(new ChessBoardLayout());
        if (light == null) throw new NullPointerException("Null: light");
        if (dark == null) throw new NullPointerException("Null: dark");
        if (orientation == null) throw new NullPointerException("Null: orientation");
        this.light = light;
        this.dark = dark;
        this.orientation = orientation;

        setDoubleBuffered(true);

        final Coordinate a1 = new Coordinate(File.A, Rank.FIRST);
        squares.put(a1, new Square(a1, light, dark));
        aFile.add(a1);
        for (Coordinate next = a1.next(); next != null; next = next.next()) {
            squares.put(next, new Square(next, light, dark));
            if (File.A.equals(next.getFile())) {
                aFile.add(next);
            } else if (File.H.equals(next.getFile())) {
                hFile.add(next);
            }
        }

        // this is done in order to iterate from the 8th rank to the first, as this
        // corresponds to the order in which we need to add squares to the board
        Collections.reverse(aFile);
        // populate squares
        layoutForSide(orientation);
    }

    /**
     * Lays out the board oriented for the given side. For the light-colored player, the board is oriented with
     * square <code>a1</code> in the lower left corner when a {@linkplain tabula.Coloring#LIGHT LIGHT}
     * <var>orientation</var> is specified and <code>h8</code> when {@linkplain tabula.Coloring#DARK DARK}
     * is specified.
     *
     * @param side the side (player) for which the board is oriented
     * @return this Board
     */

    public ChessBoard layoutForSide(Coloring side) {
        if (side == null) throw new NullPointerException("Null: side");
        removeAll();
        if (Coloring.LIGHT.equals(side)) {
            layoutForWhite();
        } else {
            layoutForBlack();
        }
        return this;
    }


    /**
     * Adds squares to the board in the proper order for the board to be oriented with the
     * square a1 in the lower left corner and h8 in the upper right corner.
     * <p/>
     * The caller must take care to first remove all components from the board.
     */

    private void layoutForWhite() {
        for (Coordinate leading : aFile) {
            for (Coordinate coord = leading; coord != null && leading.getRank().equals(coord.getRank()); coord = coord.next()) {
                add(squares.get(coord));
            }
        }
    }


    /**
     * Adds squares to the board in the proper order for the board to be oriented with the
     * square h8 in the lower left corner and a1 in the upper right corner.
     * <p/>
     * The caller must take care to first remove all components from the board.
     */

    private void layoutForBlack() {
        for (Coordinate leading : hFile) {
            for (Coordinate coord = leading; coord != null && leading.getRank().equals(coord.getRank()); coord = coord.previous()) {
                add(squares.get(coord));
            }
        }
    }


    @Override
    public Rectangle getBoundsAt(Coordinate coord) {
        if (coord == null) throw new NullPointerException("Null: coord");
        return squares.get(coord).getBounds();
    }


    @Override
    public Square getSquareAt(Point p) {
        if (p == null) throw new NullPointerException("Null: p");
        final Component c = getComponentAt(p);
        return (c instanceof Square) ? (Square) c : null;
    }


    /**
     * Places the ChessMand on the square denoted by the coordinate.
     *
     * @param chessman   Chessman to place
     * @param coordinate where to place it
     */

    public void placePiece(Chessman chessman, Coordinate coordinate) {
        squares.get(coordinate).add(chessman);
        chessman.setVisible(true);
    }


    /**
     * Furnishes the chessman (if any) which occupies the square identified by <var>coordinate</var>
     * @param coordinate of the square on which a chessman is sought
     * @return the Chessman occupying the square or null if the square is not occupied
     */
    public Chessman getPieceAt(Coordinate coordinate){
        return squares.get(coordinate).getPiece();
    }

    /**
     * Removes the ChessMan, if any, which occupies the square identified
     * by <var>coordinate</var>
     * @param coordinate to vacate
     * @return the Chessman vacated from the square or null if the square was unoccupied
     */
    public Chessman removePiece(Coordinate coordinate){
        return squares.get(coordinate).removePiece();
    }
}