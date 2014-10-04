package client.manager.interfaces;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import client.model.map.HexInterface;

public interface GMBoardMapInterface {
	
	/**
	 * Queries the location to see if the player can build a Road there
	 * @param location
	 * @param player_index
	 * @return true if the location is next to another road or a building owned by the player
	 */
	public boolean canBuildRoad(EdgeLocation location, int player_index);

	/**
	 * Queries the location to see if the player can build a Settlement there
	 * @param location
	 * @param player_index
	 * @return true if the location is empty and it is not next to any other buildings
	 */
	public boolean canBuildSettlement(VertexLocation location, int player_index);

	/**
	 * Queries the location to see if the player can build a City there
	 * @param location
	 * @param player_index
	 * @return true if the location has a Settlement belonging to the player in it
	 */
	public boolean canBuildCity(VertexLocation location, int player_index);
	
	/**
	 * Queries the location to see if the player has a building in the location given
	 * @param location
	 * @param player_index
	 * @return true if the player owns the building in the location
	 */
	public boolean canMaritimeTrade(HexInterface location, int player_index);
}