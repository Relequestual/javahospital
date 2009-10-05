/*******************************************************************************
 * Copyright (c) 2009, Kevin Glass
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * 
 *     * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *     * Neither the name of CokeAndCode nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/
package pathFinding;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import pathFinding.heuristics.ManhattanHeuristic;

/**
 * A path finder implementation that uses the AStar heuristic based algorithm to
 * determine a path.
 * 
 * @author Kevin Glass
 */
public class AStarPathFinder implements PathFinder {
    /** The set of nodes that have been searched through */
    private ArrayList closed = new ArrayList();
    /** The set of nodes that we do not yet consider fully searched */
    private SortedList open = new SortedList();

    /** The map being searched */
    private TileBasedMap map;
    /** The maximum depth of search we're willing to accept before giving up */
    private int maxSearchDistance;

    /** The complete set of nodes across the map */
    private Node[][] nodes;
    /** True if we allow diaganol movement */
    private boolean allowDiagMovement;
    /** The heuristic we're applying to determine which nodes to search first */
    // private AStarHeuristic heuristic;
    private AStarHeuristic heuristic;
    
    /**This varible can be changed to false to stop randomisation of the nodes 
     * when searching the neighbours for possible next steps */
    boolean randomiseNodes = true;

    /**
     * Create a path finder with the default heuristic - closest to target.
     * 
     * @param map
     *            The map to be searched
     * @param maxSearchDistance
     *            The maximum depth we'll search before giving up
     * @param allowDiagMovement
     *            True if the search should try diaganol movement
     */
    public AStarPathFinder(TileBasedMap map, int maxSearchDistance, boolean allowDiagMovement) {
	this(map, maxSearchDistance, allowDiagMovement, new ManhattanHeuristic(1));
    }

    /**
     * Create a path finder
     * 
     * @param heuristic
     *            The heuristic used to determine the search order of the map
     * @param map
     *            The map to be searched
     * @param maxSearchDistance
     *            The maximum depth we'll search before giving up
     * @param allowDiagMovement
     *            True if the search should try diaganol movement
     */
    public AStarPathFinder(TileBasedMap map, int maxSearchDistance, boolean allowDiagMovement, AStarHeuristic heuristic) {
	this.heuristic = heuristic;
	this.map = map;
	this.maxSearchDistance = maxSearchDistance;
	this.allowDiagMovement = allowDiagMovement;

	nodes = new Node[map.getWidthInTiles()][map.getHeightInTiles()];
	for (int x = 0; x < map.getWidthInTiles(); x++) {
	    for (int y = 0; y < map.getHeightInTiles(); y++) {
		nodes[x][y] = new Node(x, y);
	    }
	}
    }

    /**
     * @see PathFinder#findPath(Mover, int, int, int, int)
     */
    public Path findPath(int sx, int sy, int tx, int ty) {
	// easy first check, if the destination is blocked, we can't get there
	if (map.blocked(tx, ty)) {
	    System.err.println("Destination is a blocked square. bad error m8.");
	    return null;
	}

	// initial state for A*. The closed group is empty. Only the starting
	// tile is in the open list and it's cost is zero, i.e. we're already
	// there
	System.out.println("path length " + nodes.length);
	nodes[sx][sy].cost = 0;
	nodes[sx][sy].depth = 0;
	closed.clear();
	open.clear();
	open.add(nodes[sx][sy]);

	nodes[tx][ty].parent = null;

	// while we haven't found the goal and haven't exceeded our max search
	// depth
	int maxDepth = 0;
	while ((maxDepth < maxSearchDistance) && (open.size() != 0)) {
	    // pull out the first node in our open list, this is determined to
	    // be the most likely to be the next step based on our heuristic
	    Node current = getFirstInOpen();
	    if (current == nodes[tx][ty]) {
		break;
	    }

	    removeFromOpen(current);
	    addToClosed(current);

	    // search through all the neighbours of the current node evaluating
	    // them as next steps
	    LinkedList<Point> NBourNodes = createNodeNboursRand(current.x,current.y);
	    for(Point node : NBourNodes){
		    // not a neighbour, its the current tile
		    if ((node.x-current.x == 0) && (node.y-current.y == 0)) {
			continue;
		    }

		    // if we're not allowing diagonal movement then only
		    // one of x or y can be set
		    if (!allowDiagMovement) {
			if ((node.x-current.x != 0) && (node.y-current.y != 0)) {
			    continue;
			}
		    }

		    // determine the location of the neighbour and evaluate it
		    int xp = node.x;
		    int yp = node.y;

		    if (isValidLocation(current.x, current.y, xp, yp)) {
			// the cost to get to this node is cost the current plus
			// the movement
			// cost to reach this node. Note that the heursitic
			// value is only used
			// in the sorted open list
			float nextStepCost = current.cost + getMovementCost(current.x, current.y, xp, yp);
			Node neighbour = nodes[xp][yp];
			map.pathFinderVisited(xp, yp);

			// if the new cost we've determined for this node is
			// lower than
			// it has been previously makes sure the node hasn't
			// been discarded. We've
			// determined that there might have been a better path
			// to get to
			// this node so it needs to be re-evaluated
			if (nextStepCost < neighbour.cost) {
			    if (inOpenList(neighbour)) {
				removeFromOpen(neighbour);
			    }
			    if (inClosedList(neighbour)) {
				removeFromClosed(neighbour);
			    }
			}

			// if the node hasn't already been processed and
			// discarded then
			// reset it's cost to our current cost and add it as a
			// next possible
			// step (i.e. to the open list)
			if (!inOpenList(neighbour) && !(inClosedList(neighbour))) {
			    neighbour.cost = nextStepCost;
			    neighbour.heuristic = getHeuristicCost(xp, yp, tx, ty);
			    maxDepth = Math.max(maxDepth, neighbour.setParent(current));
			    addToOpen(neighbour);
			}
		    }
	    }
	}

	// since we've got an empty open list or we've run out of search
	// there was no path. Just return null
	if (nodes[tx][ty].parent == null) {
	    return null;
	}

	// At this point we've definitely found a path so we can uses the parent
	// references of the nodes to find out way from the target location back
	// to the start recording the nodes on the way.
	Path path = new Path();
	Node target = nodes[tx][ty];
	while (target != nodes[sx][sy]) {
	    path.prependStep(target.x, target.y);
	    target = target.parent;
	}
	path.prependStep(sx, sy);

	// thats it, we have our path
	return path;
    }
    
    /*This method is a sort of test to see if the node neighbours can be randomised
     * to create a more randomised walking paten... 
     */
    public LinkedList<Point> createNodeNboursRand(int cx, int cy){
	LinkedList<Point> theseNodes = new LinkedList<Point>();
	for (int x = -1; x < 2; x++) {
		for (int y = -1; y < 2; y++) {
		    theseNodes.add(new Point(x+cx,y+cy));
		}
	}
	//This boolean can be turned off in the event there is need to debug path finding
	if(this.randomiseNodes){
	    Collections.shuffle(theseNodes);
	}
	return theseNodes;
    }

    /**
     * Get the first element from the open list. This is the next one to be
     * searched.
     * 
     * @return The first element in the open list
     */
    protected Node getFirstInOpen() {
	return (Node) open.first();
    }

    /**
     * Add a node to the open list
     * 
     * @param node
     *            The node to be added to the open list
     */
    protected void addToOpen(Node node) {
	open.add(node);
    }

    /**
     * Check if a node is in the open list
     * 
     * @param node
     *            The node to check for
     * @return True if the node given is in the open list
     */
    protected boolean inOpenList(Node node) {
	return open.contains(node);
    }

    /**
     * Remove a node from the open list
     * 
     * @param node
     *            The node to remove from the open list
     */
    protected void removeFromOpen(Node node) {
	open.remove(node);
    }

    /**
     * Add a node to the closed list
     * 
     * @param node
     *            The node to add to the closed list
     */
    protected void addToClosed(Node node) {
	closed.add(node);
    }

    /**
     * Check if the node supplied is in the closed list
     * 
     * @param node
     *            The node to search for
     * @return True if the node specified is in the closed list
     */
    protected boolean inClosedList(Node node) {
	return closed.contains(node);
    }

    /**
     * Remove a node from the closed list
     * 
     * @param node
     *            The node to remove from the closed list
     */
    protected void removeFromClosed(Node node) {
	closed.remove(node);
    }

    /**
     * Check if a given location is valid for the supplied mover
     * 
     * @param sx
     *            The starting x coordinate
     * @param sy
     *            The starting y coordinate
     * @param x
     *            The x coordinate of the location to check
     * @param y
     *            The y coordinate of the location to check
     * @return True if the location is valid for the given mover
     */
    protected boolean isValidLocation(int sx, int sy, int x, int y) {
	boolean invalid = (x < 0) || (y < 0) || (x >= map.getWidthInTiles()) || (y >= map.getHeightInTiles());

	if ((!invalid) && ((sx != x) || (sy != y))) {
	    invalid = map.blocked(sx,sy,x,y);
	}

	return !invalid;
    }

    /**
     * Get the cost to move through a given location
     * 
     * @param sx
     *            The x coordinate of the tile whose cost is being determined
     * @param sy
     *            The y coordiante of the tile whose cost is being determined
     * @param tx
     *            The x coordinate of the target location
     * @param ty
     *            The y coordinate of the target location
     * @return The cost of movement through the given tile
     */
    public float getMovementCost(int sx, int sy, int tx, int ty) {
	return map.getCost(sx, sy, tx, ty);
    }

    /**
     * Get the heuristic cost for the given location. This determines in which
     * order the locations are processed.
     * 
     * @param x
     *            The x coordinate of the tile whose cost is being determined
     * @param y
     *            The y coordiante of the tile whose cost is being determined
     * @param tx
     *            The x coordinate of the target location
     * @param ty
     *            The y coordinate of the target location
     * @return The heuristic cost assigned to the tile
     */
    public float getHeuristicCost(int x, int y, int tx, int ty) {
	return heuristic.getCost(map, x, y, tx, ty);
    }

    /**
     * A simple sorted list
     * 
     * @author kevin
     */
    private class SortedList {
	/** The list of elements */
	private ArrayList list = new ArrayList();

	/**
	 * Retrieve the first element from the list
	 * 
	 * @return The first element from the list
	 */
	public Object first() {
	    return list.get(0);
	}

	/**
	 * Empty the list
	 */
	public void clear() {
	    list.clear();
	}

	/**
	 * Add an element to the list - causes sorting
	 * 
	 * @param o
	 *            The element to add
	 */
	public void add(Object o) {
	    list.add(o);
	    Collections.sort(list);
	}

	/**
	 * Remove an element from the list
	 * 
	 * @param o
	 *            The element to remove
	 */
	public void remove(Object o) {
	    list.remove(o);
	}

	/**
	 * Get the number of elements in the list
	 * 
	 * @return The number of element in the list
	 */
	public int size() {
	    return list.size();
	}

	/**
	 * Check if an element is in the list
	 * 
	 * @param o
	 *            The element to search for
	 * @return True if the element is in the list
	 */
	public boolean contains(Object o) {
	    return list.contains(o);
	}
    }

    /**
     * A single node in the search graph
     */
    private class Node implements Comparable {
	/** The x coordinate of the node */
	private int x;
	/** The y coordinate of the node */
	private int y;
	/** The path cost for this node */
	private float cost;
	/** The parent of this node, how we reached it in the search */
	private Node parent;
	/** The heuristic cost of this node */
	private float heuristic;
	/** The search depth of this node */
	private int depth;

	/**
	 * Create a new node
	 * 
	 * @param x
	 *            The x coordinate of the node
	 * @param y
	 *            The y coordinate of the node
	 */
	public Node(int x, int y) {
	    this.x = x;
	    this.y = y;
	}

	/**
	 * Set the parent of this node
	 * 
	 * @param parent
	 *            The parent node which lead us to this node
	 * @return The depth we have no reached in searching
	 */
	public int setParent(Node parent) {
	    depth = parent.depth + 1;
	    this.parent = parent;

	    return depth;
	}

	/**
	 * @see Comparable#compareTo(Object)
	 */
	public int compareTo(Object other) {
	    Node o = (Node) other;

	    float f = heuristic + cost;
	    float of = o.heuristic + o.cost;

	    if (f < of) {
		return -1;
	    } else if (f > of) {
		return 1;
	    } else {
		return 0;
	    }
	}
    }
}
