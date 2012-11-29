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

import tabula.pieces.Chessman;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This move mediator is a mouse listener that carries out the updates to the source and
 * destination squares as well as the DragLayer for effecting a drag and drop operation.
 * @author Roger Grantham
 * @author Scott Sirovy
 * @since $Id$
 */

class MoveMediator extends MouseAdapter {

    private final   DragLayer               dragLayer;
    private final   BoardTranslator         translator;

    private Chessman heldPiece   = null;
    private         Square                  source;

    /** Temporary pass-through move listener */
    private MoveListener            moveListener = new MoveListener(){
        @Override
        public boolean acceptMove(Chessman piece, Coordinate source, Coordinate destination) {
            return true;
        }
    };


    /**
     * Creates a new MoveListener which reports moves in coordinate space to the
     * <var>dragLayer</var>
     *
     * @param dragLayer   component responsible for drawing the piece image when a move is underway.
     * @param translator  PointToSquareTranslator for resolving points to a Square; it is assumed that
     * this mouse events and the Translator occupy the same coordinate space.
     */

    public MoveMediator(DragLayer dragLayer, BoardTranslator translator){
        if (dragLayer == null) throw new NullPointerException("Null: dragLayer");
        if (translator == null) throw new NullPointerException("Null: translator");
        this.dragLayer = dragLayer;
        this.translator = translator;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        final Square s = translator.getSquareAt(e.getPoint());
        if (s == null) return;
        heldPiece = s.removePiece();
        if (heldPiece == null) return;

		source = s;
		source.repaint();
        dragLayer.startMove(heldPiece.getScaledImage(), getOffsetPoint(e.getPoint()));
    }


    /**
     * Cleans up after releasing over the target
     */

    private void reset(){
        dragLayer.endMove();
        heldPiece	= null;
        source		= null;
    }


    /**
     * Computes an offset location so that the cursor will appear to be centered in the image being dragged.
     * @param location Point current mouse loation
     * @return the mouse location offset by half the image size to make the image appear centered under the cursor
     */

    private Point getOffsetPoint(Point location){
        if (heldPiece == null) return location;
        final Point p = new Point(location);
        p.x -= Math.round(heldPiece.getScaledImage().getWidth(null) / 2);
        p.y -= Math.round(heldPiece.getScaledImage().getHeight(null) / 2);
        return p;
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        if (heldPiece == null) return;
        final Square d = translator.getSquareAt(e.getPoint());
        if (d != null && moveListener.acceptMove(heldPiece, source.getCoordinate(), d.getCoordinate())) {
            d.setPiece(heldPiece);
			d.repaint();
        } else {
            source.setPiece(heldPiece);
			source.repaint();
        }
        reset();
    }


    @Override
    public void mouseDragged(MouseEvent e) {
		if (heldPiece == null) return;
        dragLayer.updatePieceLocation(getOffsetPoint(e.getPoint()));
    }


    /**
     * MoveListener passed in through the ChessBoardContext.
     * @param moveListener listener from the client to use
     */
    public void setMoveListener(MoveListener moveListener){
        if (moveListener == null) throw new NullPointerException("Null: moveListener");
        this.moveListener = moveListener;
    }

}
