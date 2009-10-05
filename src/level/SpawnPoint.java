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
package level;

import java.awt.Graphics;
import java.awt.Point;

import GridObjects.GridObject;

public class SpawnPoint {

    Point spawnTo;
    private int rotation;

    public SpawnPoint(Point spawnTo) {
	this.spawnTo = spawnTo;
    }

    public int getRotation() {
	return rotation;
    }

    public void setRotation(int rotation) {
	this.rotation = rotation;
    }

    public Point getSpawnFrom() {
	Point spawnFrom = null;
	System.out.println("spawnTo is " + spawnTo);
	System.out.println("rotation is " + rotation);
	switch (rotation) {
	case 1:
	    spawnFrom = new Point(spawnTo.x, spawnTo.y + 1);
	    break;
	case 2:
	    spawnFrom = new Point((spawnTo.x - 1), spawnTo.y);
	    break;
	case 3:
	    spawnFrom = new Point(spawnTo.x, spawnTo.y - 1);
	    break;
	case 4:
	    spawnFrom = new Point(spawnTo.x + 1, spawnTo.y);
	    break;
	}
	System.out.println("spawnFrom is " + spawnFrom);
	return spawnFrom;
    }

    public Point getSpawnTo() {
	return new Point(spawnTo.x, spawnTo.y);
    }

    public void rotateMe() {
	System.out.println("rotation called");
	if (rotation != 4) {
	    rotation++;
	} else {
	    rotation = 1;
	}
    }

}
