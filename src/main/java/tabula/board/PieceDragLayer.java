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

import javax.swing.*;
import java.awt.*;

/**
 * <p>Acts as a transparent panel which is meant to overlay the {@linkplain ChessBoard Board}
 * on which the drag image (i.e. the {@link tabula.pieces.Chessman piece}) is rendered.</p>
 * <p>The expected use follows the sequence:</p>
 * <ol>
 *  <li>the caller uses {@link #startMove(java.awt.Image, java.awt.Point) } to initiate the move, then,
 *  <li>as the mouse moves (or a programmatic sequence is carried out), the caller invokes
 *      {@link #updatePieceLocation (java.awt.Point)}, until the move is completed, whereupon the call to </li>
 *  <li>{@link #endMove()} is made.</li>
 * </ol>
 * @author Roger Grantham
 * @author Scott Sirovy
 * @since $Id$
 */

public final class PieceDragLayer extends JPanel implements DragLayer {

    private static final Point      ORIGIN          = new Point(0, 0);

    private final   AlphaComposite  alphaComposite  = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f);
    private         Point           location        = ORIGIN;
    private         Image           pieceImage;
	private			int				width;
	private			int				height;


    /**
     * Constructs a transparent GlassPane
     */

    public PieceDragLayer(){
        super();
        setOpaque(false);
    }


    @Override
    public void startMove(Image pieceImage, Point location){
        if (pieceImage == null) throw new NullPointerException("Null: pieceImage");
        this.pieceImage = pieceImage;
		width = pieceImage.getWidth(null);
		height = pieceImage.getHeight(null);
        updatePieceLocation(location);
    }


    @Override
    public void endMove(){
        updatePieceLocation(ORIGIN);
		pieceImage = null;
		width = -1;
		height = -1;
    }

    @Override
    public void updatePieceLocation(Point location){
        if (location == null) {
			throw new NullPointerException("Null: location");
		}

		// first repaint (at the old location) will erase the piece
		repaint(this.location.x, this.location.y, width, height);

		// second repaint (at the new location) will draw the piece
		this.location = location;
		if (!location.equals(ORIGIN)) {
			repaint(this.location.x, this.location.y, width, height);
		}
	}


    @Override
    public void paintComponent(Graphics g){
        if (null == pieceImage) return;
        ((Graphics2D)g).setComposite(alphaComposite);
        g.drawImage(pieceImage, location.x,  location.y,  null);
    }
}
