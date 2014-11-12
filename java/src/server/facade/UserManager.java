package server.facade;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
	
	private Map<String, User> userList = null;
	private static int currentID = 1;
	
	public UserManager(){
		
		userList = new HashMap<String, User>(); 
	}
	
	public boolean canRegister(String name){
		
		return !userList.containsKey(name);
	}
	
	public boolean canLogin(String name, String password){
		return (userList.containsKey(name) && userList.get(name).getPassword().equals(password));
	}
	
	public void addUser(String username, String password){
		
		if (canRegister(username)){
			userList.put(username, new User(username, password, currentID));
			currentID++;
		}
	}
	
	

}
