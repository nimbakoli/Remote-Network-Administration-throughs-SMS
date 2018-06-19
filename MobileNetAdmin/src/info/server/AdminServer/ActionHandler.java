package info.server.AdminServer;
import info.server.MainFrame.JDBCTemplate;
import info.server.MainFrame.MobileConfig;
import info.server.MainFrame.ServerFrame;
import info.server.dto.ChkBoxAList;
import info.server.dto.ServerDTO;
import info.server.log.SystemLogger;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;

public class ActionHandler implements ActionListener{
//	ArrayList<JCheckBox> chkA=null;
	//private static ArrayList<Socket> socketA=null;
	public void actionPerformed(ActionEvent e){
		sendDataToClient(e.getActionCommand());
	}
	public void sendDataToClient(String str){
		try{
			ArrayList<JCheckBox> chkA=ChkBoxAList.getChkBoxList();
			if(chkA!=null){
				  JPasswordField passwordField = new JPasswordField();
			      passwordField.setEchoChar('*');
			      Object[] obj = {"Please enter the password:\n\n", passwordField};
			      Object stringArray[] = {"OK"};
			      JOptionPane.showOptionDialog(null, obj, "Authentication",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, obj);
			      char pwd[] = passwordField.getPassword();
			     String pwdc=new String(pwd);
				  try{
				      java.sql.Connection c=null;
				      c=JDBCTemplate.getConnection();
				      java.sql.Statement str3=c.createStatement();
				      java.sql.ResultSet rs3=str3.executeQuery("SELECT  LoginID FROM Login WHERE password='"+pwdc+"'");
				      if(rs3.next())
				      {
				    		System.out.println("chkA1"+chkA);
							ArrayList<Socket> socketA=ServerDTO.getASocketList();
							for(int i=0;i<chkA.size();i++){
								JCheckBox ch=(JCheckBox)chkA.get(i);
								if(ch.isSelected()){
									Socket s= (Socket)socketA.get(i);
									PrintWriter out = new PrintWriter(s.getOutputStream(),true);
									out.println(str);
								}				
							}
				     }
				    else
				     {
				    	System.out.print("enter the valid password");
				    	JOptionPane.showMessageDialog(null,"<html><font size=4 color=red style=Verdana>You are enter Rong password","authentication",JOptionPane.ERROR_MESSAGE);
				    
				     }
				  }
				 catch(Exception SQL)
				 {
					 
				 }
			
			}else if(str.equals("MobileConfig")){
				
				  JPasswordField passwordField = new JPasswordField();
			      passwordField.setEchoChar('*');
			      Object[] obj = {"Please enter the password:\n\n", passwordField};
			      Object stringArray[] = {"OK"};
			      JOptionPane.showOptionDialog(null, obj, "Authentication",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, obj);
			      char pwd[] = passwordField.getPassword();
			    String  pwdc=new String(pwd);
				  try{
				        java.sql.Connection c=null;
				        c=JDBCTemplate.getConnection();
				        java.sql.Statement str1=c.createStatement();
				        java.sql.ResultSet rs3=str1.executeQuery("SELECT  LoginID FROM Login WHERE password='"+pwdc+"'");
				         if(rs3.next())
				         {
				        	 
				    	         new MobileConfig(ServerFrame.getServerFrame(),"Mobile Configuration Frame",true);
				         }
				         else
				         {
				    	   System.out.print("enter the valid password");
				    	   JOptionPane.showMessageDialog(null,"<html><font size=4 color=red style=Verdana>You are enter Rong password","authentication",JOptionPane.ERROR_MESSAGE);
				         }
				      }
				    catch(Exception SQL)
				     {
					 
				     }
			
			   }
		   else{
				JTextArea ta=ChkBoxAList.getTextArea();
				ta.setText("Client isn't connected");
			}	
		}catch(Exception e){
			//SystemLogger.passException(e);
			e.printStackTrace();			
		}
	}
}
