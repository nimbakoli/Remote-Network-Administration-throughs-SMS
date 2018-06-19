package info.server.AdminServer;

import info.server.MainFrame.WP;
import info.server.dto.ChkBoxAList;
import info.server.dto.ServerDTO;
import info.server.dto.WestPanelDTO;
import info.server.log.SystemLogger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;

public class DataInputServer extends Thread {
	Socket client;
	private BufferedReader br=null;
	DataInputServer(Socket client){
		try{
			this.client=client;	
			br=new BufferedReader(new InputStreamReader(client.getInputStream()));
		}catch(Exception e){
			//SystemLogger.passException(e);
			e.printStackTrace();
		}
	}
	public void run(){
		try{
			JTextArea ta=ChkBoxAList.getTextArea();
			ta.append(br.readLine()+"\n\r");    		
		}catch(Exception e){
			ArrayList<Socket> aSocket=ServerDTO.getASocketList();
			ArrayList<JCheckBox> aCheck=ChkBoxAList.getChkBoxList();
			JTextArea ta=ChkBoxAList.getTextArea();
			WP wp=WestPanelDTO.getWestPDTO();
			int no= aSocket.indexOf(client);
			aSocket.remove(client);
			JCheckBox ch =(JCheckBox)aCheck.get(no);
			ta.append(" Client is disconnect"+ch.getText()+"\n\r");
			aCheck.remove(no);
			wp.remove(no+1);
			wp.repaint();
			e.printStackTrace();
			//SystemLogger.passException(e);
		}
	}
}
