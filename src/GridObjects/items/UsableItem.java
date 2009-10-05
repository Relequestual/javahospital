package GridObjects.items;

import java.awt.Point;

public abstract class UsableItem extends Item {
    Point pointOfUsage;

    public Point getPointOfUsage() {
	return pointOfUsage;
    }

    public void setPointOfUseage(Point point) {
	pointOfUsage = point;
    }

    // This method finds the fix point or points of usage for an item when it is
    // designed or rotated.
    public abstract void findPointOfUse();

}