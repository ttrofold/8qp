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

/**
 * @author Roger Grantham
 * @since Jan 14, 2009, 11:13:42 PM
 */

public enum PieceType {

    PAWN("P"),
    ROOK("R"),
    KNIGHT("N"),
    BISHOP("B"),
    KING("K"),
    QUEEN("Q");
    
    private final String fenName;

    /**
     * FEN differentiates color by lower-case (black) and upper-case
     * piece names (e.g. "p" is a black pawn and "P" is a white pawn. However,
     * since our enum is agnostic about color, this bit of information is irrelevant. 
     * @param fenName "P, R, N, B, Q, or K"
     */
    private PieceType(String fenName){
        this.fenName = fenName;        
    }

    
    public String getFenName() {
        return fenName;
    }


    public static PieceType fromFENName(String fenName){
        for (PieceType type: PieceType.values()){
            if (type.getFenName().equalsIgnoreCase(fenName)){
                return type;
            }
        }
        return null;
    }
    
}
