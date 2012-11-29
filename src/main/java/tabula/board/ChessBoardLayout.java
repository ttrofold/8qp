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

import java.awt.*;


/**
 * Manages the layout for a container which is expected to contain 64 coponents which are laid out
 * on an 8 x 8 grid in which the child component are held to maintain a constant 1:1 ratio
 * of the lenght of their sides (thus keeping them square, even when being resized).
 * <p/>
 * No padding is permitted.
 */

public final class ChessBoardLayout implements LayoutManager {

    private static final int SPAN = 8;
    private static final int SQUARES = 64;

    public ChessBoardLayout() {
        super();
    }

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
        return calculateSize(parent);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return calculateSize(parent);
    }


    /**
     * Accepts a dimension and returns the smaller of its height or width
     *
     * @param d Dimensions
     * @return int smaller of its height or width
     */

    private int getShortSide(Dimension d) {
        return Math.min(d.height, d.width);
    }


    /**
     * Computes the ideal size of the container by scaling the first component
     * size by 8 and correcting the dimensions to make them come out square.
     *
     * @param parent Container whose components are being laid out.
     * @return The scaled up square dimension which this Conainter should ideally possess
     */

    private Dimension calculateSize(Container parent) {
        if (parent == null) throw new NullPointerException("null: parent");
        synchronized (parent.getTreeLock()) {
            if (SQUARES != parent.getComponentCount())
                throw new IllegalStateException("Expected 64 components, found " + parent.getComponentCount());
            final Dimension child = parent.getComponent(0).getSize();
            final int side = getShortSide(child) * SPAN;
            return new Dimension(side, side);
        }
    }


    /**
     * Positions each component within and 8 x 8 grid, setting each component's height and width to the same
     * value in order to preserve their square aspect.
     *
     * @param parent container whose components are to be laid out
     */
    @Override
    public void layoutContainer(Container parent) {
        if (parent == null) throw new NullPointerException("null: parent");
        synchronized (parent.getTreeLock()) {
            if (SQUARES != parent.getComponentCount())
                throw new IllegalStateException("Expected 64 components, found " + parent.getComponentCount());

            final int parentSide = getShortSide(parent.getSize());
            final int side = Math.round(parentSide / SPAN);
            final Component[] components = parent.getComponents();

            int x = 0;
            int y = 0;
            for (int i = 0; i < SQUARES; i++) {
                if (i > 0 && i % 8 == 0) {
                    y += side;
                    x = 0;
                }
                components[i].setBounds(x, y, side, side);
                x += side;
            }
        }
    }
}