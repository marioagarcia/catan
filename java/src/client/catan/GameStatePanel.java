package client.catan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import client.base.IAction;


@SuppressWarnings("serial")
public class GameStatePanel extends JPanel
{
	private JButton button;
	
	public GameStatePanel()
	{
		this.setLayout(new FlowLayout());
		this.setBackground(Color.white);
		this.setOpaque(true);
		
		button = new JButton();
		
		Font font = button.getFont();
		Font newFont = font.deriveFont(font.getStyle(), 20);
		button.setFont(newFont);
		button.setPreferredSize(new Dimension(400, 50));
		addKeyBindings(button);
		
		this.add(button);
		
		updateGameState("Waiting for other Players", false);
	}
	
	
	
	public void updateGameState(String stateMessage, boolean enable)
	{
		button.setText(stateMessage);
		button.setEnabled(enable);
	}
	
	public void setButtonAction(final IAction action)
	{
		ActionListener[] listeners = button.getActionListeners();
		for(ActionListener listener : listeners) {
			button.removeActionListener(listener);
		}
		
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				action.execute();
			}
		};
		button.addActionListener(actionListener);
	}
	
	public void addKeyBindings(JComponent jc){
		jc.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),"Enter pressed");
		jc.getActionMap().put("Enter pressed",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(button.isEnabled()){
					button.doClick();
				}
			}
			
		});
	
	}
}

