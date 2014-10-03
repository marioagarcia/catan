package client.model;

public class Message {

		String message;
		String source;
	
		public Message(String msg, String src){
			message = msg;
			source = src;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}
}
