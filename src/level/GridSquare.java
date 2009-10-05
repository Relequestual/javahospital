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

import java.awt.Rectangle;

@SuppressWarnings("serial")
public class GridSquare extends Rectangle {

    boolean full = true;
    //The building preview booleans should really be elsewhere... hum
    boolean buildPreview;
    boolean pointOfUsagePreview;
    //
    boolean visited;
    boolean outUp = true;
    boolean outDown = true;
    boolean outLeft = true;
    boolean outRight = true;
    SquareType squareType = SquareType.grass;
    
    public enum SquareType{
	grass, path, ground
    }

    public SquareType getSquareType() {
        return squareType;
    }

    public void setSquareType(SquareType squareType) {
        this.squareType = squareType;
    }

    public GridSquare(int x, int y, int width, int height) {
	super(x, y, width, height);
    }

    public void setVisited(boolean bool) {
	visited = bool;
    }

    public boolean getVisited() {
	return visited;
    }

    public boolean isOutUp() {
        return outUp;
    }

    public void setOutUp(boolean outUp) {
        this.outUp = outUp;
    }

    public boolean isOutDown() {
        return outDown;
    }

    public void setOutDown(boolean outDown) {
        this.outDown = outDown;
    }

    public boolean isOutLeft() {
        return outLeft;
    }

    public void setOutLeft(boolean outLeft) {
        this.outLeft = outLeft;
    }

    public boolean isOutRight() {
        return outRight;
    }

    public void setOutRight(boolean outRight) {
        this.outRight = outRight;
    }

    public void setFull(boolean bool) {
	full = bool;
    }

    public boolean getFull() {
	return full;
    }

    public void setBuildPreview(boolean bool) {
	buildPreview = bool;
    }

    public boolean getBuildPreview() {
	return buildPreview;
    }

    public void setPointOfUsagePreview(boolean bool) {
	pointOfUsagePreview = bool;
    }

    public boolean getPointOfUsagePreview() {
	return pointOfUsagePreview;
    }

    public boolean isBuildRoomEdge() {
	if(!isOutDown() || !isOutUp() || !isOutLeft() || !isOutRight())
	{
	    return true;
	} else {
	    return false;
	}
    }
}
