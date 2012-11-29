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

/**
 * Represents the algrebraic notation coordinate of a square on the chess board.
 * Each instance is an immutable value object.
 *
 * @author Roger Grantham
 * @since Jan 2, 2009 at 2:06:27 PM
 */

public final class Coordinate implements BoardIterator<Coordinate>, Comparable<Coordinate> {

    private final File file;
    private final Rank rank;
    private final String algebraicCoordinate;
    private final Coloring coloring;

    private final boolean hasNext;
    private final boolean hasPrevious;

    private Coordinate next;
    private Coordinate previous;


    /**
     * Constructs an object which represents an algebraic notation coordinate of a square on the chess board
     *
     * @param file The File part of the coordinate (a - h)
     * @param rank The Rank part of the coordinate (1 - 8)
     */

    public Coordinate(File file, Rank rank) {
        if (file == null) throw new IllegalArgumentException("null: file");
        if (rank == null) throw new IllegalArgumentException("null: rank");
        this.file = file;
        this.rank = rank;
        algebraicCoordinate = file.toString() + rank.toString();
        coloring = file.computeColoring(rank);
        hasNext = !(File.H.equals(file) && Rank.EIGHTH.equals(rank));
        hasPrevious = !(File.A.equals(file) && Rank.FIRST.equals(rank));
    }


    @Override
    public int compareTo(Coordinate o) {
        if (o == null) throw new NullPointerException("Null: o");
        if (equals(o)) return 0;
        if (rank.compareTo(o.getRank()) > 0) {
            return 1;
        } else if (file.compareTo(o.getFile()) > -1 && rank.compareTo(o.getRank()) > -1) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        return file == that.file && rank == that.rank;
    }

    /**
     * Furnishes the {@linkplain tabula.Coloring Coloring} of this
     * coordinate (i.e. a1 is a dark square, h1 is a light square)
     *
     * @return Coloring
     */

    public Coloring getColoring() {
        return coloring;
    }

    /**
     * Furnishes the file (i.e. vertical column of squares) part of the coordinate
     *
     * @return File
     */

    public File getFile() {
        return file;
    }

    /**
     * Furnishes the rank part of the coordinate
     *
     * @return Rank
     */

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public boolean hasPrevious() {
        return hasPrevious;
    }

    @Override
    public int hashCode() {
        return 31 * file.hashCode() + rank.hashCode();
    }

    @Override
    public Coordinate next() {
        if (!hasNext) return null;
        if (next != null) return next;
        // use current rank if file can be incremented
        final Rank nextRank = (file.hasNext()) ? rank : rank.next();
        final File nextFile = (file.hasNext()) ? file.next() : File.A;
        next = new Coordinate(nextFile, nextRank);
        return next;
    }

    @Override
    public Coordinate previous() {
        if (!hasPrevious) return null;
        if (previous != null) return previous;
        // use current rank if file can be decremented
        final Rank previousRank = (file.hasPrevious()) ? rank : rank.previous();
        final File previousFile = (file.hasPrevious()) ? file.previous() : File.H;
        previous = new Coordinate(previousFile, previousRank);
        return previous;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove operation not supported");
    }

    /**
     * Furnishes an algebraic notation represention of this coordinate, e.g. <code>a1</code> or <code>h8</code>
     *
     * @return String an algebraic notation represention of this coordinate, e.g. <code>a1</code> or <code>h8</code>
     */

    @Override
    public String toString() {
        return algebraicCoordinate;
    }
}
