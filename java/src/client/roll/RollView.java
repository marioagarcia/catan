package client.roll;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.*;

import client.base.*;

/**
 * Implementation for the roll view, which allows the user to roll the dice
 */
@SuppressWarnings("serial")
public class RollView extends OverlayView implements IRollView {

	private final int LABEL_TEXT_SIZE = 20;
	private final int BUTTON_TEXT_SIZE = 28;
	private final int BORDER_WIDTH = 1;

	private JLabel label;
    private JLabel imageLabel;
	private JButton rollButton;
	private JPanel buttonPanel;
	
	private MyTimer rollTimer;

	public RollView() {
		
		this.setOpaque(true);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black, BORDER_WIDTH));
		
		label = new JLabel("Roll View");
		Font labelFont = label.getFont();
		labelFont = labelFont.deriveFont(labelFont.getStyle(), LABEL_TEXT_SIZE);
		label.setFont(labelFont);
		this.add(label, BorderLayout.NORTH);
		
        try {
            BufferedImage diceImg = ImageIO.read(new File("images/misc/dice.jpg"));
            Image smallDiceImg = diceImg.getScaledInstance(300, 224, Image.SCALE_SMOOTH);
            imageLabel = new JLabel(new ImageIcon(smallDiceImg));
            this.add(imageLabel, BorderLayout.CENTER);
        } catch (IOException ex) {
            // Handle Exception Here
        	System.out.println("EXCEPTION");
        }

		rollButton = new JButton("Roll!");
		rollButton.addActionListener(actionListener);
		addKeyBindings(rollButton);
		//rollButton.addKeyListener(keyListener);
		Font buttonFont = rollButton.getFont();
		buttonFont = buttonFont.deriveFont(buttonFont.getStyle(), BUTTON_TEXT_SIZE);
		rollButton.setFont(buttonFont);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));		
		buttonPanel.add(rollButton);		
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void addKeyBindings(JComponent jc){
		jc.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),"Enter pressed");
		jc.getActionMap().put("Enter pressed",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				rollTimer.stopTimer();
				closeModal();
				getController().rollDice();
			}
			
		});
	
	}
	
/*	KeyListener keyListener = new KeyListener(){

		@Override
		public void keyTyped(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				rollTimer.stopTimer();
				closeModal();
				getController().rollDice();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
		
	};*/

	private ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == rollButton) {
				rollTimer.stopTimer();
				closeModal();
				getController().rollDice();
			}
		}	
	};
	
	public void startRollTimer(){
		rollTimer = new MyTimer();
	}
	
	private class MyTimer implements ActionListener{
		Timer t;
		private int seconds;
		private final int ONE_SECOND = 1000;
		
		public MyTimer(){
			seconds = 5;
			t = new Timer(ONE_SECOND, this);
			t.start();
		}
		
		private void writeMessage(){
			setMessage("Automatically rolling in " + seconds + " seconds...");
		}
		
		private void stopTimer(){
			t.stop();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(seconds == 0){
				t.stop();
				closeModal();
				getController().rollDice();
			}else{
				seconds--;
				writeMessage();
			}
		}
	}
	
	@Override
	public IRollController getController() {
		
		return (IRollController)super.getController();
	}

	@Override
	public void setMessage(String message) {
		label.setText(message);
	}
}


