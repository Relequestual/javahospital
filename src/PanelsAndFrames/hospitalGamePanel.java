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
package PanelsAndFrames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import level.Board;
import level.Game;
import level.GridSquare;
import level.LevelMap;
import mouseActions.BuildRoomMouse;
import mouseActions.standardGameMouse;
import GridObjects.Buildings.Room;
import GridObjects.items.UsableItem;
import GridObjects.people.Person;

@SuppressWarnings("serial")
public class hospitalGamePanel extends JPanel {

    static int gridSize = Game.getGame().getGridSize();
    static int panelHeight = Game.getGame().getPanelHeight();
    static int panelWidth = Game.getGame().getPanelWidth();
    static int gridHeight = (panelHeight / gridSize);
    static int gridWidth = (panelWidth / gridSize);

    static double scrollSpeedX = 5;
    static double scrollSpeedY = 5;
    static int scrollWidth;
    static int scrollHeight;

    static Point mouseAt = new Point(0, 0);
    static boolean mouseIn = true;

    private static hospitalGamePanel ref;

    private hospitalGamePanel() {

	setGrid();
	Game.getGame().getLevelMap().setTestLevel();
	System.out.println("done stuff");
	standardGameMouse listener = new standardGameMouse();
	addMouseListener(listener);
	addMouseMotionListener(listener);
	Timer timer = new Timer(80, new GameListener());
	timer.start();
    }

    public static synchronized hospitalGamePanel getGamePanel() {
	if (ref == null)
	    // it's ok, we can call this constructor
	    ref = new hospitalGamePanel();
	return ref;
    }

    public void setGrid() {
	System.out.println("setting grid");
	Board.getBoard();
    }

    public Rectangle getPanelBounds() {
	Rectangle bounds = new Rectangle(hospital.gameScrollPane.getBounds());
	return bounds;
    }

    public void paint(Graphics graphic) {
	int gridSize = Game.getGame().getGridSize();
	super.paintComponent(graphic);
	Color gridColour = new Color(208, 255, 255);
	Color POU = new Color(246, 144, 0);
	Color buildingPreview = new Color(168, 185, 185);

	Game.getGame().getLevelMap().paint(graphic);

	// Currently drawing layout preview
	graphic.setColor(gridColour);
	
	// This section places the buildings and will place items
	for (Room room : Game.getGame().getRooms()) {
	    room.paint(graphic);
	}

	for (UsableItem item : Game.getGame().getUsableItems()) {
	    item.paint(graphic);
	}

	for (Person person : Game.getGame().getPeople()) {
	    person.paint(graphic);
	}
	
	for (int i = 0; i < gridWidth; i += 1) {
	    for (int j = 0; j < gridHeight; j += 1) {
		GridSquare thisSquare = Board.getBoard().getSquare(i, j);
		int x, y;
		x = thisSquare.x;
		y = thisSquare.y;
		
		// build preview fills squares with grid colour
		graphic.setColor(gridColour);
		if (thisSquare.getBuildPreview()) {
		    graphic.fillRect(x, y, gridSize, gridSize);
		}

		// draw orange circles for point of usages.
		graphic.setColor(POU);
		if (thisSquare.getPointOfUsagePreview()) {
		    graphic.drawOval(x, y, gridSize, gridSize);
		}

		// TODO: This part of the graphics needs re writing to
		// compensate for multiple items
		// in the buildPreview arraylist in Game.getGame().
		graphic.setColor(buildingPreview);
		if (BuildRoomMouse.getCurrentRect() != null) {
		    // Draw a rectangle on top of the image.
		    // graphic.setXORMode(Color.white); //Color of line varies
		    // depending on image colors
		    Rectangle rectToDraw = BuildRoomMouse.getRectToDraw();
		    // System.out.println(rectToDraw);
		    graphic.drawRect(rectToDraw.x, rectToDraw.y, rectToDraw.width - 1, rectToDraw.height - 1);
		}

		if (Game.getGame().getPeople().size() != 0) {
		    if (Game.getGame().getPerson(0).getCurrentPath() != null) {
			if (Game.getGame().getPerson(0).getCurrentPath().contains(i, j)) {
			    graphic.setColor(Color.red);
			    graphic.fillRect((x) + 4, (y) + 4, 7, 7);
			}
		    }
		}
		
		// middle click make solid
		graphic.setColor(Color.black);
		if (thisSquare.getFull()) {
		    // Should implement debug mode
		    graphic.drawLine(x, y, x + 5, y + 5);
		}
		
		//Detection of grid squares round the edge of a built room
		if (thisSquare.isBuildRoomEdge()) {
		    graphic.drawLine(x+30, y, x+5, y+5);
		}

		// draw grid over all of this!
		graphic.setColor(gridColour);
		graphic.drawRect(x, y, gridSize, gridSize);
	    }
	}
    }

    private class GameListener implements ActionListener {
	public void actionPerformed(ActionEvent event) {

	    if (Game.getGame().getPeople() != null) {
		//This loop must use this format rather than each:for format so that the person can be removed
		for (int i = Game.getGame().getPeople().size()-1; i >= 0; i--) {
		    Person person = Game.getGame().getPerson(i);
		    
		    if (person.getRemove()) {
			Game.getGame().getPeople().remove(i);
		    } else {
			person.tick();
		    }
		}
	    }

	    // System.out.println(mouseIn);
	    if (mouseIn) {
		// TODO: Work out why the scroll down is so darn annoying!
		//panelScrollChecker();
	    }

	    repaint();
	}
    }

    public static boolean isMouseIn() {
	return mouseIn;
    }

    public static void setMouseIn(boolean bool) {
	mouseIn = bool;
    }

    void panelScrollChecker() {
	Point moveView = hospital.gameScrollPane.getViewport().getViewPosition();
	scrollWidth = hospital.gameScrollPane.getWidth();
	scrollHeight = hospital.gameScrollPane.getHeight();
	boolean nearLR = true;
	boolean nearUD = true;
	if (scrollWidth - 60 + moveView.getX() < mouseAt.getX()) {
	    scrollLeftRight(true, moveView);
	} else if (60 + moveView.getX() > mouseAt.getX()) {
	    scrollLeftRight(false, moveView);
	} else {
	    nearLR = false;
	}

	if (scrollHeight - 60 + moveView.getY() < mouseAt.getY()) {
	    scrollUpDown(true, moveView);
	    System.out.println("scroll down");
	} else if (60 + moveView.getY() > mouseAt.getY()) {
	    scrollUpDown(false, moveView);
	} else {
	    nearUD = false;
	}

	if (!nearLR) {
	    scrollSpeedX = 5;
	    difX = 0.5;
	}

	if (!nearUD) {
	    scrollSpeedY = 5;
	    difY = 0.5;
	}

	hospital.gameScrollPane.getViewport().setViewPosition(moveView);
    }

    double difX = 0.5;

    void scrollLeftRight(Boolean right, Point moveView) {

	if (!right && scrollSpeedX > 0 && difX > 0) {
	    scrollSpeedX = -scrollSpeedX;
	    difX = -difX;
	}
	Point view = new Point();
	if (!(moveView.getX() + scrollSpeedX < 0)
		&& !((int) (moveView.getX() + scrollSpeedX) > (panelWidth - scrollWidth))) {

	    view.setLocation(moveView.getX() + scrollSpeedX, moveView.getY());
	    mouseAt.setLocation(mouseAt.getX() + scrollSpeedX, mouseAt.getY());
	    moveView.setLocation(view);
	    if (scrollSpeedX < 20 || scrollSpeedX > -20) {
		scrollSpeedX += difX;
	    }
	    /*
	     * The following 2 else ifs, on the precondition that the next
	     * addition of scrollspeed would make the scroll view go out of
	     * bounds, that the scroll view be set to the max or min of the x or
	     * y position, making sure it will always scroll right up to the
	     * edge!
	     */
	} else if (!(moveView.getX() == 0) && difX < 0) {
	    // System.out.println("would go over x");
	    view = new Point(0, (int) moveView.getY());
	    moveView.setLocation(view);
	} else if (!(moveView.getX() + scrollSpeedX == panelWidth - scrollWidth) && difX > 0) {
	    // System.out.println("would go over x");
	    view = new Point(panelWidth - scrollWidth, (int) moveView.getY());
	    moveView.setLocation(view);
	}
    }

    double difY = 0.5;

    void scrollUpDown(Boolean up, Point moveView) {

	if (!up && scrollSpeedY > 0 && difY > 0) {
	    scrollSpeedY = -scrollSpeedY;
	    difY = -difY;
	}
	Point view = new Point();
	if (!(moveView.getY() + scrollSpeedY < 0)
		&& !((int) (moveView.getY() + scrollSpeedY) > (panelHeight - scrollHeight))) {
	    view.setLocation(moveView.getX(), moveView.getY() + scrollSpeedY);
	    mouseAt.setLocation(mouseAt.getX(), mouseAt.getY() + scrollSpeedY);
	    moveView.setLocation(view);
	    if (scrollSpeedY < 20 || scrollSpeedY > -20) {
		scrollSpeedY += difY;
	    }
	} else if (!(moveView.getY() == 0) && difY < 0) {
	    // System.out.println("would go over Y");
	    view = new Point((int) moveView.getX(), 0);
	    moveView.setLocation(view);
	} else if (!(moveView.getY() + scrollSpeedY == panelHeight - scrollHeight) && difY > 0) {
	    // System.out.println("would go over Y");
	    view = new Point((int) moveView.getX(), panelHeight - scrollHeight);
	    moveView.setLocation(view);
	}
    }

    public static Point getMouseAt() {
	return mouseAt;
    }

    public static void setMouseAt(Point mouseGiven) {
	mouseAt = mouseGiven;
    }

    public void setMouseListener(MouseListener newListener) {
	for (MouseListener ml : getMouseListeners()) {
	    removeMouseListener(ml);
	}
	addMouseListener(newListener);
	//addMouseListener(new standardGameMouse());
    }

    public void setMouseMotionListener(MouseMotionListener newListener) {
	for (MouseMotionListener ml : getMouseMotionListeners()) {
	    removeMouseMotionListener(ml);
	}
	addMouseMotionListener(newListener);
	//addMouseMotionListener(new standardGameMouse());
    }
}
