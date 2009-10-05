package mouseActions;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import level.Board;
import level.Game;
import level.GridSquare;
import level.LevelMap;
import level.GridSquare.SquareType;

public class LevelEditMouse implements MouseListener {
    
    SquareType squareType;
    public LevelEditMouse(SquareType squareType){
	this.squareType = squareType;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	Point mouseAt = new Point(e.getX(),e.getY());
	for (int i = 0; i < Game.getGame().getGridWidth(); i += 1) {
		for (int j = 0; j < Game.getGame().getGridHeight(); j += 1) {
		    GridSquare thisSquare = Board.getBoard().getSquare(i, j);
		    if(thisSquare.contains(mouseAt)){
			thisSquare.setSquareType(squareType);
		    }
		}
	}
	Game.getGame().getLevelMap().setBlocked();
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub

    }

}
