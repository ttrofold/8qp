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

import java.util.Iterator;

/**
 * Extends the Iterator interface by providing {@linkplain #hasPrevious()} and {@linkplain #previous()}
 * methods without the additional baggage associated with {@linkplain java.util.ListIterator ListIterator}
 *
 * @author Roger Grantham
 * @since Jan 17, 2009, 2:46:15 PM
 */

public interface BoardIterator<E> extends Iterator<E> {


    /**
     * Returns <code>true</code> if the iteration has a previous element
     *
     * @return boolean
     */

    boolean hasPrevious();


    /**
     * Returns the previous element or <code>null</code> if there is not previous element.
     *
     * @return E or null
     */

    E previous();

}
