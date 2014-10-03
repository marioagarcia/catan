package client.model.card;

public class ResourceCard implements ResourceCardInterface {

	private ResourceCardType type;
	
	public ResourceCard(ResourceCardType type){
		this.type = type;
	}
	
	@Override
	public ResourceCardType getResourceCardType() {
		return this.type;
	}

}
