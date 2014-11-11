package shared.serialization.parameters;

public class LogParameters {
	
	private MessageParameters[] lines;
	
	public LogParameters(MessageParameters[] lines){
		this.lines = lines;
	}

	public MessageParameters[] getLines() {
		return lines;
	}

	public void setLines(MessageParameters[] lines) {
		this.lines = lines;
	}
}
