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

import java.util.ArrayList;

/**
 * A path determined by some path finding algorithm. A series of steps from the
 * starting location to the target location. This includes a step for the
 * initial location.
 * 
 * @author Kevin Glass
 */
public class Path {
    /** The list of steps building up this path */
    private ArrayList steps = new ArrayList();

    /**
     * Create an empty path
     */
    public Path() {

    }

    /**
     * Get the length of the path, i.e. the number of steps
     * 
     * @return The number of steps in this path
     */
    public int getLength() {
	return steps.size();
    }

    /**
     * Get the step at a given index in the path
     * 
     * @param index
     *            The index of the step to retrieve. Note this should be >= 0
     *            and < getLength();
     * @return The step information, the position on the map.
     */
    public Step getStep(int index) {
	return (Step) steps.get(index);
    }

    /**
     * Get the x coordinate for the step at the given index
     * 
     * @param index
     *            The index of the step whose x coordinate should be retrieved
     * @return The x coordinate at the step
     */
    public int getX(int index) {
	return getStep(index).x;
    }

    /**
     * Get the y coordinate for the step at the given index
     * 
     * @param index
     *            The index of the step whose y coordinate should be retrieved
     * @return The y coordinate at the step
     */
    public int getY(int index) {
	return getStep(index).y;
    }

    /**
     * Append a step to the path.
     * 
     * @param x
     *            The x coordinate of the new step
     * @param y
     *            The y coordinate of the new step
     */
    public void appendStep(int x, int y) {
	steps.add(new Step(x, y));
    }

    /**
     * Prepend a step to the path.
     * 
     * @param x
     *            The x coordinate of the new step
     * @param y
     *            The y coordinate of the new step
     */
    public void prependStep(int x, int y) {
	steps.add(0, new Step(x, y));
    }

    public void nextStep() {
	steps.remove(0);
    }

    /**
     * Check if this path contains the given step
     * 
     * @param x
     *            The x coordinate of the step to check for
     * @param y
     *            The y coordinate of the step to check for
     * @return True if the path contains the given step
     */
    public boolean contains(int x, int y) {
	return steps.contains(new Step(x, y));
    }

    /**
     * A single step within the path
     * 
     * @author Kevin Glass
     */
    public class Step {
	/** The x coordinate at the given step */
	private int x;
	/** The y coordinate at the given step */
	private int y;

	/**
	 * Create a new step
	 * 
	 * @param x
	 *            The x coordinate of the new step
	 * @param y
	 *            The y coordinate of the new step
	 */
	public Step(int x, int y) {
	    this.x = x;
	    this.y = y;
	}

	/**
	 * Get the x coordinate of the new step
	 * 
	 * @return The x coodindate of the new step
	 */
	public int getX() {
	    return x;
	}

	/**
	 * Get the y coordinate of the new step
	 * 
	 * @return The y coodindate of the new step
	 */
	public int getY() {
	    return y;
	}

	/**
	 * @see Object#hashCode()
	 */
	public int hashCode() {
	    return x * y;
	}

	/**
	 * @see Object#equals(Object)
	 */
	public boolean equals(Object other) {
	    if (other instanceof Step) {
		Step o = (Step) other;

		return (o.x == x) && (o.y == y);
	    }

	    return false;
	}
    }
}
