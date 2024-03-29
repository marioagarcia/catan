package client.communication.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

public class ServerPoller implements ServerPollerInterface
{
	private boolean isRunning;
	private boolean gameListRunning;
	
	private String latestModel;
	private String latestGameList;
	
	boolean is_daemon = true;
	
	private Timer pollTimer = null;
	private TimerTask pollMethod = null;
	
	private Timer listTimer = null;
	private TimerTask listMethod = null;
	private ServerProxyInterface proxyObject = null;
	
	private transient Collection<ModelStateObserver> modelObservers;
	private transient Collection<GameListObserver> gameListObservers;
	
	public ServerPoller(){
		isRunning = false;
		gameListRunning = false;
		
		modelObservers = new ArrayList<ModelStateObserver>();
		gameListObservers = new ArrayList<GameListObserver>();
	}
	
	public void startPoller(long interval){
		if (!isRunning){
			
			pollTimer = new Timer(is_daemon);
			pollMethod = new CatanPoller();
			pollTimer.schedule(pollMethod, 3000, 3000);
			isRunning = true;
		}
	}
	
	public void startListPoller(long interval){
		if (!gameListRunning){
			
			listTimer = new Timer(is_daemon);
			listMethod = new GameListPoller();
			listTimer.schedule(listMethod, 3000, 3000);
			gameListRunning = true;
		}
	}
	
	public void stopPoller(){
		if (isRunning){
			pollTimer.cancel();
			pollMethod.cancel();
			isRunning = false;
		}
	}
	
	public void stopListPoller(){
		if (gameListRunning){
			listTimer.cancel();
			listMethod.cancel();
			gameListRunning = false;
		}
	}
	
	public void registerModelObserver(ModelStateObserver new_observer){
		modelObservers.add(new_observer);
	}
	
	public void registerListObserver(GameListObserver new_observer){
		gameListObservers.add(new_observer);
	}
	
	@Override
	public String getModelState(){
		return latestModel;
	}

	@Override
	public void setProxy(ServerProxyInterface proxy){
		proxyObject = proxy;
	}
	
	private class CatanPoller extends TimerTask{
		@Override
		public void run(){
			String model = proxyObject.getGameModel(false);
			
			if (!model.equals("\"true\"") && !model.equals("Success") && !model.equals(latestModel)){
				latestModel = model;
				
				for (ModelStateObserver o : modelObservers){
					o.modelChanged(model);
				}
			}
		}
	}
	
	private class GameListPoller extends TimerTask{
		@Override
		public void run(){
			String game_list = proxyObject.listGames();
			
			if (!game_list.equals(latestGameList)){
				
				latestGameList = game_list;
				
				for (GameListObserver o : gameListObservers){
					o.gameListChanged(game_list);
				}
			}
		}
	}
	
	public interface ModelStateObserver{
		public void modelChanged(String model_data);	
	}
	
	public interface GameListObserver{
		public void gameListChanged(String game_list_data);	
	}
}
