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
 * Represents a file (i.e. a vertical column of squares) on the chess board (a through h); also, given rank will
 * compute the color of the square.
 *
 * @author Roger Grantham
 * @see Rank
 * @since Jan 2, 2009 at 12:53:40 AM
 */

public enum File implements BoardIterator<File> {
    // DO NOT CHANGE THE ORDER OF THESE DECLARATIONS

    A("a", 1) {
        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public File next() {
            return B;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public File previous() {
            return null;
        }
    },

    B("b", 0) {
        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public File next() {
            return C;
        }

        @Override
        public boolean hasPrevious() {
            return true;
        }

        @Override
        public File previous() {
            return A;
        }
    },

    C("c", 1) {
        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public File next() {
            return D;
        }

        @Override
        public boolean hasPrevious() {
            return true;
        }

        @Override
        public File previous() {
            return B;
        }
    },

    D("d", 0) {
        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public File next() {
            return E;
        }

        @Override
        public boolean hasPrevious() {
            return true;
        }

        @Override
        public File previous() {
            return C;
        }
    },

    E("e", 1) {
        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public File next() {
            return F;
        }

        @Override
        public boolean hasPrevious() {
            return true;
        }

        @Override
        public File previous() {
            return D;
        }
    },

    F("f", 0) {
        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public File next() {
            return G;
        }

        @Override
        public boolean hasPrevious() {
            return true;
        }

        @Override
        public File previous() {
            return E;
        }
    },

    G("g", 1) {
        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public File next() {
            return H;
        }

        @Override
        public boolean hasPrevious() {
            return true;
        }

        @Override
        public File previous() {
            return F;
        }
    },

    H("h", 0) {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public File next() {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return true;
        }

        @Override
        public File previous() {
            return G;
        }
    };

    final String fileName;
    final int offset;

    /**
     * Constructs a File with the given name
     *
     * @param fileName String
     * @param offset   0 or 1, used in computing wether the coordinate identified by this File and a given rank
     *                 will be {@linkplain tabula.Coloring#LIGHT LIGHT} or
     *                 {@linkplain tabula.Coloring @DARK DARK}
     */

    File(String fileName, int offset) {
        if (fileName == null) throw new IllegalArgumentException("null: fileName");
        if (offset != 0 && offset != 1) throw new IllegalArgumentException("offset must be 1 or 0, found " + offset);
        this.fileName = fileName;
        this.offset = offset;
    }

    /**
     * Computes wether the coordinate identified by this File and a given rank
     * will be {@linkplain tabula.Coloring#LIGHT LIGHT} or
     * {@linkplain tabula.Coloring#DARK DARK}
     *
     * @param rank Rank
     * @return Coloring
     */

    public Coloring computeColoring(Rank rank) {
        final int colorMask = 1 & (rank.asInt() - offset);
        return (colorMask == 0) ? Coloring.DARK : Coloring.LIGHT;
    }


    /**
     * Furnishes a File from the column number; 1 == A, 8 == H
     * @param col column, or File number
     * @return the corresponding File
     */
    public static File fromInt(int col){
        switch(col){
            case 1: return A;
            case 2: return B;
            case 3: return C;
            case 4: return D;
            case 5: return E;
            case 6: return F;
            case 7: return G;
            case 8: return H;
            default: throw new IllegalArgumentException("Invalid column number: " + col);
        }
    }

    @Override
    public abstract boolean hasNext();

    @Override
    public abstract boolean hasPrevious();

    @Override
    public abstract File next();

    @Override
    public abstract File previous();

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remover operation not supported");
    }

    /**
     * Returns a representation of this File as a String
     *
     * @return String file
     */

    @Override
    public String toString() {
        return fileName;
    }
}
