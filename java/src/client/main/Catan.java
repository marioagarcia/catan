package client.main;

import javax.swing.*;

import client.catan.*;
import client.communication.facade.ModelFacade;
import client.communication.server.ServerProxy;
import client.login.*;
import client.join.*;
import client.misc.*;
import client.base.*;

/**
 * Main entry point for the Catan program
 */
@SuppressWarnings("serial")
public class Catan extends JFrame {

	private CatanPanel catanPanel;

	public Catan() {

		client.base.OverlayView.setWindow(this);

		this.setTitle("Settlers of Catan (Beta)");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		catanPanel = new CatanPanel();
		this.setContentPane(catanPanel);

		display();
	}

	private void display() {
		pack();
		setVisible(true);
	}

	//
	// Main
	//

	public static void main(final String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				ConfigView config_window = new ConfigView(null);
				
				boolean connected = false;
				ServerProxy proxy = null;
				
				while (!connected){
					config_window.reset();
					config_window.setLocationRelativeTo(null);
					config_window.setVisible(true);
					
					String port = config_window.getPort();
					String host = config_window.getHost();
					
					proxy = new ServerProxy(port, host);
					connected = proxy.isConnected();
					
					if (!connected){
						config_window.setTitle("Could not connect to server");
						config_window.revalidate();
					}
					
				}
				
				ModelFacade.getInstance(proxy);
				
				new Catan();

				PlayerWaitingView playerWaitingView = new PlayerWaitingView();
				final PlayerWaitingController playerWaitingController = new PlayerWaitingController(
						playerWaitingView);
				playerWaitingView.setController(playerWaitingController);

				JoinGameView joinView = new JoinGameView();
				NewGameView newGameView = new NewGameView();
				SelectColorView selectColorView = new SelectColorView();
				MessageView joinMessageView = new MessageView();
				final JoinGameController joinController = new JoinGameController(
						joinView, newGameView, selectColorView, joinMessageView);
				joinController.setJoinAction(new IAction() {
					@Override
					public void execute() {
						playerWaitingController.start();
					}
				});
				joinView.setController(joinController);
				newGameView.setController(joinController);
				selectColorView.setController(joinController);
				joinMessageView.setController(joinController);

				LoginView loginView = new LoginView();
				MessageView loginMessageView = new MessageView();
				LoginController loginController = new LoginController(
						loginView, loginMessageView);
				loginController.setLoginAction(new IAction() {
					@Override
					public void execute() {
						joinController.start();
					}
				});
				loginView.setController(loginController);
				loginView.setController(loginController);

				loginController.start();
			}
		});
	}
}
