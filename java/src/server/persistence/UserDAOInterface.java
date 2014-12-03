package server.persistence;

import java.util.ArrayList;

public interface UserDAOInterface {

	public abstract boolean saveUser(UserDTO data);
	public abstract ArrayList<UserDTO> getAllUsers();
	public abstract UserDTO getUser(int id);
	public abstract boolean updateUser(UserDTO data);
	public abstract boolean deleteUser(int id);
}
