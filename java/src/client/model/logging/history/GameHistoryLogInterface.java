package client.model.logging.history;

public interface GameHistoryLogInterface 
{
	/**
	 * gives you the number of log entries
	 * @return int representing the number of entries
	 */
	public int getSize(); 
	
	/**
	 * returns the entry at a particular index if the index is valid
	 * raises LogEntryDoesNotExistException if index does not exist
	 * @param logIndex the index which you want to access
	 * @return object implementing the MoveLogInterface
	 * throws LogEntryDoesNotExistException
	 */
	public LogLineInterface getLogLine(int logIndex);
	
	/**
	 * adds the given entry to the log
	 * @param logLine MoveLogInterface instance representing the new entry
	 */
	public void addLogLine(LogLineInterface logLine);

}
