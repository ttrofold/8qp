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


/**
 * An enumeration representing the eight ranks (i.e. the horizontal rows of squares) on a chess board
 *
 * @author Roger Grantham
 * @see tabula.board.File
 * @since Jan 2, 2009 at 12:38:37 AM
 */

public enum Rank implements BoardIterator<Rank> {
    // DO NOT CHANGE THE ORDER OF THESE DECLARATIONS

    FIRST(1) {
        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public Rank previous() {
            return null;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Rank next() {
            return SECOND;
        }
    },

    SECOND(2) {
        @Override
        public boolean hasPrevious() {
            return true;
        }

        @Override
        public Rank previous() {
            return FIRST;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Rank next() {
            return THIRD;
        }
    },

    THIRD(3) {
        @Override
        public boolean hasPrevious() {
            return true;
        }

        @Override
        public Rank previous() {
            return SECOND;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Rank next() {
            return FOURTH;
        }
    },

    FOURTH(4) {
        @Override
        public boolean hasPrevious() {
            return true;
        }

        @Override
        public Rank previous() {
            return THIRD;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Rank next() {
            return FIFTH;
        }
    },

    FIFTH(5) {
        @Override
        public boolean hasPrevious() {
            return true;
        }

        @Override
        public Rank previous() {
            return FOURTH;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Rank next() {
            return SIXTH;
        }
    },

    SIXTH(6) {
        @Override
        public boolean hasPrevious() {
            return true;
        }

        @Override
        public Rank previous() {
            return FIFTH;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Rank next() {
            return SEVENTH;
        }
    },

    SEVENTH(7) {
        @Override
        public boolean hasPrevious() {
            return true;
        }

        @Override
        public Rank previous() {
            return SIXTH;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Rank next() {
            return EIGHTH;
        }
    },

    EIGHTH(8) {
        @Override
        public boolean hasPrevious() {
            return true;
        }

        @Override
        public Rank previous() {
            return SEVENTH;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Rank next() {
            return null;
        }
    };

    int rank;

    /**
     * Rank instance
     *
     * @param rank integer value of the rank represented by this enumeration instance
     */

    Rank(int rank) {
        this.rank = rank;
    }

    /**
     * Accepts a number from 1 to 8 and returns the corresponding <code>Rank</code>
     *
     * @param rank int
     * @return Rank
     * @throws IllegalArgumentException if <var>rank</var> is not in the set [1..8]
     */

    public static Rank fromInt(int rank) {
        for (Rank r : Rank.values()) {
            if (r.asInt() == rank) return r;
        }
        throw new IllegalArgumentException(String.format("No such instance for %d", rank));
    }

    /**
     * Returns the value of this enumeration instance as an int
     *
     * @return int representation of this rank
     */

    public int asInt() {
        return rank;
    }

    @Override
    public abstract boolean hasNext();

    @Override
    public abstract boolean hasPrevious();

    @Override
    public abstract Rank next();

    @Override
    public abstract Rank previous();

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remover operation not supported");
    }

    /**
     * Furnishes a representation of this Rank as a String
     *
     * @return String rank
     */

    @Override
    public String toString() {
        return String.valueOf(asInt());
    }
}
