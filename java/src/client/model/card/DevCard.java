package client.model.card;

public class DevCard implements DevCardInterface{
	
	private DevCardType type;
	
	public DevCard(DevCardType type){
		this.type = type;
	}

	@Override
	public DevCardType getType() {
		return this.type;
	}

}
