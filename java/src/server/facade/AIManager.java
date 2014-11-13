package server.facade;

public class AIManager {
	
	private String[] aiList;
	
	public AIManager(){
		fillAIList();
	}
	
	private void fillAIList(){
		aiList = new String[4];
		aiList[0] = "Won't work 1";
		aiList[1] = "Won't work 2";
		aiList[2] = "Won't work 3";
		aiList[3] = "Won't work 4";
	}

	public String[] getAiList() {
		return aiList;
	}

	public void setAiList(String[] aiList) {
		this.aiList = aiList;
	}
}
