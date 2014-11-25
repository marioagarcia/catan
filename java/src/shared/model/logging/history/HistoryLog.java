package shared.model.logging.history;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class HistoryLog implements GameHistoryLogInterface, Serializable {

	private static final long serialVersionUID = -911563547081675661L;
	
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
