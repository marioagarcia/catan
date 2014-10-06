package client.manager;

import java.util.ArrayList;

import shared.locations.HexLocation;
//import client.logging.chat.Message;
import client.model.card.DevCardBank;
import client.model.card.ResourceList;
import client.model.map.Hex;
import client.model.map.Port;
import client.model.piece.Road;
import client.model.player.Player;

public class GameData {

	//Deck - DevCardBank
	DevCardBank deck; //Done
	
	//Bank - Resource List
	ResourceList bankResourceList;
	
	//Chat Message List
	//ArrayList<Message> chatMessageList;
	
	//Log Message List
	//ArrayList<Message> logMessageList;
	
	//Map
	ArrayList<Hex> hexList; //Done
	ArrayList<Port> portList;
	ArrayList<Road> roadList;
	//ArrayList<VertexObject> settlementList;
	//ArrayList<VertexObject> cityList;
	int radius;
	HexLocation robber;
	
	//Players
	ArrayList<Player> playerList;
	
	//Don't know where these fit in
	//EdgeValue
	int index;
	//EdgeLocation
	int x;
	int y;
	
	//TradeOffer (if there is one, Optional)
	int sender;
	int receiver;
	ResourceList offer;
	
	//TurnTracker
	int currentTurn;
	String status;
	int longestRoad; // Optional
	int largestArmy; // Optional
	
	//Version
	int version;
	
	//Winner (-1 if there is no winner yet)
	int winner;
	
	public GameData(){
		
	}

	public DevCardBank getDeck(){
		return deck;
	}
	
	public void setDeck(DevCardBank deck){
		this.deck = deck;
	}
	
	public ResourceList getBankResourceList() {
		return bankResourceList;
	}

	public void setBankResourceList(ResourceList bankResourceList) {
		this.bankResourceList = bankResourceList;
	}

	public ArrayList<Message> getChatMessageList() {
		return chatMessageList;
	}

	public void setChatMessageList(ArrayList<Message> chatMessageList) {
		this.chatMessageList = chatMessageList;
	}

	public ArrayList<Message> getLogMessageList() {
		return logMessageList;
	}

	public void setLogMessageList(ArrayList<Message> logMessageList) {
		this.logMessageList = logMessageList;
	}

	public ArrayList<Hex> getHexList() {
		return hexList;
	}

	public void setHexList(ArrayList<Hex> hexList) {
		this.hexList = hexList;
	}

	public ArrayList<Port> getPortList() {
		return portList;
	}

	public void setPortList(ArrayList<Port> portList) {
		this.portList = portList;
	}

	public ArrayList<Road> getRoadList() {
		return roadList;
	}

	public void setRoadList(ArrayList<Road> roadList) {
		this.roadList = roadList;
	}

/*	public ArrayList<VertexObject> getSettlementList() {
		return settlementList;
	}

	public void setSettlementList(ArrayList<VertexObject> settlementList) {
		this.settlementList = settlementList;
	}

	public ArrayList<VertexObject> getCityList() {
		return cityList;
	}

	public void setCityList(ArrayList<VertexObject> cityList) {
		this.cityList = cityList;
	}*/

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public HexLocation getRobber() {
		return robber;
	}

	public void setRobber(HexLocation robber) {
		this.robber = robber;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public ResourceList getOffer() {
		return offer;
	}

	public void setOffer(ResourceList offer) {
		this.offer = offer;
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLongestRoad() {
		return longestRoad;
	}

	public void setLongestRoad(int longestRoad) {
		this.longestRoad = longestRoad;
	}

	public int getLargestArmy() {
		return largestArmy;
	}

	public void setLargestArmy(int largestArmy) {
		this.largestArmy = largestArmy;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}
	
	
	
}
