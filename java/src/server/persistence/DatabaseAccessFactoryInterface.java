package server.persistence;

public interface DatabaseAccessFactoryInterface {

	public abstract CommandDAOInterface createCommandDAO();
	public abstract UserDAOInterface createUserDAO();
	public abstract GameDAOInterface createGameDAO();
}
