package shared.model.mock;

import java.util.ArrayList;

import shared.definitions.CatanColor;
import shared.model.GameInfo;
import shared.model.manager.GameList;
import shared.model.player.PlayerInfo;

public class MockGameList extends GameList{
	
	public MockGameList(){
		this.setGameList(buildGameList());
	}
	
	public ArrayList<GameInfo> buildGameList(){
	
		//Build game 1 players
		PlayerInfo player1 = new PlayerInfo();
		PlayerInfo player2 = new PlayerInfo();
		PlayerInfo player3 = new PlayerInfo();
		PlayerInfo player4 = new PlayerInfo();
		
		player1.setPlayerInfo(CatanColor.BLUE, "Butthole", 17);
		player2.setPlayerInfo(CatanColor.BRONZE, "Butthead", 93);
		player3.setPlayerInfo(CatanColor.GREEN, "Buttface", 111);
		player4.setPlayerInfo(CatanColor.ORANGE, "Buttwipe", 26);
		
		//Add game 1 players to game1Players list
		ArrayList<PlayerInfo> game1Players = new ArrayList<PlayerInfo>();
		game1Players.add(player1); 
		game1Players.add(player2); 
		game1Players.add(player3); 
		game1Players.add(player4); 
		
		//Build game 1
		GameInfo game1 = new GameInfo();
		game1.setGameInfo("Butts", 55, game1Players);
		
		//Build game 2 players
		PlayerInfo player5 = new PlayerInfo();
		PlayerInfo player6 = new PlayerInfo();
		PlayerInfo player7 = new PlayerInfo();
		PlayerInfo player8 = new PlayerInfo();
		
		player5.setPlayerInfo(CatanColor.PUCE, "Buttwad", 77);
		player6.setPlayerInfo(CatanColor.PURPLE, "Buttlick", 56);
		player7.setPlayerInfo(CatanColor.RED, "Buttslurp", 42);
		player8.setPlayerInfo(CatanColor.WHITE, "Buttchode", 38);
		
		//Add game 2 players to the game2Players list
		ArrayList<PlayerInfo> game2Players = new ArrayList<PlayerInfo>();
		game2Players.add(player5); 
		game2Players.add(player6); 
		game2Players.add(player7); 
		game2Players.add(player8); 
		
		//Build game 2
		GameInfo game2 = new GameInfo();
		game2.setGameInfo("More Butts", 85, game2Players);
		
		//Build game 3 players
		PlayerInfo player9 = new PlayerInfo();
		PlayerInfo player10 = new PlayerInfo();
		
		player9.setPlayerInfo(CatanColor.YELLOW, "Chortle", 1000);
		player10.setPlayerInfo(CatanColor.PURPLE, "LaQuisha", 56);
		
		//Add game 3 players to the game3Players list
		ArrayList<PlayerInfo> game3Players = new ArrayList<PlayerInfo>();
		game3Players.add(player9);
		game3Players.add(player10);
		
		//Build game 3
		GameInfo game3 = new GameInfo();
		game3.setGameInfo("Great Names", 16000, game3Players);
		
		ArrayList<GameInfo> returnGameList = new ArrayList<GameInfo>();
		returnGameList.add(game1);
		returnGameList.add(game2);
		returnGameList.add(game3);
		
		return returnGameList;
	}
	
	public static void main(String[] args){
		System.out.println(new MockGameList().getGameList().toString());
	}
}
