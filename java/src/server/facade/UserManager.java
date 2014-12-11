package server.facade;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class UserManager {
	
	private Map<String, User> userList = null;
	private static int currentID = 0;
	
	public UserManager(){
		
		userList = new HashMap<String, User>(); 
	}
	
	public boolean canRegister(String name){
		
		return !userList.containsKey(name);
	}
	
	public boolean canLogin(String name, String password){
		return (userList.containsKey(name) && userList.get(name).getPassword().equals(password));
	}
	
	public boolean verifyUser(String name, String password, int id){
		
		if (userList.containsKey(name)){
			User compareUser = userList.get(name);
			return (compareUser.getPassword().equals(password) && compareUser.getID() == id);
		}
		else{
			return false;
		}
	}
	
	public boolean register(String username, String password){
		
		if (canRegister(username)){
			userList.put(username, new User(username, password, currentID));
			currentID++;
			return true;
		}
		else{
			return false;
		}
	}
	
	public void insertUser(User new_user){
		
		userList.put(new_user.getUsername(), new_user);
	}
	
	public int getPlayerId(String name){
		
		if (userList.containsKey(name)){
			return userList.get(name).getID();
		}
		else{
			return -1;
		}
	}
	
	public String getPlayerName(int id){
		
		for (Map.Entry<String, User> user : userList.entrySet()){
			
			if (user.getValue().getID() == id){
				return user.getValue().getUsername();
			}
		}
		
		return null;
	}
	
	public String serializeUser(String username){
		
		if (userList.containsKey(username)){
			Gson gson = new Gson();
			
			return gson.toJson(userList.get(username));
		}
		else{
			return null;
		}
	}
	
	

}
