package mancala.CS151;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Model_Example will replicate all the logic of a traditional Mancala Game
 * @author AnthonyJhong
 * @version 1.0 5/1/2019
 */

public class Model_Example {

	private Pit[] pit;
	private Pit[] previousPit;

	private ArrayList<ChangeListener> listeners;

	// does player A require an extra turn?
	boolean extraTurn = false;
	boolean extraTurn_B = false;
	private boolean playerAturn = true;
	private boolean gameIsOver = false;
	private boolean previousTurn = true;
	private int undosAvail;
	
	/**
	 * Constructor of Model_Example 
	 * @param rocks number of rocks per pit
	 * @param layout the layout being used
	 */
	public Model_Example(int rocks, String layout){
		undosAvail = 3;
		if(layout.equals("Spartan")) {
			listeners = new ArrayList<>();
			pit = new SpartanLayout[14];
			previousPit = new Pit[14];

			for(int i =1; i < 7; i++){
				pit[i] = new SpartanLayout(rocks,0 ,20, i);
				pit[i+7] = new SpartanLayout(rocks ,0 , 20 ,i+7);
			}

			pit[0] = new SpartanLayout(0, 0 ,60, 0);
			pit[7] = new SpartanLayout(0, 0, 60, 7);

			for(int i = 1; i < 7; i++) {
				previousPit[i] = new SpartanLayout(rocks,0 ,20, i);
				previousPit[i+7] = new SpartanLayout(rocks ,0 , 20 ,i+7);
			}
			previousPit[0] = new SpartanLayout(0, 0 ,60, 0);
			previousPit[7] = new SpartanLayout(0, 0, 60, 7);
		}

		else {
			listeners = new ArrayList<>();
			pit = new RainbowLayout[14];
			previousPit = new Pit[14];

			for(int i =1; i < 7; i++){
				pit[i] = new RainbowLayout(rocks,0 ,20, i);
				pit[i+7] = new RainbowLayout(rocks ,0 , 20 ,i+7);
			}

			pit[0] = new RainbowLayout(0, 0 ,60, 0);
			pit[7] = new RainbowLayout(0, 0, 60, 7);

			for(int i = 1; i < 7; i++) {
				previousPit[i] = new RainbowLayout(rocks,0 ,20, i);
				previousPit[i+7] = new RainbowLayout(rocks ,0 , 20 ,i+7);
			}
			previousPit[0] = new RainbowLayout(0, 0 ,60, 0);
			previousPit[7] = new RainbowLayout(0, 0, 60, 7);
		}

	}
	/**
	 * Sets the number of rocks to each small pit
	 * @param rocks
	 */
	public void setRocks(int rocks) {
		for(int i =1; i < 7; i++){
			pit[i].setNumPebbles(rocks);
			pit[i+7].setNumPebbles(rocks);
		}
		for(int i = 1; i < 7; i++) {
			previousPit[i].setNumPebbles(rocks);
			previousPit[i+7].setNumPebbles(rocks);
		}
		this.change();
	}
	/**
	 * setPlayerATurn to true or false
	 * @param playerAturn boolean
	 */
	public void setPlayerAturn(boolean playerAturn) {
		this.playerAturn = playerAturn;
	}

	/**
	 * returns the value of playerAturn 
	 * @return boolean
	 */
	public boolean getPlayerATurn(){
		return playerAturn;
	}
	
	/**
	 * Adds a changeListner to the ArrayList of change listeners
	 * @param e ChangeListener
	 */
	public void addChangeListener(ChangeListener e){
		listeners.add(e);
	}
	/**
	 * This will call the statChanged method of all of the ChangeListers
	 */
	private void change(){
		for(ChangeListener l : listeners){
			l.stateChanged(new ChangeEvent(this));
		}
	}
	/**
	 * Returns theArray of type Pit
	 * @return Array of Pit
	 */
	public Pit[] getPit() {
		return pit;
	}

	/*
    This method takes in a param that represents a pit.
    That index is in range from 1-6 for player A and 8-9 for Player B.
    The pebbles must move counter clockwise.
	 */
	public void playTurn(int index) {
		if(index!= 7 && index!=0 && pit[index].getNumPebbles()!=0) {
			
			if(previousTurn != playerAturn) {
				undosAvail=3;
			}
			previousTurn = playerAturn;

			if (index <= 6 && playerAturn) {

				for (int i = 0; i < pit.length; i++) {

					previousPit[i].setNumPebbles(pit[i].getNumPebbles());
					previousPit[i].setRect(pit[i].getRect());
					previousPit[i].setIndex(i);
				}

				int temp = pit[index].getNumPebbles();
				pit[index].setPebblesZero();

				int lastElement = -1;

				extraTurn_B = true;
				extraTurn = true;

				setPlayerAturn(false);

				for (int i = 1; i <= temp; i++) {
					
					index -=1;
					
					if (index == -1) {

						extraTurn = false;
						extraTurn_B = true;

						index = 8;      

						for (int j = 1; j <= (temp -i) +1; j++) {

							if (index == 14) {

								HashMap<Integer, Integer> pair = new HashMap<>();

								pair.put(14, 6);
								pair.put(15, 5);
								pair.put(16, 4);
								pair.put(17, 3);
								pair.put(18, 2);
								pair.put(19, 1);
								
								pair.put(20, 0);
								pair.put(21, 8);
								pair.put(22, 9);
								pair.put(23, 10);
								pair.put(24, 11);
								pair.put(25, 12);
								pair.put(26, 13);

								for (int c = 14; c <= 14+temp - i - j+1; c++) {

									//Example: if c is equal to index 16, we set new_index = 5, which is the correct index on Player A's side
									int new_index = pair.get(c);
									pit[new_index].incrementPebbles();
									index = new_index;

								}

								break;
							}
							pit[index].incrementPebbles();
							index++;
						}
						lastElement = (index);
						break;
					}

					// the lastElement will contain the last element iterated over in the array
					lastElement = (index);
					pit[(index)].incrementPebbles();

				}
				
				if(lastElement  ==0) {
					extraTurn = true;
				}

				if (lastElement > 0 && lastElement < 7) {
					if (pit[lastElement].getNumPebbles() == 1 && pit[lastElement + 7].getNumPebbles() != 0) {

						int myPit = pit[lastElement].getNumPebbles();
						pit[lastElement].setPebblesZero();
						myPit += pit[(lastElement + 7)].getNumPebbles();
						pit[(lastElement + 7)].setPebblesZero();

						pit[0].setNumPebbles(pit[0].getNumPebbles() + myPit);

					}

				} else if (extraTurn) {
					setPlayerAturn(true);
				}

				sideAEmpty();
				sideBEmpty();

				this.change();   // notify each change listener in arraylist

			} else if (!playerAturn && index>=8){


				for (int i = 0; i < pit.length; i++) {
					previousPit[i].setNumPebbles(pit[i].getNumPebbles());
					previousPit[i].setRect(pit[i].getRect());
					previousPit[i].setIndex(i);
				}

				int temp = pit[index].getNumPebbles();
				pit[index].setPebblesZero();

				// does player A require an extra turn?
				extraTurn = true;
				setPlayerAturn(true);
				int lastElementB = -1;
				extraTurn_B = false;
				
				for (int i = 1; i <= temp; i++) {
					index += 1;
					
					if (index == 14) {

						index = 7; //if the index is 14 set the index to 7
						
						int tempIndex = index;
						
						for (int j = tempIndex; j >= ((tempIndex + i) - temp); j--) {
							
							if (index == 0) {
								HashMap<Integer, Integer> pair = new HashMap<>();
								pair.put(0, 8);
								pair.put(-1, 9);
								pair.put(-2, 10);
								pair.put(-3, 11);
								pair.put(-4, 12);
								pair.put(-5, 13);
								
								pair.put(-6, 7);
								pair.put(-7, 6);
								pair.put(-8, 5);
								pair.put(-9, 4);
								pair.put(-10, 3);
								pair.put(-11, 2);
								pair.put(-12, 1);

								for (int k = 0; k >= j -temp+ 7+i; k--) {
									pit[pair.get(k)].incrementPebbles();
									index = pair.get(k);

								}
								
								break;
							}
							if(j != ((tempIndex + i) - temp))
								index--;
							pit[j].incrementPebbles();
						}
						break;
					}
					pit[index].incrementPebbles();

				}
				// the lastElement will contain the last element iterated over in the array
				lastElementB = (index);
				
				if(lastElementB == 7) 
					extraTurn_B = true;
				else 
					extraTurn_B = false;


				if (lastElementB > 7 && lastElementB < 14) {//change her
					if (pit[lastElementB].getNumPebbles() == 1 && pit[lastElementB - 7].getNumPebbles() != 0) {

						int myPit = pit[lastElementB].getNumPebbles();  
						pit[lastElementB].setPebblesZero();
						myPit += pit[(lastElementB - 7)].getNumPebbles();
						pit[(lastElementB - 7)].setPebblesZero();
						pit[7].setNumPebbles(pit[7].getNumPebbles() + myPit);

					}
				}
				if (extraTurn_B) {
					setPlayerAturn(false);
				}

				sideBEmpty();
				sideAEmpty();

				this.change();
			}else{

				System.out.println("It is not your turn. Please enter correct index");

			}
			
			promptEndScreen();
			this.change();   // notify each change listener in arraylist

		}
	}
	/**
	 * Undos the last turn that the player played can only happen for the previous instance nothing more than that
	 */
	public void undo() {
		
		boolean allTheSame = true;
		for(int i =0; i < pit.length; i++){
			if(pit[i].getNumPebbles() != previousPit[i].getNumPebbles()) {
				allTheSame = false;
			}
		}
		if(undosAvail > 0 && !allTheSame) {
			if(playerAturn) setPlayerAturn(false);         // this works for A
			else if(!playerAturn) setPlayerAturn(true);  //this only works for B
			if(extraTurn) setPlayerAturn(true);
			if(extraTurn_B) setPlayerAturn(false);
	
	
			for(int i =0; i < pit.length; i++){
				pit[i].setNumPebbles(previousPit[i].getNumPebbles());
				pit[i].setRect(previousPit[i].getRect());
				pit[i].setIndex(i);
			}
	
			playerAturn = previousTurn;
			undosAvail--;
			this.change();
		}
	}

	/**
	 * Sets gameIsOver to true if all of the small pits have a value of 0
	 */
	public void promptEndScreen() {
		int totalPebblesLeft = 0;
		for(int i = 1; i < 7; i++) {
			totalPebblesLeft += pit[i].getNumPebbles();
			totalPebblesLeft += pit[i+7].getNumPebbles();
		}
		if(totalPebblesLeft == 0) {
			gameIsOver = true;
		}
	}
	/**
	 * Returns gameIsOver boolean
	 * @return boolean gameIsOVer
	 */
	public boolean getGameIsOver() {
		return gameIsOver;
	}
	/**
	 * Returns the number of undos available 
	 * @return
	 */
	public int getUndosLeft() {
		return undosAvail;
	}
	/**
	 * Checks to see if all of A's pits are empty if they are the rest of the stones go to B's Mancala
	 */
	public void sideAEmpty() {
		int sum = 0;
		for(int i = 1; i < 7; i++) {
			sum += pit[i].getNumPebbles();
		}
		if(sum == 0) {
			for(int i = 8; i < 14; i++) {
				sum += pit[i].getNumPebbles();
				pit[i].setPebblesZero();
			}
			pit[7].setNumPebbles(pit[7].getNumPebbles()+sum);
		}
	}
	/**
	 * Checks to see if all of B's pits are empty if they are the rest of the stones go to A's mancala
	 */
	public void sideBEmpty() {
		int sum = 0;
		for(int i = 8; i < 14; i++) {
			sum += pit[i].getNumPebbles();
		}
		if(sum == 0) {
			for(int i = 1; i < 7; i++) {
				sum += pit[i].getNumPebbles();
				pit[i].setPebblesZero();
			}
			pit[0].setNumPebbles(pit[0].getNumPebbles()+sum);
		}
	}
}
