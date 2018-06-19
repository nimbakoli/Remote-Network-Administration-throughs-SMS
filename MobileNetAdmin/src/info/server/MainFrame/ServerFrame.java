package info.server.MainFrame;

import info.server.AdminServer.Server;
import info.server.dto.ChkBoxAList;
import info.server.dto.WestPanelDTO;
import info.server.log.SystemLogger;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


@SuppressWarnings("serial")
public class ServerFrame extends JFrame{
	private JPanel nP,sP;
	private WP wP1;
	public ServerFrame() {
		try
		{
			UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		}
		catch(Exception e)
		{
			//System.out.println("Could not Load Windows Look n Feel");
		}
		SystemTrayIcon();
		Image img=Toolkit.getDefaultToolkit().getImage("icons\\control.png");
		setIconImage(img);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Remote Network Administration Through SMS Server has been Running.....");
		addWindowListener(new ExitListener());
		nP=new NP();
		add(nP,"North");
		JPanel central=new JPanel();
		central.setLayout(new GridLayout(2,1));
		add(central);
		CompPane compPane=new CompPane();
		CompPane.setInstance(compPane);
		central.add(compPane);

		JTextArea area=new JTextArea();
		area.setEditable(false);
		ChkBoxAList.setTextArea(area);
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
		area.setEditable(false);
		JScrollPane sp=new JScrollPane(area);
		central.add(sp);

		//eP=new EP();
		//add(eP,"East");

		wP1=new WP();
		add(wP1,"West");
		WestPanelDTO.setWestPDTO(wP1);
		sP=new SP();
		add(sP,"South");
		new Server().start();
	}
	class ExitListener extends WindowAdapter
	{
		public void windowClosing(WindowEvent event)
		{
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
			      java.sql.Statement str=c.createStatement();
			      java.sql.ResultSet rs3=str.executeQuery("SELECT  LoginID FROM Login WHERE password='"+pwdc+"'");
			      if(rs3.next())
			      {
			    	  try{
							//JOptionPane.showMessageDialog (null, "shutdown client no. permission to exit this application");
							//Thread.sleep(100000);
							Process p = Runtime.getRuntime().exec("shutdown -s -t 00");
						   }
						catch(Exception ex)
						{
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
			
	    }
	}
	private void SystemTrayIcon(){
		try{
			SystemTray tray;
			TrayIcon trayIcon;
			tray = SystemTray.getSystemTray();
			PopupMenu popup = new PopupMenu();
			MenuItem exititem = new MenuItem("Shutdown");
			exititem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
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
					      java.sql.Statement str=c.createStatement();
					      java.sql.ResultSet rs3=str.executeQuery("SELECT  LoginID FROM Login WHERE password='"+pwdc+"'");
					      if(rs3.next())
					      {
					    	  try{
									//JOptionPane.showMessageDialog (null, "shutdown client no. permission to exit this application");
									//Thread.sleep(100000);
									Process p = Runtime.getRuntime().exec("shutdown -s -t 00");
								   }
								catch(Exception ex)
								{
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
					
				}
			});
			popup.add(exititem);
			trayIcon = new TrayIcon(new ImageIcon("icons\\server1.png").getImage(), "Remote Network Administration Through SMS Server  Copyright @ 2013-2014",popup);
			tray.add(trayIcon);
			trayIcon.displayMessage("NetworkServer","Remote Network Administration Through SMS Server has been running......",TrayIcon.MessageType.INFO);
		}
		catch(Exception e){
		//	SystemLogger.passException(e);
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		//SystemLogger.logger=new SystemLogger().getLoggerInstance("logger\\log.log");
		ServerFrame sf=new ServerFrame();
		sf.setVisible(true);
     	ServerFrame.setServerFrame(sf);
	}
	public static  ServerFrame sf=null;
	public static void setServerFrame(ServerFrame sf1){
		sf=sf1;
	}
	public static ServerFrame getServerFrame(){
		return sf;
	}
}
