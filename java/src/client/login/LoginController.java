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
		((LoginView)getLoginView()).setRegisterButtonEnabled(false);
		getLoginView().showModal();
	}

	@Override
	public void signIn() {
		
		// TODO: log in user
		ModelFacade facade = ModelFacade.getInstance(null);
		
		String username = this.getLoginView().getLoginUsername();
		String password = this.getLoginView().getLoginPassword();
		
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
		
		// TODO: register new user (which, if successful, also logs them in)
		ModelFacade facade = ModelFacade.getInstance(null);
		
		String username = this.getLoginView().getRegisterUsername();
		String password = this.getLoginView().getRegisterPassword();

		if(facade.registerPlayer(username, password)){
			facade.loginPlayer(username, password);
		
		// If register succeeded
			getLoginView().closeModal();
			loginAction.execute();
		}else{
			this.messageView.setTitle("Register Error");
			this.messageView.setMessage("Unable to register.  Please try again.");
			this.messageView.showModal();
		}
	}

}

