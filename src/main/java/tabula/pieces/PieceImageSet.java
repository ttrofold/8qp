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
package tabula.pieces;

import java.awt.*;


/**
 * <p>Represents a set of images which are applied to pieces in the user interface, and provides
 * a simple extension point for clients of this module who wish to provide additional piece sets.</p>
 *
 * @author Roger Grantham
 * @since Jan 12, 2009 at 10:19:54 AM
 */

public interface PieceImageSet {


    /**
     * Furnishes the {@linkplain java.awt.Image Image} which is to be used to represent a black Bishop.
     *
     * @return Image, never <code>null</code>
     */

    Image getBlackBishop();


    /**
     * Furnishes the {@linkplain java.awt.Image Image} which is to be used to represent a black King.
     *
     * @return Image, never <code>null</code>
     */

    Image getBlackKing();


    /**
     * Furnishes the {@linkplain java.awt.Image Image} which is to be used to represent a black Knight.
     *
     * @return Image, never <code>null</code>
     */

    Image getBlackKnight();


    /**
     * Furnishes the {@linkplain java.awt.Image Image} which is to be used to represent a black pawn.
     *
     * @return Image, never <code>null</code>
     */

    Image getBlackPawn();


    /**
     * Furnishes the {@linkplain java.awt.Image Image} which is to be used to represent a black Queen.
     *
     * @return Image, never <code>null</code>
     */

    Image getBlackQueen();


    /**
     * Furnishes the {@linkplain java.awt.Image Image} which is to be used to represent a black Rook.
     *
     * @return Image, never <code>null</code>
     */

    Image getBlackRook();


    /**
     * Furnishes the {@linkplain java.awt.Image Image} which is to be used to represent a white Bishop.
     *
     * @return Image, never <code>null</code>
     */

    Image getWhiteBishop();


    /**
     * Furnishes the {@linkplain java.awt.Image Image} which is to be used to represent a white King.
     *
     * @return Image, never <code>null</code>
     */

    Image getWhiteKing();


    /**
     * Furnishes the {@linkplain java.awt.Image Image} which is to be used to represent a white knight.
     *
     * @return Image, never <code>null</code>
     */

    Image getWhiteKnight();


    /**
     * Furnishes the {@linkplain java.awt.Image Image} which is to be used to represent a white pawn.
     *
     * @return Image, never <code>null</code>
     */

    Image getWhitePawn();


    /**
     * Furnishes the {@linkplain java.awt.Image Image} which is to be used to represent a white Queen.
     *
     * @return Image, never <code>null</code>
     */

    Image getWhiteQueen();


    /**
     * Furnishes the {@linkplain java.awt.Image Image} which is to be used to represent a white Rook.
     *
     * @return Image, never <code>null</code>
     */

    Image getWhiteRook();
}
