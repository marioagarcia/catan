package shared.model.mock;

public class MockAIList {

	private String[] listAI;
	
	public MockAIList(){
		listAI = populateAIList();
	}
	
	private String[] populateAIList(){
		String[] returnArray = new String[4];
		returnArray[0] = "Horrible";
		returnArray[1] = "Awful";
		returnArray[2] = "Brutal";
		returnArray[3] = "The Worst";
		
		return returnArray;
	}

	public String[] getListAI() {
		return listAI;
	}

	public void setListAI(String[] listAI) {
		this.listAI = listAI;
	}
}
