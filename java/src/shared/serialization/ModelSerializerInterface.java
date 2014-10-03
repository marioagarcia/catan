package shared.serialization;

/**
 * This class handles all serialization of data going to the server and 
 * deserialization of data coming from the server.
 */
public interface ModelSerializerInterface 
{
	
	/**
	 * Serializes a player's cards and ID to send to the server
	 * 
	 * @param inventory_instance The cards that are being serialized
	 * @param playerId The ID of the player whose inventory contains the cards being serialized
	 * @return String The player's cards and ID after being serialized
	 */
	public String serializeCards(client.model.card.CardInventoryInterface inventory_instance, int playerId);
	
	/**
	 * Deserializes a road from a string from the server
	 *  
	 * @param data The serialized version of the road 
	 * @return Road The road that was just deserialized
	 */
	public client.model.piece.RoadInterface deserializeRoad(String data);
	
	/**
	 * Deserializes a player from a string from the server
	 * 
	 * @param data The serialized version of the player
	 * @return Player The player that was just deserialized
	 */
	public client.model.player.PlayerInterface deserializePlayer(String data);
	
	/**
	 * Deserializes a CatanModel's current state/data and populates/updates a CatanModel with
	 * the state/data that has been deserialized
	 * 
	 * @param data The string from the server that needs to be deserialized
	 * @return CatanModel The updated CatanModel that was just deserialized
	 */
	public client.manager.GameData deserializeGameModel(String data);
}