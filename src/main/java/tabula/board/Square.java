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

/**
 * Represents a square on the chess board, identified by its
 * {@linkplain Coordinate AlgebraicCoordinate} <p>
 * A Square may contain zero or one {@linkplain tabula.pieces.Chessman Chessman} at a time;
 * adding a Chessman will remove the Chessman (if any) occupying this square.
 *
 * @author Roger Grantham
 * @since Jan 17, 2009 at 12:20:13 AM
 */

public class Square extends JPanel {

    final Coordinate coordinate;
    final Coloring coloring;


    /**
     * Constructs a square representing the given coordinate
     *
     * @param coordinate The algebraic Coordinate which denotes this square
     * @param light      color of this square when <var>coloring</var> of the coordinate is LIGHT
     * @param dark       color of this square when <var>coloring</var> of the coordinate is DARK
     */

    public Square(Coordinate coordinate, Color light, Color dark) {
        super();
        if (coordinate == null) throw new NullPointerException("Null: coordinate");
        if (light == null) throw new NullPointerException("Null: light");
        if (dark == null) throw new NullPointerException("Null: dark");
        this.coordinate = coordinate;
        coloring = coordinate.getColoring();
        setName(coordinate.toString());
        if (Coloring.LIGHT.equals(coloring)) {
            setBackground(light);
        } else {
            setBackground(dark);
        }
        setLayout(new GridLayout(1, 1));
        setDoubleBuffered(true);
    }

    /**
     * Only accepts instances of {@linkplain tabula.pieces.Chessman Chessman}. Adding a piece will
     * remove any other piece currently occupying this square.
     *
     * @param piece Chessman to occupy this Square
     * @return the Component (Chessman) added to occupy this square.
     */

    @Override
    public Component add(Component piece) {
        if (piece == null) throw new NullPointerException("Null: piece");
        if (piece instanceof Chessman) {
            setPiece((Chessman) piece);
        } else {
            throw new IllegalArgumentException("Can only add an instance of Chessman, found: " + piece.getClass().getName());
        }
        return piece;
    }

    @Override
    public Component add(String name, Component comp) {
        return add(comp);
    }

    @Override
    public Component add(Component comp, int index) {
        return add(comp);
    }

    @Override
    public void add(Component comp, Object constraints) {
        add(comp);
    }

    @Override
    public void add(Component comp, Object constraints, int index) {
        add(comp);
    }

    /**
     * Furnishes the coordinate which describes this Square
     *
     * @return AlgebraicCoordinate
     */

    public Coordinate getCoordinate() {
        return coordinate;
    }


    /**
     * Retrieves the Chessman, if any, which occupies this square.
     *
     * @return Chessman which occupied this square or null if the square was unoccupied.
     */

    public Chessman getPiece() {
        return (getComponentCount() == 0) ? null : (Chessman) getComponent(0);
    }



    /**
     * Removes the Chessman, if any, which occupies this square.
     *
     * @return Chessman which occupied this square or null if the square was unoccupied.
     */

    public Chessman removePiece() {
        final Chessman piece = getPiece();
        removeAll();
        return piece;
    }

    /**
     * Places the piece on this square, removing any other piece that may currently occupy this square.
     *
     * @param piece Chessman
     * @return Chessman the Chessman which occupied this square (and was thus removed) before the
     *         <var>piece</var> was set on this Square, or null if no Chessman previously occupied this Square.
     */

    public Chessman setPiece(Chessman piece) {
        if (piece == null) throw new NullPointerException("Null: piece");
        final Chessman previous = removePiece();
        super.add(piece);
        return previous;
    }
}
