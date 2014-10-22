package client.model.logging.history;

import java.util.ArrayList;
import java.util.Collection;

public class HistoryLog implements GameHistoryLogInterface {

	ArrayList<LogLineInterface> logLineList;
	
	public HistoryLog() {
		logLineList = new ArrayList<LogLineInterface>();
	}
	
	public HistoryLog(Collection<LogLineInterface> currentListOfCommands) {
		logLineList = (ArrayList<LogLineInterface>) currentListOfCommands;
	}
	
	@Override
	public int getSize() {
		return logLineList.size();
	}

	@Override
	public LogLineInterface getLogLine(int logIndex) {
		if(logIndex >= 0 && logIndex < logLineList.size())
			return logLineList.get(logIndex);
		else return null;
	}

	@Override
	public void addLogLine(LogLineInterface logLine) {
		logLineList.add(logLine);
	}

}
