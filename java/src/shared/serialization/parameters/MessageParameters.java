package shared.serialization.parameters;

public class MessageParameters {

	private String source;
	private String message;
	
	public MessageParameters(String source, String message){
		this.source = source;
		this.message = message;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
