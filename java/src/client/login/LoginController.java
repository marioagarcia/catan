package client.login;

import client.base.*;
import client.communication.facade.ModelFacade;
import client.misc.*;

/**
 * Implementation for the login controller
 */
public class LoginController extends Controller implements ILoginController {

	private IMessageView messageView;
	private IAction loginAction;
	
	/**
	 * LoginController constructor
	 * 
	 * @param view Login view
	 * @param messageView Message view (used to display error messages that occur during the login process)
	 */
	public LoginController(ILoginView view, IMessageView messageView) {

		super(view);
		
		this.messageView = messageView;
	}
	
	public ILoginView getLoginView() {
		
		return (ILoginView)super.getView();
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	
	/**
	 * Sets the action to be executed when the user logs in
	 * 
	 * @param value The action to be executed when the user logs in
	 */
	public void setLoginAction(IAction value) {
		
		loginAction = value;
	}
	
	/**
	 * Returns the action to be executed when the user logs in
	 * 
	 * @return The action to be executed when the user logs in
	 */
	public IAction getLoginAction() {
		
		return loginAction;
	}

	@Override
	public void start() {
		((LoginView)getLoginView()).setRegisterButtonEnabled(false); //Disable register button until name and password fields are valid
		getLoginView().showModal();
	}

	@Override
	public void signIn() {
		ModelFacade facade = ModelFacade.getInstance(null);
		
		String username = this.getLoginView().getLoginUsername(); //Get the username from the view
		String password = this.getLoginView().getLoginPassword(); //Get the password from the view
		
		if(facade.loginPlayer(username, password)){
		// If log in succeeded
			getLoginView().closeModal();
			loginAction.execute();
		}else{
			this.messageView.setTitle("Login Error");
			this.messageView.setMessage("Invalid username or password.  Please try again.");
			this.messageView.showModal();
		}
	}

	@Override
	public void register() {
		ModelFacade facade = ModelFacade.getInstance(null);
		
		String username = this.getLoginView().getRegisterUsername(); //Get username from the view
		String password = this.getLoginView().getRegisterPassword(); //Get password from the view

		if(facade.registerPlayer(username, password)){
			// If register succeeded
			facade.loginPlayer(username, password); //Login the player
			getLoginView().closeModal();
			loginAction.execute();
		}else{
			this.messageView.setTitle("Register Error");
			this.messageView.setMessage("Unable to register.  Please try again.");
			this.messageView.showModal();
		}
	}

}

