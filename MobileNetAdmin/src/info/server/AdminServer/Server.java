package info.server.AdminServer;

import info.server.MainFrame.CompPane;
import info.server.MainFrame.WP;
import info.server.dto.ChkBoxAList;
import info.server.dto.ServerDTO;
import info.server.dto.WestPanelDTO;
import info.server.log.SystemLogger;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
public class Server extends Thread {
	ArrayList<JCheckBox> chkA=new ArrayList<JCheckBox>();
	WP pane=null;
	CompPane objBtnPane=null;
	public Server(){
		try{
			ServerSocket server_socket = new ServerSocket(5555);
			ServerDTO.setServerSocket(server_socket);
			System.out.println("Server waiting for client on port " +server_socket.getLocalPort() + "\n");
			//Get The Instance of Component
   			pane=WestPanelDTO.getWestPDTO();
   			objBtnPane=CompPane.getInstance();
			
		}catch(Exception e){
			e.printStackTrace();
			//SystemLogger.passException(e);
		}
	}
	public void run(){
    	while(true){
    		try{
    			Socket socket=ServerDTO.getServerSocket().accept();
    			if(socket!=null){
	    			InetAddress add=socket.getInetAddress();
	    			JTextArea ta=ChkBoxAList.getTextArea();
	    			ta.append("Client is Connected "+add.getHostName()+"\n\r");
	    			JCheckBox chk = new JCheckBox(add.getHostName(),true);
	    			chk.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							int i;
							for(i=0;i<chkA.size();i++){
								JCheckBox ch=(JCheckBox)chkA.get(i);
								if(ch.isSelected()){
									objBtnPane.setButtonProperty(true);
									System.out.println("Set Button Property");
									break;
								}
							}
							if(i==chkA.size()){
								objBtnPane.setButtonProperty(false);
								System.out.println("Set Button Property");
							}
						}
					});
	    			chk.setBorder(new BevelBorder(BevelBorder.LOWERED));
	    			chkA.add(chk);
					pane.add(chk);
					ServerDTO.setASocketList(socket);
					ChkBoxAList.setChkBoxList(chkA);
	    			DataInputServer in=new DataInputServer(socket);
	    			in.start();
    			}
	    		}catch(Exception e){
    			//SystemLogger.passException(e);
    			e.printStackTrace();
    		}
    	}
    }

}
