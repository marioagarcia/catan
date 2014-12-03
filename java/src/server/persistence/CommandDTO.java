package server.persistence;

import shared.serialization.parameters.MasterParameterInterface;

public class CommandDTO {

	private MasterParameterInterface parameters;
	private int game_id;
	private String parameter_type;
	
	public CommandDTO(MasterParameterInterface p, String type, int id){
		this.parameters = p;
		this.game_id = id;
		this.parameter_type = type;
	}	
	
	public MasterParameterInterface getParameter(){
		return this.parameters;
	}
	
	public int getGameID(){
		return this.game_id;
	}
	
	public String getType(){
		return this.parameter_type;
	}
}
