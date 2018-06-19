//package info.server.AdminServer;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.PrintWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Client extends JFrame{
	private String ip=null;
	private Socket socket=null;
	Client(String ip){
		this.ip=ip;
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Remote Network Administration Through SMS Client has been running.............");
        addWindowListener(new ExitListener());
		SystemTrayIcon();
		Image img=Toolkit.getDefaultToolkit().getImage("icons\\network.png");
		setIconImage(img);
		ClientThread t=new ClientThread();
		t.start();
		JPanel n=new JPanel();
		n.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
		JLabel lbl=new JLabel("Remote Network Administration Through SMS Client");
		lbl.setFont(new Font("Arial",Font.BOLD,20));
		n.add(lbl);
		add(n,"North");

		JPanel c=new JPanel();
		c.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
		c.add(new javax.swing.JLabel(new javax.swing.ImageIcon("icons\\111.jpeg")));
		add(c,"Center");

		SP s=new SP(socket);
		s.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
		add(s,"South");

		}

		/*	WindowListener wl=new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				  {
					           try{
									Process p = Runtime.getRuntime().exec("shutdown -s -t 00");
								   }
								catch(Exception ex)
								{
									}

					  }
			}*/


	private void SystemTrayIcon(){
		try{
			SystemTray tray;
			TrayIcon trayIcon;
			tray = SystemTray.getSystemTray();
			PopupMenu popup = new PopupMenu();
			MenuItem exititem = new MenuItem("Shutdown");
			exititem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){

                            try{
								//JOptionPane.showMessageDialog (null, "shutdown client no. permission to exit this application");
								//Thread.sleep(100000);
								Process p = Runtime.getRuntime().exec("shutdown -s -t 00");
							   }
							catch(Exception ex)
							{
								}

				}
			});
			popup.add(exititem);
			trayIcon = new TrayIcon(new ImageIcon("icons\\mainicon.png").getImage(), "Remote Network Administration Through SMS Client  Copyright @ 2013-2014",popup);
			tray.add(trayIcon);
			trayIcon.displayMessage("NetworkClient","Remote Network Administration Client has been running......",TrayIcon.MessageType.INFO);
		}
		catch(Exception e){
			//SystemLogger.passException(e);
			e.printStackTrace();
		}
	}
	
	class ExitListener extends WindowAdapter
	{
		public void windowClosing(WindowEvent event)
		{
			try{
				//JOptionPane.showMessageDialog (null, "shutdown client no. permission to exit this application");
				//Thread.sleep(100000);
		         Process p = Runtime.getRuntime().exec("shutdown -s -t 00");
							}catch(Exception ex){}
	    }
	}

	class ClientThread extends Thread {

		BufferedReader br=null;
		private String str=null;
		public ClientThread() {
			// connect to server
			try {
					socket = new Socket(ip,5555);
					br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
					System.out.println("Connected with server " +socket.getInetAddress()+":" + socket.getPort());
			}
			catch (UnknownHostException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void run() {
			try {
				while(true){
					str=br.readLine();
					if(str!=null){
						if(str.equals("Log Off")){
							try{
								Process p = Runtime.getRuntime().exec("shutdown -l");
								System.out.println("Hello World");
							}catch(Exception ex){ex.printStackTrace();}
						}else if(str.equals("Restart")){
							System.out.println(str);
							try{
								Process p = Runtime.getRuntime().exec("shutdown -r -t 00");
							}catch(Exception ex){}

						}else if(str.equals("Shutdown")){
							System.out.println(str);
							//out.println(localhost +": Shutdown Client");
						//	Runtime rt = Runtime.getRuntime();
						//	rt.exec("shutdown -f -s");

							try{
								Process p = Runtime.getRuntime().exec("shutdown -s -t 00");
							}catch(Exception ex){}
						}
					}
	           	}
		    }
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String args[]){

		String ip=(JOptionPane.showInputDialog(null,"Enter The Server IP Address","IP Client",JOptionPane.QUESTION_MESSAGE));

        System.out.print(ip);
		if(ip!=null&&!ip.equals("")){
			new Client(ip);
		}
		else{
			Icon error=new ImageIcon("icons\\exit.PNG");
			JOptionPane.showMessageDialog(null,"<html><font size=4 color=red style=Verdana>IP Address Can't be blank","IP Blank",JOptionPane.ERROR_MESSAGE,error);
			//System.exit(0);
		}
	}
}
