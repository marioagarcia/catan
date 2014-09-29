package client.model.map;

public interface TerrainHex extends HexInterface {

	/**
	 * Gives the possible values for a terrain hex
	 */
	public enum TerrainHexType {
		BRICKTERRAINHEX,
		ORETERRAINHEX,
		SHEEPTERRAINHEX,
		WHEATTERRAINHEX,
		WOODTERRAINHEX,
		
		//Theirs
		DESERT,
		WATER
	}
}
