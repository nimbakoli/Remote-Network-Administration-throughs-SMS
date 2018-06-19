package info.server.AdminServer;
import info.server.dto.MobileConfigDTO;

import org.smslib.AGateway;
import org.smslib.IOutboundMessageNotification;
import org.smslib.Library;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

public class SendMessage {
	//public void doIt() //throws Exception
	public void doIt(MobileConfigDTO mc) //throws Exception
	{
		try{
		OutboundNotification outboundNotification = new OutboundNotification();
		System.out.println("Example: Send message from a serial gsm modem.");
		System.out.println(Library.getLibraryDescription());
		System.out.println("Version: " + Library.getLibraryVersion());
		System.out.println("Test");
		//SerialModemGateway gateway = new SerialModemGateway("Modem #2", "COM16", 115200, "SAMSUNG", "S3500");
		System.out.println(mc.getGatewayName());
		System.out.println(mc.getComPortNo());
		System.out.println(mc.getCompName());
		System.out.println(mc.getModelNo());
		SerialModemGateway gateway = new SerialModemGateway(mc.getGatewayName(),mc.getComPortNo(), 115200,mc.getCompName(),mc.getModelNo());
		System.out.println("Test");
		gateway.setInbound(true);
		gateway.setOutbound(true);
		System.out.println("Test");
	//	gateway.setSimPin("0000");
		//gateway.setSimPin("89910371110094232537");
		System.out.println("Test");
		// Explicit SMSC address set is required for some modems.
		// Below is for VODAFONE GREECE - be sure to set your own!
		//gateway.setSmscNumber("+306942190000");
	//	gateway.setSmscNumber("+919032055002");
		System.out.println("Test4");
		Service.getInstance().setOutboundMessageNotification(outboundNotification);
		System.out.println("Test5");
		Service.getInstance().addGateway(gateway);
		System.out.println("Test6");
		Service.getInstance().startService();
		System.out.println("Test7");
		System.out.println();
		System.out.println("Modem Information:");
		System.out.println("  Manufacturer: " + gateway.getManufacturer());
		System.out.println("  Model: " + gateway.getModel());
		System.out.println("  Serial No: " + gateway.getSerialNo());
		System.out.println("  SIM IMSI: " + gateway.getImsi());
		System.out.println("  Signal Level: " + gateway.getSignalLevel() + " dBm");
		System.out.println("  Battery Level: " + gateway.getBatteryLevel() + "%");
		System.out.println();
		// Send a message synchronously.
		System.out.println("Hello World");
		//OutboundMessage msg = new OutboundMessage("919970898999", "Hello Bhavesh!");
		/*OutboundMessage msg = new OutboundMessage("919404494344", "Hello!");
		System.out.println("Hello World");
		Service.getInstance().sendMessage(msg);
		System.out.println(msg);*/
		// Or, send out a WAP SI message.
		//OutboundWapSIMessage wapMsg = new OutboundWapSIMessage("306974000000",  new URL("http://www.smslib.org/"), "Visit SMSLib now!");
		//Service.getInstance().sendMessage(wapMsg);
		//System.out.println(wapMsg);
		// You can also queue some asynchronous messages to see how the callbacks
		// are called...
		//msg = new OutboundMessage("309999999999", "Wrong number!");
		//srv.queueMessage(msg, gateway.getGatewayId());
		//msg = new OutboundMessage("308888888888", "Wrong number!");
		//srv.queueMessage(msg, gateway.getGatewayId());
		System.out.println("Now Sleeping - Hit <enter> to terminate.");
		System.in.read();
		Service.getInstance().stopService();
	}catch(Exception e){ e.printStackTrace();}
	}

	public class OutboundNotification implements IOutboundMessageNotification
	{
		public void process(AGateway gateway, OutboundMessage msg)
		{
			System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
			System.out.println(msg);
		}
	}
/*	public static void main(String args[])
	{
		SendMessage app = new SendMessage();
		try
		{
			app.doIt();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}*/

}
