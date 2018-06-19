package info.server.AdminServer;
import info.server.MainFrame.MobileConfig;
import info.server.dto.ChkBoxAList;
import info.server.dto.MobileConfigDTO;
import info.server.dto.ServerDTO;

import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.swing.JCheckBox;

import org.smslib.AGateway;
import org.smslib.AGateway.GatewayStatuses;
import org.smslib.AGateway.Protocols;
import org.smslib.ICallNotification;
import org.smslib.IGatewayStatusNotification;
import org.smslib.IInboundMessageNotification;
import org.smslib.IOrphanedMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.InboundMessage.MessageClasses;
import org.smslib.Library;
import org.smslib.Message.MessageTypes;
import org.smslib.Service;
import org.smslib.crypto.AESKey;
import org.smslib.modem.SerialModemGateway;
import java.util.Date;
import java.util.Calendar;
import org.smslib.OutboundMessage;



public class ReadMessages extends Thread
{
	String modemName,comNo,compName,handsetNo,MobileNo;
	MobileConfigDTO dto=null;
	public void doIt(MobileConfigDTO dto) throws Exception
	{
		this.dto=dto;
	}
	
	public void run(){
	//public void doIt(MobileConfigDTO dto) throws Exception
	//{
		this.modemName=dto.getGatewayName();
		this.comNo=dto.getComPortNo();
		this.compName=dto.getCompName();
		this.handsetNo=dto.getModelNo();
		this.MobileNo=dto.getMobileNo();

		// Define a list which will hold the read messages.

		// Create the notification callback method for inbound & status report
		// messages.
		InboundNotification inboundNotification = new InboundNotification();
		// Create the notification callback method for inbound voice calls.
//		CallNotification callNotification = new CallNotification();
		//Create the notification callback method for gateway statuses.
//		GatewayStatusNotification statusNotification = new GatewayStatusNotification();
//		OrphanedMessageNotification orphanedMessageNotification = new OrphanedMessageNotification();
		try
		{
			SerialModemGateway gateway = new SerialModemGateway(modemName,comNo, 115200,compName,handsetNo);
			gateway.setProtocol(Protocols.PDU);
			gateway.setInbound(true);
			gateway.setOutbound(true);

			Service.getInstance().setInboundMessageNotification(inboundNotification);
//*			Service.getInstance().setCallNotification(callNotification);
//*			Service.getInstance().setGatewayStatusNotification(statusNotification);
//*			Service.getInstance().setOrphanedMessageNotification(orphanedMessageNotification);
			// Add the Gateway to the Service object.
			Service.getInstance().addGateway(gateway);
			// Similarly, you may define as many Gateway objects, representing
			// various GSM modems, add them in the Service object and control all of them.
			// Start! (i.e. connect to all defined Gateways)
			Service.getInstance().startService();
			//Service.getInstance().getKeyManager().registerKey("+919404494344", new AESKey(new SecretKeySpec("0".getBytes(), "AES")));
			// Read Messages. The reading is done via the Service object and
			// affects all Gateway objects defined. This can also be more directed to a specific
			// Gateway - look the JavaDocs for information on the Service method calls.
		//	msgList = new ArrayList<InboundMessage>();

//			Service.getInstance().readMessages(msgList, MessageClasses.ALL);

			//for (InboundMessage msg : msgList)
			//	System.out.println(msg);
			//for (int i=0;i<1;i++){
			//	InboundMessage msg =msgList.get(i);
			//	System.out.println(msg);
			//}

			// Sleep now. Emulate real world situation and give a chance to the notifications
			// methods to be called in the event of message or voice call reception.
			System.out.println("Now Sleeping - Hit <enter> to stop service.");
			System.in.read();
			System.in.read();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try{
			Service.getInstance().stopService();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	String strData=null,strMobileNo;
	char chr1;
	Date strDate,currDate;
	Calendar msgDate,today;
	public class InboundNotification implements IInboundMessageNotification{
		public void process(AGateway gateway, MessageTypes msgType, InboundMessage msg){
		if (msgType == MessageTypes.INBOUND) {
				System.out.println(">>> New Inbound message detected from Gateway: " + gateway.getGatewayId());
				try{
					strData=msg.getText().trim();
					System.out.println("Msg " + strData);
					chr1=strData.charAt(0);
					System.out.println(chr1);
					if(msg!=null&&(chr1=='L'||chr1=='R'||chr1=='S'||chr1=='l'||chr1=='r'||chr1=='s')){
						System.out.println("Info");
						strDate=msg.getDate();
						SimpleDateFormat sdf=new SimpleDateFormat("hh:mm");
						String mdt=sdf.format(strDate);
					    System.out.println("Msg  Time:"+ mdt);
					    int h,m;
					    h=Integer.parseInt(mdt.substring(0, 2));
					    m=Integer.parseInt(mdt.substring(3));
						currDate=new Date();
						SimpleDateFormat cdf=new SimpleDateFormat("hh:mm");
						String cdt=cdf.format(currDate);
						 System.out.println("System  Time:"+ cdt);
						 int ch,cm;
						    ch=Integer.parseInt(cdt.substring(0, 2));
						    cm=Integer.parseInt(cdt.substring(3));
						msgDate=Calendar.getInstance();
						msgDate.setTime(strDate);

						today=Calendar.getInstance();
						//System.out.println("System Date & Time"+currDate);
				
						//strMobileNo=msg.getSmscNumber();
						strMobileNo=msg.getOriginator();
						System.out.println("strMobileNo"+strMobileNo);
						System.out.println("MobileNo"+MobileNo);
					//	System.out.println("MobileNo="+strMobileNo);
						//System.out.println("HH");
						//today.get(Calendar.MINUTE)==msgDate.get(Calendar.MINUTE)&&
						if(ch==h && cm==m){// && strMobileNo.equals(MobileNo)){//&&(chr1=='L'||chr1=='R'||chr1=='S'||chr1=='l'||chr1=='r'||chr1=='s')){
							System.out.println(">>> New Inbound message detected from Configure Mobile>>>");
							OutboundMessage msg1 = new OutboundMessage("+"+MobileNo, "Hello! Successfully client is Log Off!(SMS From Mobile Network Server)");
							Service.getInstance().sendMessage(msg1);
							System.out.println("Msg Send Successfully");
							System.out.println("Logoff");
							ArrayList<JCheckBox> chkA=ChkBoxAList.getChkBoxList();
							if(chkA!=null){
								ArrayList<Socket> socketA=ServerDTO.getASocketList();
							//	String tmsg=strData; //L1 R1 S1
								char chr2=strData.charAt(1);
								System.out.println(chr1);
								String s2=Character.toString(chr2);
								int x=Integer.parseInt(s2);
								x--;
								Socket c= (Socket)socketA.get(x);
								PrintWriter out = new PrintWriter(c.getOutputStream(),true);
								if(chr1=='L'||chr1=='l'){
									 msg1 = new OutboundMessage("+"+MobileNo, "Hello! Successfully client "+s2+" is Log Off!(SMS From Mobile Network Server)");
									Service.getInstance().sendMessage(msg1);
									System.out.println("Msg Send Successfully");
									System.out.println("Logoff");
									try{
									Thread.sleep(20000);
									}catch(Exception e){}
									out.println("Log Off");
								}
								else if(chr1=='R'||chr1=='r'){
									 msg1 = new OutboundMessage("+"+MobileNo, "Hello! Successfully client "+s2+" is Log Off!(SMS From Mobile Network Server)");
									Service.getInstance().sendMessage(msg1);
									System.out.println("Msg Send Successfully");
									System.out.println("Restart");
									try{
									Thread.sleep(20000);
									}catch(Exception e){}
									out.println("Restart");
								}
								else if(chr1=='S'||chr1=='s'){
								   msg1 = new OutboundMessage("+"+MobileNo, "Hello! Successfully client "+s2+" is Log Off!(SMS From Mobile Network Server)");
									Service.getInstance().sendMessage(msg1);
									System.out.println("Msg Send Successfully");
									System.out.println("Shutdown");
									try{
									Thread.sleep(20000);
									}catch(Exception e){}
									out.println("Shutdown");
								}
								System.out.println("Read Message From Admin");
								System.out.println("modemName"+modemName);
								System.out.println("Msg="+strData);
							}
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	public class CallNotification implements ICallNotification
	{
		public void process(AGateway gateway, String callerId)
		{
			System.out.println(">>> New call detected from Gateway: " + gateway.getGatewayId() + " : " + callerId);
		}
	}

	public class GatewayStatusNotification implements IGatewayStatusNotification
	{
		public void process(AGateway gateway, GatewayStatuses oldStatus, GatewayStatuses newStatus)
		{
			System.out.println(">>> Gateway Status change for " + gateway.getGatewayId() + ", OLD: " + oldStatus + " -> NEW: " + newStatus);
		}
	}

	public class OrphanedMessageNotification implements IOrphanedMessageNotification
	{
		public boolean process(AGateway gateway, InboundMessage msg)
		{
			System.out.println(">>> Orphaned message part detected from " + gateway.getGatewayId());
			System.out.println(msg);
			// Since we are just testing, return FALSE and keep the orphaned message part.
			return false;
		}
	}

/*	public static void main(String args[])
	{
		ReadMessages app = new ReadMessages();
		try
		{

			app.doIt("Modem#2","COM16","SAMSUNG","S3500");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}*/
}
