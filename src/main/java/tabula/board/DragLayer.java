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
 * <p>Defines the behaviors required for an object which can act as a drag layer over the chess board.
 * Essentially this involves painting a piece image at a given location and accepting updates of the piece
 * location. </p>
 * <p/>
 * <p>The expected use follows the lifecycle of a move:</p>
 * <ol>
 * <li>the caller uses {@link #startMove(java.awt.Image, java.awt.Point) } to initiate the move, then,
 * <li>as the mouse moves (or a programmatic sequence is carried out), the caller invokes
 * {@link #updatePieceLocation(java.awt.Point)}, until the move is completed, whereupon the call to </li>
 * <li>{@link #endMove()} is made.</li>
 * </ol>
 *
 * @author Roger Grantham
 * @since Mar 1, 2009, 6:45:28 PM
 */

public interface DragLayer {

    /**
     * Initiates drawing the give image of a piece on this transparent panel. This method
     * initiates the drawing of a "move" of a piece from one location to another. Callers should
     * subsequently call {@link #updatePieceLocation(java.awt.Point)} to "move" the image, and when
     * complete, must call {@link #endMove()}.
     *
     * @param image    the image to draw on this panel
     * @param location the point within this panel to draw the image.
     */

    void startMove(Image image, Point location);


    /**
     * Invoking this method signals that the move is completed and causes this panel to cease drawing
     * the piece image supplied in the previous call to {@link #startMove(java.awt.Image, java.awt.Point)}
     */

    void endMove();


    /**
     * Sets the location at which a piece image should be drawn when a drag operation is under-way.
     *
     * @param location Point, not null, current mouse location
     */

    void updatePieceLocation(Point location);
}
