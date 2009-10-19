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

import java.awt.Point;

/**
 * The Class UsableItem.
 */
public abstract class UsableItem extends Item {
    
    /** The point of usage. */
    Point pointOfUsage;
    
    int nextStageTime;    

    /**
     * Gets the point of usage.
     * 
     * @return the point of usage
     */
    public Point getPointOfUsage() {
	return pointOfUsage;
    }

    /**
     * Sets the point of useage.
     * 
     * @param point the new point of useage
     */
    public void setPointOfUseage(Point point) {
	pointOfUsage = point;
    }

    // This method finds the fix point or points of usage for an item when it is
    // designed or rotated.
    /* (non-Javadoc)
     * @see GridObjects.items.Item#findPointOfUse()
     */
    public abstract void findPointOfUse();

}
