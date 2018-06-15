package wclient;

import java.net.URI;
import java.nio.ByteBuffer;

import org.apache.log4j.Logger;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import com.google.protobuf.InvalidProtocolBufferException;
import recorder_proto.Recorder.Header;
import recorder_proto.Recorder.RecordCommand;

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
		Header h = null;
		try
		{
			h = Header.parseFrom(buffer);
			
			if (h != null)
			{
				logger.info("Got header. Command = " + h.getOpcode());
			}
			//int i = h.getOpcodeValue();
			switch (h.getOpcode())
			{
			case RECORD:
				RecordCommand r = RecordCommand.parseFrom(h.getMessageData());
				if (r != null)
				{
					logger.info("Got RecordCommand.");
					logger.info("Frequncy = " + r.getFrequency());
				}
				break;
				
			default:
				logger.info("Unknown command.");
				break;
			}
			
			
		}
		catch (InvalidProtocolBufferException e)
		{
			logger.error("Protocol buffer Header parsing error",e);
		}
		
		
		
		
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