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