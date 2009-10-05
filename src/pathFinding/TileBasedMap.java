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

/**
 * The description for the data we're pathfinding over. This provides the
 * contract between the data being searched (i.e. the in game map) and the path
 * finding generic tools
 * 
 * @author Kevin Glass
 */
public interface TileBasedMap {
    /**
     * Get the width of the tile map. The slightly odd name is used to
     * distiguish this method from commonly used names in game maps.
     * 
     * @return The number of tiles across the map
     */
    public int getWidthInTiles();

    /**
     * Get the height of the tile map. The slightly odd name is used to
     * distiguish this method from commonly used names in game maps.
     * 
     * @return The number of tiles down the map
     */
    public int getHeightInTiles();

    /**
     * Notification that the path finder visited a given tile. This is used for
     * debugging new heuristics.
     * 
     * @param x
     *            The x coordinate of the tile that was visited
     * @param y
     *            The y coordinate of the tile that was visited
     */
    public void pathFinderVisited(int x, int y);

    /**
     * Check if the given location is blocked, i.e. blocks movement of the
     * supplied mover.
     * 
     * @param sx
     *            The x of the moving from tile
     * @param sy
     *            The y of the moving from tile
     * @param x 
     * 		The x coordinate of the tile to check
     * @param y 
     * 		The y coordinate of the tile to check
     * @return True if the location is blocked
     */
    public boolean blocked(int sx, int sy, int x, int y);
    public boolean blocked(int tx, int ty);

    /**
     * Get the cost of moving through the given tile. This can be used to make
     * certain areas more desirable. A simple and valid implementation of this
     * method would be to return 1 in all cases.
     * 
     * @param sx
     *            The x coordinate of the tile we're moving from
     * @param sy
     *            The y coordinate of the tile we're moving from
     * @param tx
     *            The x coordinate of the tile we're moving to
     * @param ty
     *            The y coordinate of the tile we're moving to
     * @return The relative cost of moving across the given tile
     */
    public float getCost(int sx, int sy, int tx, int ty);

}
