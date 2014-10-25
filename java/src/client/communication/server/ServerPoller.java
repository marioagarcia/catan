package client.communication.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ServerPoller implements ServerPollerInterface
{
	private boolean isRunning;
	private String latestModel;
	private Timer pollTimer = null;
	private TimerTask pollMethod = null;
	private ServerProxyInterface proxyObject = null;
	
	private transient Collection<ModelStateObserver> modelObservers;
	
	public ServerPoller(){
		isRunning = false;
		
		boolean is_daemon = true;
		pollTimer = new Timer(is_daemon);
		pollMethod = new CatanPoller();
		
		modelObservers = new ArrayList<ModelStateObserver>();
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
			String model = proxyObject.getGameModel(true); //Sucktastic
			
			if (!model.equals("\"true\"") && !model.equals("\"Success\"")){
				
				latestModel = model;
				
				for (ModelStateObserver o : modelObservers){
					o.modelChanged(model);
				}
			}
		}
	}
	
	public interface ModelStateObserver{
		public void modelChanged(String model_data);	
	}
}
