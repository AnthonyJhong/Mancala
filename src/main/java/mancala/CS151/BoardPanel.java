package mancala.CS151;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;

/**
 * This class will out put the mancala pits and the player pits and put them together
 * @author AnthonyJhong
 * @version 1.0 5/2/2019
 */

public class BoardPanel extends JPanel implements ChangeListener{
	private static final long serialVersionUID = 1L;
	private Model_Example game; //Model
	private Pit[] pits; //The model data
	private PitsPanel pitsPanel; //has a pits panel that will display the small pits

	private JLabel aScore; //prints the score for player a
	private JLabel bScore; //prints the score for player b
	private JLabel undosLeft;
	
	public BoardPanel(Model_Example game, PitsPanel panel) {
		this.game = game; 
		pitsPanel = panel;
		pits = game.getPit();

		setSize(new Dimension(900, 400));
		
		setLayout(new BorderLayout()); //sets layout
		
		JLabel playerBMancala = new JLabel(pits[7]);
		playerBMancala.setLayout(new BorderLayout());
		bScore = new JLabel();
		bScore.setText("B: " + pits[7].getNumPebbles());
		playerBMancala.add(bScore, BorderLayout.SOUTH);
		bScore.repaint();
		
		add(playerBMancala, BorderLayout.WEST);
		playerBMancala.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerBMancala.setPreferredSize(new Dimension(100, 120));
		playerBMancala.repaint();
		
		JLabel playerAMancala = new JLabel(pits[0]);
		playerAMancala.setLayout(new BorderLayout());
		aScore = new JLabel();
		aScore.setText("A: " +  pits[0].getNumPebbles());
		playerAMancala.add(aScore, BorderLayout.SOUTH);
		
		playerAMancala.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(playerAMancala, BorderLayout.EAST);
		playerAMancala.setPreferredSize(new Dimension(100, 120));
		playerAMancala.repaint();
		
		add(pitsPanel, BorderLayout.CENTER);
		
		JPanel undoPanel = new JPanel();
		undoPanel.setLayout(new BorderLayout());
		
		JButton undo = new JButton("↺");
		undo.setPreferredSize(new Dimension(60,40));
		undo.setFont(new Font("Times New Roman", Font.BOLD, 30));
		undoPanel.add(undo, BorderLayout.WEST);
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.undo();
			}
		});
		undosLeft = new JLabel();
		undosLeft.setText("Undos Left: " + game.getUndosLeft());
		undoPanel.add(undosLeft);
		add(undoPanel, BorderLayout.NORTH);
		undoPanel.repaint();
		
		
		setVisible(true);
	}
	/**
	 * This will update this panel if the data has been changed
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		pits = game.getPit();
		aScore.setText("A: " + pits[0].getNumPebbles());
		bScore.setText("B: " + pits[7].getNumPebbles());
		undosLeft.setText("Undos Left: " + game.getUndosLeft());
		repaint();
		
		if(game.getGameIsOver()) {
				JFrame endScreen = new JFrame("End Screen");
				endScreen.setSize(500, 150);
				endScreen.setLayout(new BorderLayout());
				JLabel winner = new JLabel();
				winner.setFont(new Font("Cursive", Font.BOLD, 40));
				winner.setForeground(new Color(186, 171, 11));
				if(pits[0].getNumPebbles() < pits[7].getNumPebbles()) {
					winner.setText("The winner is Player B");
				}
				else 
					winner.setText("The winner is player A");
				
				endScreen.add(winner, BorderLayout.CENTER);
				JButton exit = new JButton("Exit");
				exit.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.exit(0);
					}
					
				});
				endScreen.add(exit, BorderLayout.SOUTH);
				endScreen.setLocationRelativeTo(null);
				endScreen.setVisible(true);
				endScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
	}
}
