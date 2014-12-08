package server.persistence;

import server.command.CatanCommand;

public class CommandDTO {

	private CatanCommand command = null;
	private String type;
	
	public CommandDTO(CatanCommand command, String type){
		this.command = command;
		this.type = type;
	}	
	
	public CatanCommand getCommand(){
		return this.command;
	}

	
	public String getType(){
		return this.type;
	}
}
