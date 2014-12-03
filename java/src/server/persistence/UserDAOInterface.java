package server.persistence;

import java.util.ArrayList;

public interface UserDAOInterface {

	/**
	 * Saves a user, i.e. adds a user to the database
	 * 
	 * @param data An object representation of the user being saved/added
	 * @return True if the save was successful, false otherwise
	 */
	public abstract boolean saveUser(UserDTO data);
	
	/**
	 * Retrieves a list of all of the users in the database
	 * 
	 * @return An arraylist containing all of the users in the database
	 */
	public abstract ArrayList<UserDTO> getAllUsers();
	
	/**
	 * Retrieves a user based on the given username
	 * 
	 * @param username The username of the user being retrieved
	 * @return UserDTO an object representation of the user
	 */
	public abstract UserDTO getUser(String username);
	
	/**
	 * Updates a user
	 * 
	 * @param data An object representation of the user being updated
	 * @return True if the update was successful, false otherwise
	 */
	public abstract boolean updateUser(UserDTO data);
	
	/**
	 * Deletes a user from the database
	 * 
	 * @param username The username of the user being deleted
	 * @return True if the deletion was successful, false otherwise
	 */
	public abstract boolean deleteUser(String username);
}
