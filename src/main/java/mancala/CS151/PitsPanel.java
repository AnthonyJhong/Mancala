package mancala.CS151;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
/**
 * This will out pit the pits to the GUI
 * @author AnthonyJhong
 * @version 1.0 5/2/2019
 */

public class PitsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Model_Example model;
	private Pit[] pits;
	private String[] labels = {"", "A6", "A5", "A4", "A3", "A2", "A1", "",  
			"B1", "B2", "B3", "B4", "B5", "B6"};
	
	public PitsPanel(Model_Example mancala ){

		model  = mancala;
		pits = model.getPit();
		setLayout(new BorderLayout());
		add(new JLabel("    "), BorderLayout.WEST);
		
		
		JPanel pitsPanel = new JPanel();
		pitsPanel.setLayout(new GridLayout(2, 6, 2, 2));
		
		
		for(int i = 13; i > 7; i--) {
			final JLabel pitLabel = new JLabel();
			pitLabel.setLayout(new BorderLayout());
			pitLabel.setIcon(pits[i]);
			JLabel pitNums = new JLabel(labels[i]);
			pitLabel.add(pitNums, BorderLayout.SOUTH);
			
			if(pits[0].getClass().equals(new SpartanLayout(0,0,0,0).getClass()))
				pitNums.setForeground(Color.WHITE);
			else
				pitNums.setForeground(Color.BLACK);
			
			pitLabel.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
				}
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					Pit temp = (Pit) pitLabel.getIcon();
					model.playTurn(temp.getIndex());
				}
				@Override
				public void mouseReleased(MouseEvent e) {}

				@Override
				public void mouseEntered(MouseEvent e) {}

				@Override
				public void mouseExited(MouseEvent e) {}
				
			});
			pitsPanel.add(pitLabel);
			pitLabel.repaint();
		}
		
		for(int i = 6; i > 0; i--) {
			final JLabel pitLabel = new JLabel();
			pitLabel.setLayout(new BorderLayout());
			pitLabel.setIcon(pits[i]);
			JLabel pitNums = new JLabel(labels[i]);
			pitLabel.add(pitNums, BorderLayout.SOUTH);
			if(pits[0].getClass().equals(new SpartanLayout(0,0,0,0).getClass()))
				pitNums.setForeground(Color.WHITE);
			else
				pitNums.setForeground(Color.BLACK);

			pitLabel.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					Pit temp = (Pit) pitLabel.getIcon();
					model.playTurn(temp.getIndex());
				}

				@Override
				public void mouseReleased(MouseEvent e) {}

				@Override
				public void mouseEntered(MouseEvent e) {}

				@Override
				public void mouseExited(MouseEvent e) {}
				
			});
			pitsPanel.add(pitLabel);
			pitLabel.repaint();
		}
		
	

		add(pitsPanel, BorderLayout.CENTER);
	}

}
