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


import tabula.pieces.images.PropertiesFilePieceImageSet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PieceSet
 *
 * @author Roger Grantham
 * @version $Id$
 */
public enum PieceSet {

    /** The default piece set */
    DEFAULT("/tabula/pieces/images/default/PieceSet.properties"),

    /** A piece set made from a unicode arial chess font. */
    Arial("/tabula/pieces/images/arial/Arial.properties"),

    /** A piece set made from a unicode tahoma chess font. */
    Tahoma("/tabula/pieces/images/tahoma/Tahoma.properties");


    private final String propertiesFile;

    PieceSet(String propertiesFile){
        this.propertiesFile = propertiesFile;
    }


    /**
     * Renders a PieceImageSet from this member
     * @return PieceImageSet
     */
    public PieceImageSet toPieceImageSet() {
           final Properties pieces = new Properties();
           final InputStream is = getClass().getResourceAsStream(propertiesFile);
           try {
               pieces.load(is);
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
           return new PropertiesFilePieceImageSet(pieces);
       }


}
