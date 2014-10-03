package client.communication.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import shared.serialization.ModelSerializer;
import client.manager.GameData;

public class ServerPoller implements ServerPollerInterface
{
	private boolean isRunning;
	private String latestModelVersion;
	private String gameId;
	private String latestModel;
	private Timer pollTimer = null;
	private TimerTask pollMethod = null;
	private ServerProxyInterface proxyObject = null;
	private ModelSerializer serializer = null;
	
	private transient Collection<ModelStateObserver> modelObservers;
	
	public ServerPoller(String current_model_version){
		isRunning = false;
		latestModelVersion = current_model_version;
		
		boolean is_daemon = true;
		pollTimer = new Timer(is_daemon);
		pollMethod = new CatanPoller();
		
		modelObservers = new ArrayList<ModelStateObserver>();
		serializer = new ModelSerializer();
	}
	
	public void startPoller(long interval){
		if (!isRunning){
			Date current_time = new Date();
			pollTimer.scheduleAtFixedRate(pollMethod, current_time, interval);
			isRunning = true;
		}
	}
	
	public void registerObserver(ModelStateObserver new_observer){
		modelObservers.add(new_observer);
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
			//gameId JSON? Where will this come from
			String model = proxyObject.getGameModel(gameId, latestModelVersion);
			GameData data_object = serializer.deserializeGameModel(model);
			
			if (!model.equals("True")){
				latestModel = model;
				
				//Notify listeners
				for (ModelStateObserver o : modelObservers){
					o.modelChanged(data_object);
				}
				
			}
		}
	}
	
	public interface ModelStateObserver{
		public void modelChanged(GameData model_data);	
	}
}
