package test;
import org.junit.runner.JUnitCore;

public class TestMain {

	public static void main(String[] args)
	{
		String[] classes = new String[] {
				"test.ServerPollerTest",
				"test.ServerProxyTest",
				"test.GameManagerTest",
				"test.FacadeTest",
				"test.model.map.MapTest"
		};
		
		JUnitCore.main(classes);
	}
}
