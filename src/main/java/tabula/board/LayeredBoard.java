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
 * This class is composed of a {@linkplain ChessBoard ChessBoard} instance and one or more transparent
 * panels layered over the chess board for the purposes of implementing drag and drop behavior
 * and potentially other types of drawing over the board.
 *
 * @author Roger Grantham
 * @since Feb 3, 2009 at 8:19:48 PM
 */

public class LayeredBoard extends JLayeredPane {


    private final ChessBoard        chessBoard;
    private final PieceDragLayer    dragLayer = new PieceDragLayer();
    private final MoveMediator      moveMediator;

    /**
     * Creates a new LayeredBoard
     *
     * @param chessBoard the component which represents the chess board
     */

    public LayeredBoard(ChessBoard chessBoard) {
        super();
        if (chessBoard == null) throw new NullPointerException("Null: chessBoard");
        this.chessBoard = chessBoard;
        setOpaque(false);
        setLayout(new LayeredLayout());
        add(chessBoard, DEFAULT_LAYER, 0);
        add(dragLayer, DRAG_LAYER, 0);
        moveMediator = new MoveMediator(dragLayer, chessBoard);
        addMouseListener(moveMediator);
        addMouseMotionListener(moveMediator);
    }


    /**
     * Sets the client's move listener on the move mediator
     * @param moveListener client's MoveListener
     */
    public void setMoveListener(MoveListener moveListener){
        if (moveListener == null) throw new NullPointerException("Null: moveListener");
        moveMediator.setMoveListener(moveListener);
    }


    @Override
    public Dimension getPreferredSize() {
        return chessBoard.getPreferredSize();
    }


    /**
     * Ensures that all child components (each of which represents a layer)
     * occupies the all the available space within the board.
     */

    private class LayeredLayout implements LayoutManager {
        @Override
        public void addLayoutComponent(String name, Component comp) {
            // no op
        }

        @Override
        public void removeLayoutComponent(Component comp) {
            // no op
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            return parent.getPreferredSize();
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            return parent.getPreferredSize();
        }

        @Override
        public void layoutContainer(Container parent) {
            final Dimension size = parent.getSize();
            for (Component component : parent.getComponents()) {
                component.setBounds(0, 0, size.width, size.height);
            }
        }
    }

}
