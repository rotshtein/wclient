package wclient;

import java.net.URI;
import java.net.URISyntaxException;
import org.apache.log4j.Logger;

public class Main
{
	static Logger logger = Logger.getLogger("Main");
	public static void main(String[] args)
	{
		TestServer();

	}
	
	public static void TestServer()
	{
		try
		{
			ManagementClient c = new ManagementClient(new URI("ws://127.0.0.1:8887"));
			c.connect();
			
			while (!c.isOpen());
			
			c.send("Hello");
		}
		catch (URISyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
