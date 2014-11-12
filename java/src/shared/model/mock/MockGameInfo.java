package shared.model.mock;

import java.util.ArrayList;
import java.util.List;

import shared.model.GameInfo;
import shared.model.player.PlayerInfo;

public class MockGameInfo extends GameInfo{
	
	public MockGameInfo(String title){
		this.setGameInfo(title, 737, new ArrayList<PlayerInfo>());
	}
}
