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

package tabula.fen;

import tabula.board.Coordinate;
import tabula.board.File;
import tabula.board.Rank;

import java.util.Iterator;

/**
 * FENIterator traverses the chess board's coordinates in the order in which they are referenced in FEN.
 *
 * @author Roger Grantham
 * @version $Id$
 */
public class FENIterator implements Iterator<Coordinate> {

    private Rank rank = Rank.EIGHTH;
    private File file = File.A;
    private Coordinate  next = null;

    @Override
    public boolean hasNext() {
        return rank.hasPrevious() || file.hasNext();
    }

    @Override
    public Coordinate next() {
        if (next == null){
            next = new Coordinate(file, rank);
        } else if (file.hasNext()){
            file = file.next();
            next = new Coordinate(file, rank);
        } else if (rank.hasPrevious()){
            file = File.A;
            rank = rank.previous();
            next = new Coordinate(file, rank);
        } else {
          next = null;
        }
        return next;
    }

    /**
     * @throws UnsupportedOperationException when invoked
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() not supported");
    }
}
