package mancala.CS151;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.Random;

/**
 * RainbownLayout is a Pit that creates pits that are random colors pebbles remain black
 * @author AnthonyJhong
 * @version 1.0 5/3/2019
 */


public class RainbowLayout implements Pit{
	private int numPebbles;
	private int index;
	public static final int WIDTH = 100;
	public static final int HEIGHT = 10;
	public static final int MANCALA_HEIGHT = 300;
	public static final int PEBBLE_WIDTH = 10;
	private Rectangle2D.Double pit;
	private Color[] allColors = {Color.CYAN, Color.BLUE, Color.PINK, Color.lightGray, Color.YELLOW, Color.ORANGE, Color.RED,
			Color.MAGENTA, new Color(122, 213, 110), new Color(79, 135, 226), new Color(193, 5, 193), new Color(255, 2, 154),
			new Color(22, 229, 219), new Color(213, 216, 36), new Color(132, 41, 27), new Color(115, 232, 13), 
			new Color(232, 173, 12)};
	
	/**
	 * Constructor of RainbowLayout
	 * @param pebbles the number of pebbles being set
	 * @param x the x coordinate of the rectangle
	 * @param y the y coordinate of the rectangle
	 * @param ind the index of the pit
	 */
	public RainbowLayout(int pebbles, int x, int y, int ind) { //StrategyInterfaace c
		if(index == 0|| index == 7)
			pit = new Rectangle2D.Double(x, y, WIDTH, MANCALA_HEIGHT);
		else
			pit = new Rectangle2D.Double(x, y, WIDTH, HEIGHT);
		index =ind;
		numPebbles = pebbles;
	}
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) g;
		
		Random rand = new Random();
		int randInt = rand.nextInt(allColors.length);
		
		g2.setColor(allColors[randInt]);
		

		double y1 = pit.getY() + WIDTH - 10;
		double x1 = pit.getX();
		int counter = 2;

		g2.fill(pit);
	
		for (int i = 0; i < numPebbles; i++) {

				if (x1 + ((counter+2) * PEBBLE_WIDTH ) > pit.getX() + WIDTH -1) {
					counter = 2;
					y1 += 11;
				}
				g2.setColor(Color.black);
				g2.fill(new Ellipse2D.Double(x1 + PEBBLE_WIDTH * counter, y1, PEBBLE_WIDTH, PEBBLE_WIDTH));
				counter++;
		}
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return WIDTH;
	}

	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return WIDTH;
	}

	@Override
	public void setNumPebbles(int pebbles) {
		// TODO Auto-generated method stub
		numPebbles = pebbles;
	}

	@Override
	public int getNumPebbles() {
		// TODO Auto-generated method stub
		return numPebbles;
	}

	@Override
	public void setIndex(int i) {
		// TODO Auto-generated method stub
		index = i;
	}

	@Override
	public void setRect(Double rect) {
		// TODO Auto-generated method stub
		pit = rect;
	}

	@Override
	public Double getRect() {
		// TODO Auto-generated method stub
		return pit;
	}

	@Override
	public boolean contains(java.awt.geom.Point2D.Double p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void decrementPebbles() {
		// TODO Auto-generated method stub
		numPebbles--;
	}

	@Override
	public void setPebblesZero() {
		// TODO Auto-generated method stub
		numPebbles = 0;
	}
	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return index;
	}
	@Override
	public void incrementPebbles() {
		// TODO Auto-generated method stub
		numPebbles++;
	}
}
