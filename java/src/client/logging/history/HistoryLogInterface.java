package client.logging.history;

public interface HistoryLogInterface 
{
	/**
	 * gives you the number of log entries
	 * @return int representing the number of entries
	 */
	public int getSize(); 
	
	/**
	 * returns the entry at a particular index if the index is valid
	 * raises LogEntryDoesNotExistException if index does not exist
	 * @param messageIndex the index which you want to access
	 * @return object implementing the MoveLogInterface
	 * throws LogEntryDoesNotExistException
	 */
	public MoveLogInterface getEntry(int messageIndex);
	
	/**
	 * adds the given entry to the log
	 * @param newEntry MoveLogInterface instance representing the new entry
	 */
	public void addEntry(MoveLogInterface newEntry);

}
