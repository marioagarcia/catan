package test;
import org.junit.runner.JUnitCore;

public class TestMain {

	public static void main(String[] args)
	{
		String[] classes = new String[] {
				"test.ServerPollerTest",
				"test.ServerProxyTest",
				"test.model.map.MapTest",
				//"test.server.CommandsTest",
				"test.server.CommandsTest2",
				"test.server.ServerModelFacadeTest"
		};
		
		JUnitCore.main(classes);
	}
}
