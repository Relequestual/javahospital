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
