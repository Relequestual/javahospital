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
package PanelsAndFrames.controlPanels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import PanelsAndFrames.hospitalGamePanel;

import level.Game;
import level.LevelMap;
import level.GridSquare.SquareType;
import mouseActions.LevelEditMouse;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class levelMaker extends JInternalFrame {
    private JButton bntMGrass;
    private JButton btnClear;
    private JButton btnMPath;
    private JButton btnMGround;
    private JButton btnMSpawn;
    private JButton btnSaveGame;
    private JButton btnLoadGame;

    private JPanel jPanel1;

    public levelMaker() {
	super("Level Maker", false, true);
	setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
	initGUI();

	{
	    jPanel1 = new JPanel();
	    getContentPane().add(jPanel1, BorderLayout.NORTH);
	    jPanel1.setPreferredSize(new java.awt.Dimension(200, 300));
	    {
		bntMGrass = new JButton();
		jPanel1.add(bntMGrass);
		bntMGrass.setText("Make Grass");
		bntMGrass.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
			bntMGrassActionPerformed(evt);
		    }
		});
	    }
	    {
		btnMGround = new JButton();
		jPanel1.add(btnMGround);
		btnMGround.setText("Make Ground");
		btnMGround.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
			btnMGroundActionPerformed(evt);
		    }
		});
	    }
	    {
		btnMPath = new JButton();
		jPanel1.add(btnMPath);
		btnMPath.setText("Make Path");
		btnMPath.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
			btnMPathActionPerformed(evt);
		    }
		});
	    }
	    {
		btnMSpawn = new JButton();
		jPanel1.add(btnMSpawn);
		btnMSpawn.setText("Make Spawn");
	    }
	    {
		btnClear = new JButton();
		jPanel1.add(btnClear);
		btnClear.setText("Clear All");
		btnClear.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
			btnClearActionPerformed(evt);
		    }
		});
	    }
	    {
		btnSaveGame = new JButton();
		jPanel1.add(btnSaveGame);
		btnSaveGame.setText("Save Game");
		btnSaveGame.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
			btnSaveGameActionPerformed(evt);
		    }
		});
	    }
	    {
		btnLoadGame = new JButton();
		jPanel1.add(btnLoadGame);
		btnLoadGame.setText("Load Game");
		btnLoadGame.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
			btnLoadGameActionPerformed(evt);
		    }
		});
	    }
	}
    }

    protected void btnSaveGameActionPerformed(ActionEvent evt) {
	Game.getGame().saveGame();
    }

    protected void btnLoadGameActionPerformed(ActionEvent evt) {
	Game.getGame().loadGame();
    }

    private void initGUI() {
	try {
	    pack();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void btnClearActionPerformed(ActionEvent evt) {
	Game.getGame().getPeople().clear();
	Game.getGame().getRooms().clear();
	Game.getGame().getUsableItems().clear();
	Game.getGame().getLevelMap().clearWalls();
	Game.getGame().getLevelMap().setBlocked();
    }
    
    private void bntMGrassActionPerformed(ActionEvent evt) {
	System.out.println("bntMGrass.actionPerformed, event="+evt);
	LevelEditMouse listener = new LevelEditMouse(SquareType.grass);
	hospitalGamePanel.getGamePanel().setMouseListener(listener);
    }
    
    private void btnMGroundActionPerformed(ActionEvent evt) {
	System.out.println("btnMGround.actionPerformed, event="+evt);
	LevelEditMouse listener = new LevelEditMouse(SquareType.ground);
	hospitalGamePanel.getGamePanel().setMouseListener(listener);
    }
    
    private void btnMPathActionPerformed(ActionEvent evt) {
	System.out.println("btnMPath.actionPerformed, event="+evt);
	LevelEditMouse listener = new LevelEditMouse(SquareType.path);
	hospitalGamePanel.getGamePanel().setMouseListener(listener);
    }

}
