package info.server.MainFrame;

import info.server.AdminServer.ActionHandler;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CompPane extends JPanel {
	private JButton btnExit;
	JButton btnLogOff=new JButton("Log Off",new ImageIcon("icons\\logoff.png"));
	JButton btnRestart=new JButton("Restart",new ImageIcon("icons\\restart.png"));
	JButton btnShutdown=new JButton("Shutdown",new ImageIcon("icons\\shutdown.jpg"));
	JButton btnConfMobile=new JButton("MobileConfig",new ImageIcon("icons\\mobile.png"));
	private CP cP;
	CompPane(){
		setLayout(new GridLayout(1,2));

		cP=new CP();
		add(cP);

		JPanel btnPane=new JPanel();
		btnPane.setLayout(new GridLayout(5,1,5,20));
		btnPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
		ActionHandler ah=new ActionHandler();

		btnLogOff.addActionListener(ah);
		btnPane.add(btnLogOff);

		btnRestart.addActionListener(ah);
		btnPane.add(btnRestart);

		btnShutdown.addActionListener(ah);
		btnPane.add(btnShutdown);

		btnConfMobile.addActionListener(ah);
	    btnPane.add(btnConfMobile);
		btnExit=new JButton("Exit",new ImageIcon("icons\\exit.png"));
		btnExit.addActionListener(new ActionListener(){
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
		btnPane.add(btnExit);
		add(btnPane);
	}
	public void setButtonProperty(boolean f){
		btnLogOff.setEnabled(f);
		btnRestart.setEnabled(f);
		btnShutdown.setEnabled(f);
	}
	private static CompPane pane=null;
	public static void setInstance(CompPane obj){
		pane=obj;
	}
	public static CompPane getInstance(){
		return pane;
	}
}
