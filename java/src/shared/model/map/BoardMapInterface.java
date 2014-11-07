package shared.model.map;

import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.turntracker.TurntrackerInterface.Status;

/**
 * This class models the Map composed of the different hexes
 *
 */
public interface BoardMapInterface {

	/**
	 * returns the hex at the given location
	 * @param location the location to be associated with the hex requested
	 * @return HexInterface the hex associated with the given location
	 * @throws HexNotFoundException if the hex doesn't exist
	 */
	public HexInterface getHex(HexLocation location) throws HexNotFoundException;
	
	/**
	 * determines whether the given Player can currently build a road at the given location
	 * @param location the location where the player would like to build the road
	 * @param playerIndex the index of the player who wishes to build a road
	 * @param status game status (some statuses have special privileges)
	 * @return boolean whether or not you can build the road
	 */
	public boolean canBuildRoad(EdgeLocation location, int playerIndex, Status status);
	
	/**
	 * determines whether the given Player can currently build a settlement on the given location
	 * @param location the location where the player would like to build the road
	 * @param playerIndex the player who would like to build the Settlement
	 * @param setupPhase whether or not the game is in a setup phase
	 * @return boolean whether or not you can build the Settlement
	 */
	public boolean canBuildSettlement(VertexLocation location, int playerIndex, boolean setupPhase);
	
	/**
	 * determines whether the given Player can currently build a City at the given location
	 * @param location the VertexLocation where the Player would like to build the City
	 * @param playerIndex the index of the Player who would like to build a City
	 * @return boolean whether or not the Player can build a City at the given location
	 */
	public boolean canBuildCity(VertexLocation location, int playerIndex);
	
	/**
	 * determines whether the given Player can trade at the given location
	 * @param location the location of the Harbor where the person would like to trade
	 * @param playerIndex the index of the Player who would like to trade
	 * @return boolean whether or not the given Player can make a Maritime trade at the given location
	 */
	public boolean canMaritimeTrade(EdgeLocation location, int playerIndex);
	
	/**
	 * determines whether the given Player can play a road building card with the given locations
	 * @param location1 the EdgeLocation of the first Road to build
	 * @param location2 the EdgeLocation of the second Road to build
	 * @param playerIndex the index of the Player who would like to play a road building card
	 * @return boolean whether or not the given Player can play a road building card at the given location
	 */
	public boolean canPlayRoadBuilding(EdgeLocation location1, EdgeLocation location2, int playerIndex);
	
	/**
	 * determines whether the given Player can play a soldier card with the given locations and target player
	 * @param oldLocation the HexLocation where the Robber is currently located
	 * @param newLocation the HexLocation where the Robber will be moved to
	 * @param targetPlayerIndex the Player who is going to be robbed
	 * @return boolea whether or not the given Player can play a soldier card with the given parameters
	 */
	public boolean canPlaySoldier(HexLocation oldLocation, HexLocation newLocation, int targetPlayerIndex);
	
	
	
}
