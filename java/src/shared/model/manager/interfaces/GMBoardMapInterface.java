package shared.model.manager.interfaces;

import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.turntracker.TurntrackerInterface.Status;

public interface GMBoardMapInterface {
	
	/**
	 * Queries the location to see if the player can build a Road there
	 * @param location the location where the road is wanted to be built
	 * @param player_index the index of the local player
	 * @return true if the location is next to another road or a building owned by the player
	 */
	public boolean canBuildRoad(EdgeLocation location, int player_index, Status status);
	
	/**
	 * builds a Road at the given location
	 * @param location EdgeLocation location where the Road should be built
	 * @param player_index int the player who will own the Road
	 */
	public void buildRoad(EdgeLocation location, int player_index, Status status);

	/**
	 * Queries the location to see if the player can build a Settlement there
	 * @param location the location where the settlement is wanted to be built
	 * @param player_index the index of the local player
	 * @param setupPhase whether or not the phase is set up
	 * @return true if the location is empty and it is not next to any other buildings
	 */
	public boolean canBuildSettlement(VertexLocation location, int player_index, boolean setupPhase);       
	
	/**
	 * builds the Settlement at the given location
	 * @param location VertexLocation the place where the Settlement needs to be built
	 * @param player_index int the index of the player
	 * @param setupPhase boolean whether or not the Settlement will be built for free during the setup phase
	 */
	public void buildSettlement(VertexLocation location, int player_index, boolean setupPhase);

	/**
	 * Queries the location to see if the player can build a City there
	 * @param location the location where the city should be built
	 * @param player_index the index of the local player
	 * @return true if the location has a Settlement belonging to the player in it
	 */
	public boolean canBuildCity(VertexLocation location, int player_index);
	
	/**
	 * build a City at the given location
	 * @param location VertexLocation the location where the City is to be built
	 * @param player_index int the index of the player who is building the City
	 */
	public void buildCity(VertexLocation location, int player_index);
	/**
	 * Queries the location to see if the player can trade there
	 * @param location the location where the player would like to trade
	 * @param player_index the index of the local player
	 * @return true if the player can trade at the given location
	 */
	public boolean canMaritimeTrade(EdgeLocation location, int player_index);

	/**
	 * Queries the locations to determine whether or not the roads can be build there given the following conditions: 
	 * The first road location is connected to one of the player's roads
	 * The second road location is connected to one of the player's roads or the previous location.
	 * Neither location is on water
	 * @param location1 is the first road location
	 * @param location2 is the second road location
	 * @param player_index the local player's index 
	 * @return true if all conditions were met
	 */
	public boolean canPlayRoadBuilding(EdgeLocation location1, EdgeLocation location2, int player_index);
	
	/**
	 * play a road building card
	 * @param location1 EdgeLocation where the first road should be built
	 * @param location2 EdgeLocation where the second road should be built
	 * @param player_index int the index of the player who is playing the road building card
	 */
	public void playRoadBuilding(EdgeLocation location1, EdgeLocation location2, int player_index);
	/**
	 * Compares the two locations and determines if the player can move the robber from  oldLocation to newLocation
	 * @param oldLocation the hex where the robber was
	 * @param newLocation the hex where the player wants to move the robber to 
	 * @param player_index the local player's index
	 * @return true if the locations are different
	 */
	public boolean canPlaySoldier(HexLocation oldLocation, HexLocation newLocation, int player_index);
	
	/**
	 * play a soldier card
	 * @param oldLocation HexLocation the location where the robber is currently located
	 * @param newLocation HexLocation the new robber location
	 * @param player_index int the index of the player playing the card
	 */
	public void playSoldier(HexLocation oldLocation, HexLocation newLocation, int player_index);
}