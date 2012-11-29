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
package tabula;


import tabula.board.ChessBoard;
import tabula.board.LayeredBoard;
import tabula.board.MoveListener;
import tabula.fen.FENParser;
import tabula.pieces.PieceImageSet;
import tabula.pieces.PieceSets;

import javax.swing.*;
import java.awt.*;

/**
 * Instance of this class are responsible for furnishing clients with a working chess board component as well as a
 * mechanism by which the board may be configured and move listeners registered. By default this will produce a
 * board whose lower left coordinate is a1 (e.g. oriented for white). Clients may add new piece sets, change
 * the board orientation, set up a position using a FEN string, etc.
 *
 * @author Roger Grantham
 * @since Feb 9, 2009 at 11:42:42 PM
 */

public final class ChessBoardContext {

    /**
     * The starting position of the peices in FEN
     * @see <a href="http://en.wikipedia.org/wiki/FEN">Forsyth-Edwards Notation</a>
     */
    private static final String DEFAULT_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";



    /** Color used for the dark squares */
    private Color         dark          = new Color(112,133,182);

    /** Color used for the light squares */
    private Color         light         = new Color(238,238,238);

    /** Orientation of the board: for the player of the light or the dark pieces. */
    private Coloring      orientation   = Coloring.LIGHT;

    /** PieceImageSet from which images of chessmen are retrieved. */
    private PieceImageSet pieceImageSet = PieceSets.DEFAULT;


    private FENParser fenParser;

    /**
     * The position as a FEN string to set up on the board.
     * @see <a href="http://en.wikipedia.org/wiki/FEN">Forsyth-Edwards Notation</a>
     */
    private String        positionFEN   = DEFAULT_FEN;

    /** The ChessBoard instance produced from this context's state. */
    private ChessBoard chessBoard;

    /** The LayeredBoard instance produced from this context's state. */
    private LayeredBoard layeredBoard;

    /** The MoveListener which may veto a move attempted on the ChessBoard. */
    private MoveListener moveListener;



    /**
     * Furnishes a JComponent which represents a chess board. The caller should
     * take care to call this method from the EDT.
     * @return a JComponent which represents a chess board
     */
    public JComponent getBoardView(){
        if (chessBoard == null || layeredBoard == null){
            if (SwingUtilities.isEventDispatchThread()){
                makeBoard();
            } else {
                try {
                    SwingUtilities.invokeAndWait(new Runnable(){
                        @Override
                        public void run() {
                            makeBoard();   
                        }
                    });
                } catch (Exception e){
                    throw new RuntimeException(e);
                }
            }
        }

        return layeredBoard;
    }


    /**
     * Creates the LayeredBoard and its ChessBoard; only call from EDT.
     */

    private void makeBoard(){
        chessBoard = new ChessBoard(light, dark, orientation);
        fenParser = new FENParser(pieceImageSet, chessBoard);
        layeredBoard = new LayeredBoard(chessBoard);
        fenParser.parse(positionFEN);
        if (moveListener != null) layeredBoard.setMoveListener(moveListener);
    }


    /**
     * Sets the colors to use for the dark and the light squares on the chess board
     * @param dark color for dark squares
     * @param light color for light squares
     * @return this
     */

    public ChessBoardContext setColors(Color dark, Color light) {
        if (dark == null) throw new NullPointerException("Null: dark");
        if (light == null) throw new NullPointerException("Null: light");
        this.dark = dark;
        this.light = light;
        return this;
    }


    /**
     * Sets the move listener which is responsible for vetoing moves on the chess board
     * @param moveListener MoveListener
     * @return this
     */
    public ChessBoardContext setMoveListener(MoveListener moveListener) {
        if (moveListener == null) throw new NullPointerException("Null: moveListener");
        this.moveListener = moveListener;
        if (layeredBoard != null) layeredBoard.setMoveListener(moveListener);
        return this;
    }


    public ChessBoardContext setOrientation(Coloring orientation) {
        if (orientation == null) throw new NullPointerException("Null: orientation");
        this.orientation = orientation;
        return this;
    }

    /**
     * Sets the piece images to use on the chess board
     * @param pieceImageSet set of piece images
     * @return this
     */

    public ChessBoardContext setPieceImageSet(PieceImageSet pieceImageSet) {
        if (pieceImageSet == null) throw new NullPointerException("Null: pieceImageSet");
        this.pieceImageSet = pieceImageSet;
        if (chessBoard != null) fenParser = new FENParser(pieceImageSet, chessBoard);
        return this;
    }


    /**
     * Describes the board's current position as the part of a FEN string which pertains the
     * the placement of pieces on the board; this DOES NOT convey any information about castling,
     * the fifty-move rule, which side is to move, etc.
     * @see <a href="http://en.wikipedia.org/wiki/FEN">Forsyth-Edwards Notation</a>
     * @return a String describing the board's current position as the part of a FEN string which pertains the
     * the placement of pieces on the board
     */

    public String getPositionFEN(){
        return fenParser.getFEN();
    }

    /**
     * Indicates the position of the pieces to set up on the board
     * @param positionFEN FEN string describing the position
     * @see <a href="http://en.wikipedia.org/wiki/FEN">Forsyth-Edwards Notation</a>
     * @return this
     */
    public ChessBoardContext setPositionFEN(String positionFEN) {
        if (positionFEN == null) throw new NullPointerException("Null: positionFEN");
        if (positionFEN.isEmpty()) throw new IllegalArgumentException("positionFEN is empty.");
        this.positionFEN = positionFEN;
        return this;
    }
}
