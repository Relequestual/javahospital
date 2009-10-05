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
