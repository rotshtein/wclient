package wclient;

import java.net.URI;
import java.nio.ByteBuffer;

import org.apache.log4j.Logger;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

class ManagementClient extends WebSocketClient
{
	static Logger logger = Logger.getLogger("ManagementClient");
	public ManagementClient(URI serverUri)
	{
		super(serverUri);
	}

	@Override
	public void onOpen(ServerHandshake handshakedata)
	{
		logger.info("Connected");

	}

	@Override
	public void onMessage(String message)
	{
		logger.info("got message: " + message);
	}
	@Override
	public void onMessage(ByteBuffer buffer)
	{
		
	}

	@Override
	public void onClose(int code, String reason, boolean remote)
	{
		System.out.println("Disconnected");
		System.exit(0);

	}

	@Override
	public void onError(Exception ex)
	{
		ex.printStackTrace();

	}
}