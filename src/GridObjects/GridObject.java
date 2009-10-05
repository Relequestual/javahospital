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

public abstract class GridObject {
    protected Point topLeftPoint;
    protected int width;
    protected int height;
    Color gridObjectColour = null;

    public String toString() {
	String string = new String("Grid Object - " + topLeftPoint + " " + width + " " + height);
	return string;
    }

    public abstract void paint(Graphics graphic);

    public Color getColor() {
	return gridObjectColour;
    }

    public int getWidth() {
	return width;
    }

    public void setWidth(int widthSetter) {
	width = widthSetter;
    }

    public int getHeight() {
	return height;
    }

    public void setHeight(int heightSetter) {
	height = heightSetter;
    }

    public void setDimentions(int widthSetter, int heightSetter) {
	width = widthSetter;
	height = heightSetter;
    }

    public Point getTopLeftPoint() {
	return topLeftPoint;
    }

    public void setTopLeftPoint(Point TLPointSetter) {
	topLeftPoint = TLPointSetter;
    }

    public int getX() {
	return (int) topLeftPoint.getX();
    }

    public int getY() {
	return (int) topLeftPoint.getY();
    }

}
