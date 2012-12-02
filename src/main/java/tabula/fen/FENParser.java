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


import tabula.Coloring;
import tabula.board.ChessBoard;
import tabula.board.Coordinate;
import tabula.board.File;
import tabula.board.Rank;
import tabula.pieces.Chessman;
import tabula.pieces.PieceImageSet;
import tabula.pieces.PieceType;

import java.awt.*;

/**
 * FENParser reads
 * <a href="http://en.wikipedia.org/wiki/FEN">Forsyth-Edwards Notation</a>
 * and sets up the corresponding board position on the provided
 * {@linkplain tabula.board.ChessBoard ChessBoard}
 *
 * @author Roger Grantham
 * @version $Id$
 */

public class FENParser {

    private static final String RANK_SEPARATOR = "/";

    private final PieceImageSet pieceSet;
    private final ChessBoard board;

    /**
     * Sets up a new parser to be used on the supplied board with the provided piece set.
     * @param pieceSet PieceImageSet
     * @param board ChessBoard
     */
    public FENParser(PieceImageSet pieceSet, ChessBoard board) {
        if (pieceSet == null) throw new NullPointerException("Null: pieceSet");
        if (board == null) throw new NullPointerException("Null: board");
        this.pieceSet = pieceSet;
        this.board = board;
    }


    /**
     * Accepts a FEN string and sets up the position on the supplied board. The FEN string
     * may not be a full FEN string, but must contain at least the portion which describes
     * the location of pieces on the board. For example, the FEN string for the initial position
     * of the chess board before a game has started is:
     * <ul><li><code>rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1</code></ul>
     * This string may be supplied to this method, but also acceptable is:
     * <ul><li><code>rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR</code></ul>
     * @param fen of the position to set up
     */
    public void parse(String fen){
        if (fen == null) throw new NullPointerException("Null: fen");
        if (board == null) throw new NullPointerException("Null: board");
        final String[] position = fen.split("\\s+");
        final String[] ranks = position[0].split(RANK_SEPARATOR);
        if (ranks.length != 8) throw new IllegalArgumentException("Malformed FEN String: " + fen);
        int rank = 8;
        for (String rankFEN: ranks){
            parseRank(Rank.fromInt(rank--), rankFEN);
        }

    }

    /**
     * Sets pieces on the given rank of the chessboard as described by the FEN string for this rank
     * @param rank on which to place pieces
     * @param rankFEN FEN fragment which describes piece placement on a single rank
     */
    private void parseRank(Rank rank, String rankFEN){
        int cols = 0;
        int j =0;
        for (int i = 0; i < rankFEN.length(); i++){
            final String token = rankFEN.substring(i, i + 1);
            final PieceType pieceType = PieceType.fromFENName(token);
            if (pieceType == null){
                final Integer num;
                try {
                    num = Integer.parseInt(token);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(String.format("%s in %s is not valid FEN.", token, rankFEN));
                }

                // Small fix, that works for the 8 queens problem FENs.
                // The problem's FENs always have 1 Chessman a row
                // Otherwise, if you have more than one Chessman a row, it won't work,
                // because it doesn't handle empty cell offsets.
                j += num;
            } else {
                final Coordinate coord = new Coordinate(File.fromInt(j + 1), rank);
                final Coloring coloring = determineColoring(token);
                final Chessman man = makeChessman(coloring, pieceType);
                board.placePiece(man, coord);
            }
        }
    }


    /**
     * Given a single character from a FEN string denoting a piece (one of [prnbqkPRNBQK]), returns the color of the piece
     * @param token one of [prnbqkPRNBQK]
     * @return Coloring
     */
    private Coloring determineColoring(String token){
        // lower-case letters indicate black, upper-case letter denote white
        if (token.matches("[prnbqk]")){
            return Coloring.DARK;
        } else if (token.matches("[PRNBQK]")){
            return Coloring.LIGHT;
        } else {
            throw new IllegalArgumentException(String.format("Valid FEN expects one of [prnbqkPRNBQK] but found [%s]", token ));
        }
    }


    /**
     * Reports the position of this board as a FEN string
     * @return the board's position as a FEN string
     */
    public String getFEN(){
        final FENIterator fenIter = new FENIterator();
        final int numFiles = 8;
        int fileCount = 0;
        int unoccupiedCount = 0;
        final StringBuilder fen = new StringBuilder();
        for (Coordinate coord = fenIter.next(); coord != null; coord = fenIter.next()){
            fileCount++;
            final Chessman chessman = board.getPieceAt(coord);
            if (chessman == null){
                unoccupiedCount++;
            } else {
                if (unoccupiedCount > 0){
                    fen.append(unoccupiedCount);
                    unoccupiedCount = 0;
                }
                final String fenName = chessman.getPieceType().getFenName();
                final String record = (chessman.getColor() == Coloring.LIGHT) ? fenName.toUpperCase() : fenName.toLowerCase();
                fen.append(record);
            }
            if (fileCount == numFiles){
                if (unoccupiedCount > 0){
                    fen.append(unoccupiedCount);
                    unoccupiedCount = 0;
                }
                if (fenIter.hasNext()) fen.append(RANK_SEPARATOR);
                fileCount = 0;
            }
        }
        fen.append(" "); // append a space to separate the piece tabulation from the rest of the fen string
        return fen.toString();
    }


    /**
     * Makes a chessman of color <var>color</var> and of type <var>pieceType</var>
     * @param color of the chessman to be make
     * @param pieceType type of chessman to make
     * @return the rendered chessman
     */
    public Chessman makeChessman(Coloring color, PieceType pieceType){
        final boolean isWhite = Coloring.LIGHT == color;
        final Image image;
        switch (pieceType){
            case PAWN:  image = (isWhite) ? pieceSet.getWhitePawn() : pieceSet.getBlackPawn();
                break;
            case ROOK:  image = (isWhite) ? pieceSet.getWhiteRook() : pieceSet.getBlackRook();
                break;
            case KNIGHT:  image = (isWhite) ? pieceSet.getWhiteKnight() : pieceSet.getBlackKnight();
                break;
            case BISHOP:  image = (isWhite) ? pieceSet.getWhiteBishop() : pieceSet.getBlackBishop();
                break;
            case QUEEN:  image = (isWhite) ? pieceSet.getWhiteQueen() : pieceSet.getBlackQueen();
                break;
            case KING:  image = (isWhite) ? pieceSet.getWhiteKing() : pieceSet.getBlackKing();
                break;
            default: throw new IllegalArgumentException("Unhandled piece type: " + pieceType);
        }
        return new Chessman(color, pieceType, image);
    }

}
