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

import java.awt.Graphics;

import level.Game;
import GridObjects.GridObject;

// TODO: Auto-generated Javadoc
/**
 * The Class Item.
 */
public abstract class Item extends GridObject {
    
    /** The rotation. */
    private int rotation;

    /**
     * Can rotate.
     * 
     * @return true, if successful
     */
    public boolean canRotate() {
	return true;
    }

    /**
     * Gets the rotation.
     * 
     * @return the rotation
     */
    public int getRotation() {
	return rotation;
    }

    /**
     * Sets the rotation.
     * 
     * @param rotation the new rotation
     */
    public void setRotation(int rotation) {
	this.rotation = rotation;
    }

    // The reason the rotation is in the item class is to deal with graphics.
    // In fact, this could even be placed down as low as the GridObject class.
    /**
     * Rotate me.
     */
    public void rotateMe() {
	System.out.println("rotation called");
	if (rotation != 4) {
	    rotation++;
	} else {
	    rotation = 1;
	}
    }

    /* (non-Javadoc)
     * @see GridObjects.GridObject#paint(java.awt.Graphics)
     */
    public void paint(Graphics graphic) {
	graphic.setColor(getColor());
	int gridSize = Game.getGame().getGridSize();
	graphic.fillRect(getX()*gridSize, getY()*gridSize, gridSize * this.getWidth(), gridSize * this.getHeight());
    }

    /**
     * Find point of use.
     */
    public abstract void findPointOfUse();
}
