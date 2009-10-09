/*******************************************************************************
 * Copyright (c) 2009 Ben Hutton
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
 ******************************************************************************/
package GridObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

// TODO: Auto-generated Javadoc
/**
 * The Class GridObject.
 */
public abstract class GridObject {
    
    /** The top left point. */
    protected Point topLeftPoint;
    
    /** The width. */
    protected int width;
    
    /** The height. */
    protected int height;
    
    /** The grid object colour. */
    Color gridObjectColour = null;

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
	String string = new String("Grid Object - " + topLeftPoint + " " + width + " " + height);
	return string;
    }

    /**
     * Paint.
     * 
     * @param graphic the graphic
     */
    public abstract void paint(Graphics graphic);

    /**
     * Gets the color.
     * 
     * @return the color
     */
    public Color getColor() {
	return gridObjectColour;
    }

    /**
     * Gets the width.
     * 
     * @return the width
     */
    public int getWidth() {
	return width;
    }

    /**
     * Sets the width.
     * 
     * @param widthSetter the new width
     */
    public void setWidth(int widthSetter) {
	width = widthSetter;
    }

    /**
     * Gets the height.
     * 
     * @return the height
     */
    public int getHeight() {
	return height;
    }

    /**
     * Sets the height.
     * 
     * @param heightSetter the new height
     */
    public void setHeight(int heightSetter) {
	height = heightSetter;
    }

    /**
     * Sets the dimentions.
     * 
     * @param widthSetter the width setter
     * @param heightSetter the height setter
     */
    public void setDimentions(int widthSetter, int heightSetter) {
	width = widthSetter;
	height = heightSetter;
    }

    /**
     * Gets the top left point.
     * 
     * @return the top left point
     */
    public Point getTopLeftPoint() {
	return topLeftPoint;
    }

    /**
     * Sets the top left point.
     * 
     * @param TLPointSetter the new top left point
     */
    public void setTopLeftPoint(Point TLPointSetter) {
	topLeftPoint = TLPointSetter;
    }

    /**
     * Gets the x of the top left point.
     * 
     * @return the x
     */
    public int getX() {
	return (int) topLeftPoint.getX();
    }

    /**
     * Gets the y of the top left point.
     * 
     * @return the y
     */
    public int getY() {
	return (int) topLeftPoint.getY();
    }

}
