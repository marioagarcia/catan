package test;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.Test;
import client.communication.server.ServerMoxy;
import client.communication.server.ServerPoller;

public class ServerPollerTest {

	public int number_of_polls = 0;
	
	@Test
	public void test(){
		ServerPoller poller = new ServerPoller();
		
		poller.setProxy(new ServerMoxy());
		
		poller.registerObserver(observer);
		
		Date before_test = new Date();
		poller.startPoller(3000);
		
		Date now = null;
		
		while (number_of_polls < 10){
			now = new Date();
			if (now.getTime() - before_test.getTime() >= 30000){
				break;
			}
		}
		
		long time_taken = now.getTime() - before_test.getTime();
		
		assertTrue(number_of_polls == 10 && (time_taken / 1000 >= 27 && time_taken / 1000 <= 32));
		
	}
	
	private ServerPoller.ModelStateObserver observer = new ServerPoller.ModelStateObserver() {
		
		@Override
		public void modelChanged(String model_data) {
			number_of_polls++;
			System.out.println("Poll complete");
		}
	};

}
