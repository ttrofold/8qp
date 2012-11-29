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

import tabula.Coloring;

import javax.swing.*;
import java.awt.*;

/**
 * <p>Each instance of this class represents a chessman (i.e. a piece used on the chess board). </p>
 *
 * @author Roger Grantham
 * @since Jan 14, 2009 at 11:08:16 PM
 */

public class Chessman extends JComponent {

    private final Coloring color;
    private final PieceType pieceType;
    private final Image image;
    private Image scaledImage;

    /**
     * This property defined the proportion of the piece size to the square.
     */
    private float proportion = 0.8f;


    /**
     * Creates a new Chessman of the specified type (e.g. Knight, Pawn), Coloring (e.g. LIGHT, DARK) and uses the
     * provided image to render the piece. The default piece-to-sqaure <Var>propoortion</var> of <code>0.8</code>
     *
     * @param color     Coloring, indicates whether this is a {@linkplain tabula.Coloring#LIGHT}
     *                  or {@linkplain tabula.Coloring#DARK} piece.
     * @param pieceType the type of piece or Chessman this instance represents
     * @param image     the Image used to display the piece.
     */

    public Chessman(Coloring color, PieceType pieceType, Image image) {
        if (color == null) throw new NullPointerException("null: color");
        if (pieceType == null) throw new NullPointerException("null: pieceType");
        if (image == null) throw new NullPointerException("null: image");
        this.color = color;
        this.pieceType = pieceType;
        this.image = image;
        this.scaledImage = image; // default, unscaled image; paint cycle will scale this
        setOpaque(false);
        setDoubleBuffered(true);
    }


    /**
     * Creates a new Chessman of the specified type (e.g. Knight, Pawn), Coloring (e.g. LIGHT, DARK) and uses the
     * provided image to render the piece. The default piece-to-sqaure <var>propoortion</var> of <code>0.8</code>
     *
     * @param color      Coloring, indicates whether this is a {@linkplain tabula.Coloring#LIGHT}
     *                   or {@linkplain tabula.Coloring#DARK} piece.
     * @param pieceType  the type of piece or Chessman this instance represents
     * @param image      the Image used to display the piece.
     * @param proportion specifies the piece to square size ratio, which must be between <code>0.0f</code>
     *                   and <code>1.0</code> inclusive.
     */

    public Chessman(Coloring color, PieceType pieceType, Image image, float proportion) {
        this(color, pieceType, image);
        setProportion(proportion);
    }

    /**
     * Indicates this pieces color
     * @return Color
     */
    public Coloring getColor() {
        return color;
    }

    /**
     * Indicates this pieces type (e.g. Rook or Bishop)
     * @return PieceType
     */
    public PieceType getPieceType() {
        return pieceType;
    }

    /**
     * Furnishes the scaled image used to represent this piece on the chess board. The scaled image
     * is updated on each paint cycle since the size is a proportional to the size of the square this
     * piece occupies, which may be resized at any time.
     *
     * @return Image scaled image used to represent this piece on the chess board.
     */
    public Image getScaledImage() {
        return scaledImage;
    }


    /**
     * Sets the piece-to-sqaure <var>propoortion</var>
     *
     * @param proportion specifies the piece to square size ratio, which must be between <code>0.0f</code>
     *                   and <code>1.0</code> inclusive.
     */
    public void setProportion(float proportion) {
        if (proportion > 1.0f || proportion < 0.0) {
            throw new IllegalArgumentException(String.format("expected [0.0f <= proportion <= 1.0f]; found %f", proportion));
        }
        this.proportion = proportion;
    }


    /**
     * Draws a peice image on this component which is scaled to the containing square size and modified by the
     * {@linkplain #proportion proportion}.
     *
     * @param g Graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Dimension size = getSize();
        final int side = size.width;
        final int scaledSide = Math.round(size.width * proportion);
        final int offset = Math.round((side - scaledSide) / 2);
        scaledImage = image.getScaledInstance(scaledSide, scaledSide, Image.SCALE_SMOOTH);
        g.drawImage(scaledImage, offset, offset, null);
    }

}
