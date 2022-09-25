package mancala.CS151;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * This class will construct a start screen with two buttons to direct
 * user to correct board layout
 * @author armandobenavidez
 *
 */
public class MancalaTest{
	
	public static void main(String [] args)
	{
		JFrame frame;
		JPanel pane;
		
		frame = new JFrame("Mancala");
		pane = new JPanel();
		
		JLabel message = new JLabel("To Start Mancala Please Select A Layout");
		message.setFont(new Font("SansSerif", Font.BOLD, 12));
		message.setHorizontalAlignment(JLabel.CENTER);
		pane.add(message);
		
		
		//create the buttons and add them
		JButton choose1 = new JButton("Spartan");
		choose1.setPreferredSize(new Dimension(75,25));
		choose1.setFont(new Font("SansSarif", Font.PLAIN, 10));
		JButton choose2 = new JButton("Rainbow");
		choose2.setPreferredSize(new Dimension(75,25));
		choose2.setFont(new Font("SansSerif", Font.PLAIN, 10));
		JButton exitButton = new JButton("Exit");
		exitButton.setPreferredSize(new Dimension(75,25));
		exitButton.setFont(new Font("SansSerif", Font.PLAIN, 10));
		

		pane.add(choose1);
		choose1.addActionListener(new
				ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{	
						Model_Example model1 = new Model_Example(0, choose1.getText());
				        PitsPanel panel = new PitsPanel(model1);
				        BoardPanel p = new BoardPanel(model1, panel);
				        model1.addChangeListener(p);
				        MancalaFrame mFrame = new MancalaFrame(p);
				        int pebbles = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter 3 or 4 Pebbles to Load Board: "));
				        if(pebbles != 4)
				        {
				        	model1.setRocks(3);
					        frame.dispose();
				        	
				       }
				       else {
				    	   model1.setRocks(4);
					        frame.dispose();
				        }
						
					}
				});
		pane.add(choose2);
		choose2.addActionListener(new
				ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						Model_Example model2 = new Model_Example(0, choose2.getText());
				        PitsPanel panel = new PitsPanel(model2);
				        BoardPanel p = new BoardPanel(model2, panel);
				        model2.addChangeListener(p);
				        MancalaFrame mFrame = new MancalaFrame(p);
				        int pebbles = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter 3 or 4 Pebbles to Load Board: "));
				        if(pebbles != 4)
				        {
				        	model2.setRocks(3);
					        frame.dispose();
				        	
				       }
				       else {
				    	   model2.setRocks(4);
					       frame.dispose();
				        }
						
					}
				});
		pane.add(exitButton);
		exitButton.addActionListener(new
				ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						//exit the panel
						System.exit(0);
					}
				});
		
		//frame settings
		frame.add(pane, BorderLayout.SOUTH);
		frame.add(message,BorderLayout.CENTER);
		//centers the frame
		frame.setVisible(true);
		frame.setSize(300,100);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
