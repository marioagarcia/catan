package client.main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


@SuppressWarnings("serial")
public class ConfigView extends JDialog{

	private JTextField portTextField;
	private JTextField hostTextField;
	private JButton okButton;
	private JButton exitButton;
	
	private String host;
	private String port;
	
	public ConfigView(JFrame frame){
		super(frame, true);
		
		this.setTitle("Configuration");
		this.setResizable(false);
		
		// Host panel
		
		JLabel hostLabel = new JLabel("Host");
		Font defaultFont = hostLabel.getFont();
		Font font = new Font(defaultFont.getName(), defaultFont.getStyle(), 14);
		hostLabel.setFont(font);
		
		hostTextField = new JTextField();
		hostTextField.addKeyListener(key_listener);
		hostTextField.setPreferredSize(new Dimension(100, 20));
		
		JPanel hostPanel = new JPanel();
		hostPanel.setLayout(new BoxLayout(hostPanel, BoxLayout.Y_AXIS));
		
		hostPanel.add(hostLabel);
		hostPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		hostPanel.add(hostTextField);
		
		// Port panel
		
		JLabel portLabel = new JLabel("Port");
		font = new Font(defaultFont.getName(), defaultFont.getStyle(), 14);
		portLabel.setFont(font);
		
		portTextField = new JTextField();
		portTextField.addKeyListener(key_listener);
		portTextField.setPreferredSize(new Dimension(100, 20));
		
		JPanel portPanel = new JPanel();
		portPanel.setLayout(new BoxLayout(portPanel, BoxLayout.Y_AXIS));
		
		portPanel.add(portLabel);
		portPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		portPanel.add(portTextField);
		
		
		// Button panel
		
		okButton = new JButton("OK");
		okButton.addActionListener(actionListener);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(actionListener);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		buttonPanel.add(Box.createRigidArea(new Dimension(150, 0)));
		buttonPanel.add(okButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		buttonPanel.add(exitButton);
		
		// Root panel
		
		JPanel rootPanel = new JPanel();
		rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
		
		rootPanel.add(hostPanel);
		rootPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		rootPanel.add(portPanel);
		rootPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		rootPanel.add(buttonPanel);
		rootPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		this.add(rootPanel);
		
		this.pack();
	}
	
	public void reset(){
		hostTextField.setText("localhost");
		portTextField.setText("8081");
	}
	
	private void submit(){
		port = portTextField.getText();
		host = hostTextField.getText();
		
		if (port.equals("")){
			port = "8081";
		}
		
		if (host.equals("")){
			host = "localhost";
		}
		
		this.setVisible(false);
	}
	
	public String getHost(){
		return this.host;
	}
	
	public String getPort(){
		return this.port;
	}
	
	private KeyAdapter key_listener = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e){
			if (e.getKeyCode() == KeyEvent.VK_ENTER){
				ConfigView.this.submit();
			}
		}
	
	};
	
	private ActionListener actionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e){
			if (e.getSource() == okButton) {
				ConfigView.this.submit();
			}
			
			if (e.getSource() == exitButton) {
				System.exit(0);
			}
		}
	};
}

