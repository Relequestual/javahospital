package GridObjects.items;

import java.awt.Graphics;

import level.Game;
import GridObjects.GridObject;

public abstract class Item extends GridObject {
    private int rotation;

    public boolean canRotate() {
	return true;
    }

    public int getRotation() {
	return rotation;
    }

    public void setRotation(int rotation) {
	this.rotation = rotation;
    }

    // The reason the rotation is in the item class is to deal with graphics.
    // In fact, this could even be placed down as low as the GridObject class.
    public void rotateMe() {
	System.out.println("rotation called");
	if (rotation != 4) {
	    rotation++;
	} else {
	    rotation = 1;
	}
    }

    public void paint(Graphics graphic) {
	graphic.setColor(getColor());
	int gridSize = Game.getGame().getGridSize();
	graphic.fillRect(getX()*gridSize, getY()*gridSize, gridSize * this.getWidth(), gridSize * this.getHeight());
    }

    public abstract void findPointOfUse();
}
