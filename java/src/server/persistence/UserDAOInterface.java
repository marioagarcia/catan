package server.persistence;

import java.util.ArrayList;

public interface UserDAOInterface {

	/**
	 * Serializes a user and stores it in the database
	 * 
	 * @param user_blob An object representation of the user being saved/added
	 * @param username The username to be associated with the given user blob
	 * @return True if the save was successful, false otherwise
	 */
	public abstract boolean saveUser(String user_blob, String username);
	
	/**
	 * Retrieves a serialized list of all of the users in the database
	 * 
	 * @return An arraylist containing all of the serialized users in the database
	 */
	public abstract ArrayList<String> getAllUsers();
	
	/**
	 * Retrieves a user based on the given username
	 * 
	 * @param username The username of the user being retrieved
	 * @return A serialized representation of the user
	 */
	public abstract String getUser(String username);
	
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
