package client.model.card;

import shared.definitions.ResourceType;

public class ResourceCard implements ResourceCardInterface {

	private ResourceType type;
	
	public ResourceCard(ResourceType type){
		this.type = type;
	}
	
	@Override
	public ResourceType getResourceCardType() {
		return this.type;
	}

}
