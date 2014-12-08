package shared.serialization.parameters;

import shared.model.card.ResourceList;

public class GameModelParameters {

	private DevCardListParameters deck;
	private MapParameters map;
	private PlayerParameters[] players;
	private LogParameters log;
	private LogParameters chat;
	private ResourceList bank;
	private TurnTrackerParameters turnTracker;
	private TradeOfferParameters tradeOffer;
	private int winner;
	private int version;
	private int id;
	private String name;
	
	public GameModelParameters(DevCardListParameters deck, MapParameters map, PlayerParameters[] players, 
							   LogParameters log, LogParameters chat, ResourceList bank, 
							   TurnTrackerParameters turnTracker, TradeOfferParameters tradeOffer,
							   int winner, int version, int id, String name){
		this.deck = deck;
		this.map = map;
		this.players = players;
		this.log = log;
		this.chat = chat;
		this.bank = bank;
		this.turnTracker = turnTracker;
		this.tradeOffer = tradeOffer;
		this.winner = winner;
		this.version = version;
		this.id = id;
		this.name = name;
	}

	public DevCardListParameters getDeck() {
		return deck;
	}

	public void setDeck(DevCardListParameters deck) {
		this.deck = deck;
	}

	public MapParameters getMap() {
		return map;
	}

	public void setMap(MapParameters map) {
		this.map = map;
	}

	public PlayerParameters[] getPlayers() {
		return players;
	}

	public void setPlayers(PlayerParameters[] players) {
		this.players = players;
	}

	public LogParameters getLog() {
		return log;
	}

	public void setLog(LogParameters log) {
		this.log = log;
	}

	public LogParameters getChat() {
		return chat;
	}

	public void setChat(LogParameters chat) {
		this.chat = chat;
	}

	public ResourceList getBank() {
		return bank;
	}

	public void setBank(ResourceList bank) {
		this.bank = bank;
	}

	public TurnTrackerParameters getTurnTracker() {
		return turnTracker;
	}

	public void setTurnTracker(TurnTrackerParameters turnTracker) {
		this.turnTracker = turnTracker;
	}

	public TradeOfferParameters getTradeOffer() {
		return tradeOffer;
	}

	public void setTradeOffer(TradeOfferParameters tradeOffer) {
		this.tradeOffer = tradeOffer;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
