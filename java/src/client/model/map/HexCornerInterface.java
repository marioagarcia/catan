package client.model.map;

import client.model.piece.BuildingInterface;
import client.model.player.PlayerInterface;

public interface HexCornerInterface {

	/**
	 * returns the building currently built on the corner
	 * @return BuildingInterface
	 */
	public abstract BuildingInterface getBuilding();
	
	/**
	 * tells us whether or not we can build a Settlement on this HexCorner
	 * (verifies if there is already something built there)
	 * @return
	 */
	public abstract boolean canBuildSettlement(PlayerInterface player);
	
	/**
	 * tells us whether or not the given player can build a City on this corner
	 * (the corner must have a Settlement which is owned by this player)
	 * @return boolean value for whether or not we can build there
	 */
	public abstract boolean canBuildCity(PlayerInterface player);
	
	public abstract void buildBuilding( BuildingInterface building );
	
	/**
	 * Gives the possible enum values for a hex corner
	 */
	public enum HexCornerType {
		HEXCORNERTOPLEFT,
		HEXCORNERTOPRIGHT,
		HEXCORNERTOP,
		HEXCORNERBOTTOMLEFT,
		HEXCORNERBOTTOMRIGHT,
		HEXCORNERBOTTOM
	}
}
