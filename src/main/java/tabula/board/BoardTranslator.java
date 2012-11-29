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

import java.awt.*;

/**
 * Defines the behavior of translator which accepts a point and returns a Square residing at that point, and will
 * also accept a {@link Coordinate} and return the bounds of the denoted Square. The
 * simplest implementation may assume that the <var>Point</var> arguments are in the coordinate space of
 * the {@linkplain tabula.board.ChessBoard ChessBoard}, but implementors are free to translate
 * the coordinate space of one context to another.
 *
 * @author Roger Grantham
 * @since Mar 1, 2009, 7:41:51 PM
 */

public interface BoardTranslator {


    /**
     * Returns a possibly null Square at the given point
     *
     * @param p Point the point at which a Square is sought
     * @return Square, or null if no square is at that Point
     */

    Square getSquareAt(Point p);


    /**
     * Furnishes the bounds of the square at the given algebraic chess coordinate.
     *
     * @param coord Coordinate (e.g. a1)
     * @return the bounds of the Square at the given coordinate
     */

    Rectangle getBoundsAt(Coordinate coord);

}
