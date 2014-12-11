package server;

import server.persistence.CommandDAOInterface;
import server.persistence.GameDAOInterface;
import server.persistence.PersistenceInterface;
import server.persistence.UserDAOInterface;

public class TestPlugin implements PersistenceInterface{

	@Override
	public void startTransaction() {
		System.out.println("Started Transaction");
	}

	@Override
	public void endTransaction() {
		System.out.println("Ended Transaction");
	}

	@Override
	public UserDAOInterface createUserDAO() {
		System.out.println("Created User DAO");
		return null;
	}

	@Override
	public GameDAOInterface createGameDAO() {
		System.out.println("Created Game DAO");
		return null;
	}

	@Override
	public CommandDAOInterface createCommandDAO() {
		System.out.println("Created Command DAO");
		return null;
	}

	@Override
	public void resetAllPersistence() {
		System.out.println("All persistence was reset");
	}
}
