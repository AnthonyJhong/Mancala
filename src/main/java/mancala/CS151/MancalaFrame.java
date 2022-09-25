package mancala.CS151;

import java.awt.*;
import javax.swing.*;

/**
 * MancalaFrame will put all of the components together for the user
 * @author AnthonyJhong
 * @version 1.0 5/2/2019
 */

public class MancalaFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private BoardPanel board; //has a board panel that displays the board
	
	public MancalaFrame(BoardPanel p) {
		
		board = p;
		setPreferredSize(new Dimension(900, 550));
		setMinimumSize(new Dimension(900, 550));
        setMaximumSize(new Dimension(900, 550));
		
		setLayout(new BorderLayout());
		
		add(board, BorderLayout.CENTER);
		board.setAlignmentX(Component.CENTER_ALIGNMENT);
		board.setAlignmentY(Component.CENTER_ALIGNMENT);

		JLabel playera = new JLabel("Player B");
		JLabel playerb = new JLabel("Player A");
		playera.setHorizontalAlignment(SwingConstants.CENTER);
		playera.setFont(new Font("SansSerif", Font.BOLD, 30));
		add(playera, BorderLayout.NORTH);
		playerb.setHorizontalAlignment(SwingConstants.CENTER);
		playerb.setFont(new Font("SansSerif", Font.BOLD, 30));
		add(playerb, BorderLayout.SOUTH);
		
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

}
