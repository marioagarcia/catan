package client.map;

import shared.model.player.RobPlayerInfo;
import client.base.*;

/**
 * Interface for the rob view, which lets the user select a player to rob
 */
public interface IRobView extends IOverlayView
{
	
	void setPlayers(RobPlayerInfo[] candidateVictims);
}

