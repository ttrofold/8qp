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
package tabula.pieces.images;


import tabula.pieces.PieceImageSet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Properties;


/**
 * <p>This class will provide chess men images when provided with a {@linkplain java.util.Properties properties}
 * instance which maps the name of a chess man (e.g. whiteKnight) to the location on the class path
 * of an image file. The supplied <code>Properties</code> must defined the following keys:</p>
 * <ul>
 * <li>blackBishop</li>
 * <li>blackKing</li>
 * <li>blackKnight</li>
 * <li>blackPawn</li>
 * <li>blackQueen</li>
 * <li>blackRook</li>
 * <li>whiteBishop</li>
 * <li>whiteKing</li>
 * <li>whiteKnight</li>
 * <li>whitePawn</li>
 * <li>whiteQueen</li>
 * <li>whiteRook</li>
 * </ul>
 * <p>See for example</p>
 * <ul>
 * <li>/org/magnopere/tabula/pieces/images/default/PieceSet.properties</li>
 * <li>/org/magnopere/tabula/pieces/images/knights/Knights.properties</li>
 * <li>/org/magnopere/tabula/pieces/images/xboard/XBoard.properties</li>
 * </ul>
 *
 * @author Roger Grantham
 * @since Jan 12, 2009 at 8:15:55 PM
 */

public class PropertiesFilePieceImageSet implements PieceImageSet {


    private final EnumMap<Pieces, Image> images = new EnumMap<Pieces, Image>(Pieces.class);


    /**
     * <p>Constructs a new instance using the supplied  {@linkplain java.util.Properties propterties}, which must
     * map the name of each chess man to a class-path resource of the corresponding image file. The required names
     * of the chess men in this file are:  </p>
     * <ul>
     * <li>blackBishop</li>
     * <li>blackKing</li>
     * <li>blackKnight</li>
     * <li>blackPawn</li>
     * <li>blackQueen</li>
     * <li>blackRook</li>
     * <li>whiteBishop</li>
     * <li>whiteKing</li>
     * <li>whiteKnight</li>
     * <li>whitePawn</li>
     * <li>whiteQueen</li>
     * <li>whiteRook</li>
     * </ul>
     * <p>If any of the above keys are missing, a <code>NullPointerException</code> will be thrown. If any of the
     * values to these keys (i.e. the class-path resource of the corresponding image file) cannot be loaded
     * from the classloader of this instance, then an IllegalArgumentException will be thrown</p>
     *
     * @param pieceNames which map each piece name to a class-path resource of an image file
     * @throws NullPointerException     if <var>pieceNames</code> does not define each piece name listed above.
     * @throws IllegalArgumentException if the image resource associated with a <var>pieceNames</var> key cannot be
     *                                  loaded from the class path.
     */

    public PropertiesFilePieceImageSet(Properties pieceNames) {
        if (pieceNames == null) throw new NullPointerException("null: pieceNames");
        for (Pieces piece : Pieces.values()) {
            final String path = pieceNames.getProperty(piece.getKeyName());
            if (path == null)
                throw new NullPointerException(String.format("No file path defined for [%s]", piece.getKeyName()));
            final InputStream imageStream = getClass().getResourceAsStream(path);
            if (imageStream == null)
                throw new IllegalArgumentException(String.format("null: imageStream; could not load resource %s", path));
            final Image image;
            try {
                image = ImageIO.read(imageStream);
            } catch (IOException e) {
                // catastrophic error, don't bother trying to recover from this.
                throw new RuntimeException(e);
            }
            if (image == null)
                throw new IllegalArgumentException(String.format("null: image; could not load resource %s", path));
            images.put(piece, image);
        }
    }


    public static PropertiesFilePieceImageSet fromClassPathResource(String classPathResource) throws IOException {
        final Properties pieces = new Properties();
        final InputStream is = PropertiesFilePieceImageSet.class.getResourceAsStream(classPathResource);
        pieces.load(is);
        return new PropertiesFilePieceImageSet(pieces);
    }

    @Override
    public Image getBlackBishop() {
        return images.get(Pieces.BLACK_BISHOP);
    }

    @Override
    public Image getBlackKing() {
        return images.get(Pieces.BLACK_KING);
    }

    @Override
    public Image getBlackKnight() {
        return images.get(Pieces.BLACK_KNIGHT);
    }

    @Override
    public Image getBlackPawn() {
        return images.get(Pieces.BLACK_PAWN);
    }

    @Override
    public Image getBlackQueen() {
        return images.get(Pieces.BLACK_QUEEN);
    }

    @Override
    public Image getBlackRook() {
        return images.get(Pieces.BLACK_ROOK);
    }

    @Override
    public Image getWhiteBishop() {
        return images.get(Pieces.WHITE_BISHOP);
    }

    @Override
    public Image getWhiteKing() {
        return images.get(Pieces.WHITE_KING);
    }

    @Override
    public Image getWhiteKnight() {
        return images.get(Pieces.WHITE_KNIGHT);
    }

    @Override
    public Image getWhitePawn() {
        return images.get(Pieces.WHITE_PAWN);
    }

    @Override
    public Image getWhiteQueen() {
        return images.get(Pieces.WHITE_QUEEN);
    }

    @Override
    public Image getWhiteRook() {
        return images.get(Pieces.WHITE_ROOK);
    }


    /**
     * Enumerates the expected keys in the properties file.
     */

    protected enum Pieces {

        BLACK_BISHOP("blackBishop"),
        BLACK_KING("blackKing"),
        BLACK_KNIGHT("blackKnight"),
        BLACK_PAWN("blackPawn"),
        BLACK_QUEEN("blackQueen"),
        BLACK_ROOK("blackRook"),
        WHITE_BISHOP("whiteBishop"),
        WHITE_KING("whiteKing"),
        WHITE_KNIGHT("whiteKnight"),
        WHITE_PAWN("whitePawn"),
        WHITE_QUEEN("whiteQueen"),
        WHITE_ROOK("whiteRook");

        private final String keyName;

        /**
         * Constructs a new member, whose <var>name</var> is expected to
         * appear as a key in a properties file.
         *
         * @param keyName String expected key in a properties file.
         */

        Pieces(String keyName) {
            this.keyName = keyName;
        }

        /**
         * The key expected in the proptery file, whose value is the relative path (on the class path)
         * of an image file.
         *
         * @return String property file key name
         */
        String getKeyName() {
            return keyName;
        }

    }

}
