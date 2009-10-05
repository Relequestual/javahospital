package mouseActions;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import level.Board;
import level.Game;
import level.GridSquare;
import GridObjects.Buildings.Room;
import GridObjects.Buildings.RoomFactory;
import GridObjects.Buildings.RoomFactory.RoomType;
import PanelsAndFrames.hospitalGamePanel;

@SuppressWarnings("serial")
public class BuildRoomMouse implements MouseListener, MouseMotionListener {

    static int gridSize = Game.getGame().getGridSize();
    static int gridHeight = Game.getGame().getGridHeight();
    static int gridWidth = Game.getGame().getGridWidth();

    static Rectangle currentRect = null;
    static Rectangle rectToDraw = null;
    static Rectangle previousRectDrawn = new Rectangle();

    static Room thisBuild;

    static GridSquare currentSquare = null;

    static int height = Game.getGame().getGridHeight() * 30;
    static int width = Game.getGame().getGridWidth() * 30;

    public BuildRoomMouse(RoomType roomType) {
	thisBuild = RoomFactory.createRoom(roomType);
	Game.getGame().addBuildingPreview(thisBuild);
    }

    public static Rectangle getCurrentRect() {
	return currentRect;
    }

    public static void setCurrentRect(Rectangle currentRect) {
	BuildRoomMouse.currentRect = currentRect;
    }

    public static Rectangle getRectToDraw() {
	return rectToDraw;
    }

    public static void setRectToDraw(Rectangle rectToDraw) {
	BuildRoomMouse.rectToDraw = rectToDraw;
    }

    public static Rectangle getPreviousRectDrawn() {
	return previousRectDrawn;
    }

    public static void setPreviousRectDrawn(Rectangle previousRectDrawn) {
	BuildRoomMouse.previousRectDrawn = previousRectDrawn;
    }

    public static void middleClick(Point mouseAt) {
	/*
	 * This bit of code was used for testing to make sure that each square
	 * could be clicked and the colour would change. This shows that each
	 * square can be referenced using the method shown below. Now assigned
	 * to middle click.
	 */
	for (int i = 0; i < gridWidth; i += 1) {
	    for (int j = 0; j < gridHeight; j += 1) {

		if (Board.getBoard().getSquare(i, j).contains(mouseAt)) {
		    System.out.println("middle click " + i + " " + j);
		    System.out.println("middle click " + Board.getBoard().getSquare(i, j));
		    Board.getBoard().getSquare(i, j).setFull(!Board.getBoard().getSquare(i, j).getFull());
		}
	    }
	}
    }

    public static void rightClick() {
    }

    public static void leftClick(Point mouseAt) {
    }

    public static void updateDrawableRect(int compWidth, int compHeight) {
	int x = currentRect.x;
	int y = currentRect.y;
	int width = currentRect.width;
	int height = currentRect.height;

	// Make the width and height positive, if necessary.
	if (width < 0) {
	    width = 0 - width;
	    x = x - width + 1;
	    if (x < 0) {
		width += x;
		x = 0;
	    }
	}

	if (height < 0) {
	    height = 0 - height;
	    y = y - height + 1;
	    if (y < 0) {
		height += y;
		y = 0;
	    }
	}

	// The rectangle shouldn't extend past the drawing area.
	if ((x + width) > compWidth) {
	    width = compWidth - x;
	}
	if ((y + height) > compHeight) {
	    height = compHeight - y;
	}

	// Update rectToDraw after saving old value.
	previousRectDrawn.setBounds(rectToDraw.x, rectToDraw.y, rectToDraw.width, rectToDraw.height);
	rectToDraw.setBounds(x, y, width, height);

	// Rectangle checker code. if drawn rect contains any grid rects,
	// preview is turned on.

	for (int i = 0; i < gridWidth; i += 1) {
	    for (int j = 0; j < gridHeight; j += 1) {
		if (rectToDraw.contains(Board.getBoard().getSquare(i, j))) {
		    System.out.println("square contains - " + i + " " + j);
		    Board.getBoard().getSquare(i, j).setBuildPreview(true);
		} else {
		    Board.getBoard().getSquare(i, j).setBuildPreview(false);
		}
	    }
	}
    }

    public static void updateSize(MouseEvent e) {
	int x = e.getX();
	int y = e.getY();
	currentRect.setSize(x - currentRect.x, y - currentRect.y);
	System.out.println("current rect size " + currentRect.getSize());
	System.out.println("perams sent " + width + " " + height);
	updateDrawableRect(width, height);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	Point mouseAt = new Point(e.getX(), e.getY());

	switch (e.getModifiers()) {
	case InputEvent.BUTTON1_MASK: {
	    BuildRoomMouse.leftClick(mouseAt);
	    System.out.println("That's the LEFT button");
	    break;
	}
	case InputEvent.BUTTON2_MASK: {
	    BuildRoomMouse.middleClick(mouseAt);
	    System.out.println("That's the MIDDLE button");
	    break;
	}
	case InputEvent.BUTTON3_MASK: {
	    BuildRoomMouse.rightClick();
	    System.out.println("That's the RIGHT button");
	    break;
	}
	}
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
	hospitalGamePanel.setMouseIn(true);
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
	hospitalGamePanel.setMouseIn(false);
    }

    @Override
    public void mousePressed(MouseEvent e) {
	System.out.println("mouse pressed");
	int x = e.getX();
	int y = e.getY();
	currentRect = new Rectangle(x, y, 0, 0);
	rectToDraw = new Rectangle(x, y, width, height);
	updateDrawableRect(rectToDraw.width, rectToDraw.height);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	/*
	 * in this section, i need to...from all the gridSquares contained in
	 * the rectangle currentRectand then find the one with the smallest
	 * topleftpoint and the onewith the largest top left point. this will
	 * give the top left square andbottom right. I can then subtract one
	 * from the other to determine theheight and width of the building.this
	 * info should then be saved to the HowToPlace currentBuild gridObject
	 * :)
	 */
	Point topLeft = new Point(10000, 10000);
	Point bottomRight = new Point(0, 0);
	int height, width;
	System.out.println("mouse released");

	for (int i = 0; i < gridWidth; i += 1) {
	    for (int j = 0; j < gridHeight; j += 1) {
		GridSquare thisSquare = Board.getBoard().getSquare(i, j);
		if (rectToDraw.contains(thisSquare)) {
		    if (thisSquare.getLocation().getX() < topLeft.getX()
			    && thisSquare.getLocation().getY() < topLeft.getY()) {
			topLeft = thisSquare.getLocation();
			System.out.println("topleft " + topLeft);
		    }
		    if (thisSquare.getLocation().getX() > topLeft.getX()
			    && thisSquare.getLocation().getY() > topLeft.getY()) {
			bottomRight = thisSquare.getLocation();
			// System.out.println("bottomRight " + bottomRight);
		    }
		}
	    }
	}

	height = (int) (bottomRight.getY() + gridSize - topLeft.getY());
	width = (int) (bottomRight.getX() + gridSize - topLeft.getX());
	System.out.println(width + " " + height);
	thisBuild.setTopLeftPoint(Game.getGame().screenToGame(topLeft));
	thisBuild.setDimentions(width / gridSize, height / gridSize);
	System.out.println(thisBuild);
	// HowToPlace.nextBuildState();
	// HowToPlace.setBuildingNowBool(false);
	currentRect = null;
	hospitalGamePanel.getGamePanel().SetMouseListener(new BuildRoomDoorMouse(thisBuild));
	hospitalGamePanel.getGamePanel().SetMouseMotionListener(new BuildRoomDoorMouse(thisBuild));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
	System.out.println("Mouse Dragged");
	// System.out.println(e.getX() + " " + e.getY() + " " + getWidth()+ " "
	// + getHeight());
	updateSize(e);
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

}