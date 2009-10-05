package GridObjects.items;

import java.awt.Color;
import java.awt.Point;

import level.Game;

public class ReceptionDesk extends UsableItem {
    Color gridObjectColour = new Color(199, 255, 97);

    public Color getColor() {
	return gridObjectColour;
    }

    //This constructor is used during building preview
    //TODO: this needs to be changed to use the factory.
    public ReceptionDesk(Point topLeftEntered) {
	this.topLeftPoint = topLeftEntered;
	findPointOfUse();
    }

    public ReceptionDesk() {
	System.out.println("new rd called con");
	setRotation(1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see Items.AbstractUsableItem#findPointOfUse()
     */
    public void findPointOfUse() {
	Point pou = null;
	System.out.println("findPOU - topleft is " + topLeftPoint);
	
	//Here I need to code down the topLeftPoint / 30...
	
	
	switch (getRotation()) {
	case 1:
	    pou = new Point((int) topLeftPoint.getX(), (int) topLeftPoint.getY() + 1);
	    this.setPointOfUseage(pou);
	    setHeight(1);
	    setWidth(2);
	    break;
	case 2:
	    pou = new Point((int) topLeftPoint.getX() - 1, (int) topLeftPoint.getY());
	    this.setPointOfUseage(pou);
	    setHeight(2);
	    setWidth(1);
	    break;
	case 3:
	    pou = new Point((int) topLeftPoint.getX() + 1, (int) topLeftPoint.getY() - 1);
	    this.setPointOfUseage(pou);
	    setHeight(1);
	    setWidth(2);
	    break;
	case 4:
	    pou = new Point((int) topLeftPoint.getX() + 1, (int) topLeftPoint.getY() + 1);
	    this.setPointOfUseage(pou);
	    setHeight(2);
	    setWidth(1);
	    break;
	}
	System.out.println("new pou is" + pou);
    }
}