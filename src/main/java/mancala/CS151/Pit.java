package mancala.CS151;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

/**
 * Pit interface that will be the backbone of our strategy patterns
 * @author AnthonyJhong
 * @version 1.0 5/3/2019
 */

public interface Pit extends Icon{
	/**
	 * Sets the number of pebbles within the Pit
	 * @param pebbles number of pebbles to be set
	 */
	public void setNumPebbles(int pebbles);
	/**
	 * Gets the number of pebbles in the pit
	 * @return int value of the number of pebbles
	 */
	public int getNumPebbles();
	/**
	 * Sets the index of the pit
	 * @param i the index of the pit
	 */
	public void setIndex(int i);
	/**
	 * Returns the index of the pit
	 * @return index
	 */
	public int getIndex();
	/**
	 * Sets the Rectangle of the pit
	 * @param rect Rectangle
	 */
	public void setRect(Rectangle2D.Double rect);
	public Rectangle2D.Double getRect();
	/**
	 * Contains checks if point is within the rectangle
	 * @param p paint 
	 * @return boolean
	 */
	public boolean contains(Point2D.Double p);
	/**
	 * decrements the number of pebbles by 1
	 */
	public void decrementPebbles();
	/**
	 * adds 1 to the number of pebbles
	 */
	public void incrementPebbles();
	/**
	 * Sets the number of Pebbles to zero
	 */
	public void setPebblesZero();
	
}
