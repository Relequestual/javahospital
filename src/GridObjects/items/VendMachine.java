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
package GridObjects.items;

import java.awt.Color;
import java.awt.Point;

import level.Game;

public class VendMachine extends UsableItem {
    Color gridObjectColour = new Color(199, 255, 97);

    public Color getColor() {
	return gridObjectColour;
    }

    public VendMachine(Point topLeftEntered) {
	this.topLeftPoint = topLeftEntered;
	findPointOfUse();
	width = 1;
	height = 1;
    }

    public VendMachine() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see Items.AbstractUsableItem#findPointOfUse()
     */
    public void findPointOfUse() {
	Point pou;
	int gridSize = Game.getGame().getGridSize();
	switch (getRotation()) {
	case 1:
	    pou = new Point((int) topLeftPoint.getX(), (int) topLeftPoint.getY() + 1 * gridSize);
	    pointOfUsage = (pou);
	    break;
	case 2:
	    pou = new Point((int) topLeftPoint.getX() - 1 * gridSize, (int) topLeftPoint.getY());
	    pointOfUsage = (pou);
	    break;
	case 3:
	    pou = new Point((int) topLeftPoint.getX() * gridSize, (int) topLeftPoint.getY() - 1 * gridSize);
	    pointOfUsage = (pou);
	    break;
	case 4:
	    pou = new Point((int) topLeftPoint.getX() + 1 * gridSize, (int) topLeftPoint.getY() * gridSize);
	    pointOfUsage = (pou);
	    break;
	}
    }
}
