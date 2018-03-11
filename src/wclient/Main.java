package wclient;

import java.net.URI;
import java.nio.ByteBuffer;

import org.apache.log4j.Logger;

import recorder_proto.Recorder.Header;
import recorder_proto.Recorder.OPCODE;
import recorder_proto.Recorder.RecordCommand;

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
			
			//while (!c.isOpen());
			Thread.sleep(200);
			
			c.send("Hello");
			
			RecordCommand r = RecordCommand.newBuilder()
					.setFrequency(950e6)
					.setRate(100e6)
					.setGain(0)
					.setFilename("xx")
					.setNumberOfSamples(1e10)
					.build();
			
			Header h = Header.newBuilder()
					.setOpcode(OPCODE.RECORD)
					.setSequence(123)
					.setMessageData(r.toByteString())
					.build();
			
			
			c.send(h.toByteArray());
			
			c.onMessage(ByteBuffer.wrap(h.toByteArray()));
			
			c.close();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
