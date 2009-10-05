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

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;

import pathFinding.AStarPathFinder;
import pathFinding.GameMap;
import pathFinding.PathFinder;
import GridObjects.GridObject;
import GridObjects.Buildings.Room;
import GridObjects.items.Item;
import GridObjects.items.UsableItem;
import GridObjects.people.Person;

public class Game {
    private static Game ref;

    final int gridSize = 30;
    final int panelHeight = 1000;
    final int panelWidth = 1400;
    final int gridHeight = panelHeight / gridSize;
    final int gridWidth = panelWidth / gridSize;

    // This arraylist holds all rooms
    private ArrayList<Room> rooms = new ArrayList<Room>();
    // Arraylist to hold people, patient or otherwise
    private ArrayList<Person> people = new ArrayList<Person>();
    // This arraylist holds all general items
    private ArrayList<UsableItem> usableItems = new ArrayList<UsableItem>();
    // This array holds any grid objects that need a build preview.
    // It should be cleared before the game is saved.
    private ArrayList<GridObject> buildingPreview = new ArrayList<GridObject>();

    /** The map on which the units will move */
    private static GameMap map = new GameMap();
    /** The path finder we'll use to search our map */
    private static PathFinder finder = new AStarPathFinder(map, 500, false);
    
    private LevelMap levelMap = new LevelMap();

    public PathFinder getFinder() {
	return finder;
    }

    private Game() {}

    public static GameMap getMap() {
        return map;
    }

    public LevelMap getLevelMap() {
        return levelMap;
    }

    public ArrayList<Room> getRooms() {
	return rooms;
    }

    public ArrayList<Person> getPeople() {
	return people;
    }

    public ArrayList<GridObject> getBuildingPreview() {
	return buildingPreview;
    }

    public ArrayList<UsableItem> getUsableItems() {
	return usableItems;
    }

    public void clearBuildingPreview() {
	buildingPreview.clear();
    }

    public Room getRoom(int index) {
	return rooms.get(index);
    }

    public Person getPerson(int index) {
	return people.get(index);
    }

    public GridObject getBuildingPreviewi(int index) {
	return buildingPreview.get(index);
    }

    public Item getItem(int index) {
	return usableItems.get(index);
    }

    public void setRoom(int index, Room room) {
	rooms.set(index, room);
    }

    public void setPerson(int index, Person person) {
	people.set(index, person);
    }

    public void setBuildingPreview(int index, GridObject gridObject) {
	buildingPreview.set(index, gridObject);
    }

    public void setItem(int index, UsableItem item) {
	usableItems.set(index, item);
    }

    public void addRoom(GridObject room) {
	rooms.add((Room) room);
    }

    public void addPerson(Person person) {
	people.add(person);
    }

    public void addBuildingPreview(GridObject gridObject) {
	buildingPreview.add(gridObject);
    }

    public void addUsableItem(GridObject item) {
	usableItems.add((UsableItem) item);
    }

    public static synchronized Game getGame() {
	if (ref == null)
	    // it's ok, we can call this constructor
	    ref = new Game();
	return ref;
    }

    public Object clone() throws CloneNotSupportedException {
	throw new CloneNotSupportedException();
	// that'll teach 'em. prevents clone method.
    }

    public int getGridSize() {
	return gridSize;
    }

    public int getPanelHeight() {
	return panelHeight;
    }

    public int getPanelWidth() {
	return panelWidth;
    }

    public int getGridHeight() {
	return gridHeight;
    }

    public int getGridWidth() {
	return gridWidth;
    }
    
    public Point screenToGame(Point point){
	return new Point(point.x/Game.getGame().getGridSize(),point.y/Game.getGame().getGridSize());
    }
    
    public Point gameToScreen(Point point){
	return new Point(point.x*Game.getGame().getGridSize(),point.y*Game.getGame().getGridSize());
    }
    
    //TODO: Rather than saving and loading the board, it can be made so when loading, the squares are refilled and set
    public void saveGame(){
	XStream xstream = new XStream();
	
	try {
            FileOutputStream fs = new FileOutputStream("C:/temp/testGameText.txt");
            xstream.toXML(Game.getGame(), fs);
            fs.close();
            FileOutputStream fs2 = new FileOutputStream("C:/temp/testBoardText.txt");
            xstream.toXML(Board.getBoard(), fs2);
            fs2.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    public void loadGame(){
	XStream xstream = new XStream();
	
	try {
	    FileInputStream fi = new FileInputStream("C:/temp/testGameText.txt");
	    xstream.fromXML(fi, Game.getGame());
	    fi.close();
	    FileInputStream fi2 = new FileInputStream("C:/temp/testBoardText.txt");
	    xstream.fromXML(fi2, Board.getBoard());
	    fi2.close();
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
