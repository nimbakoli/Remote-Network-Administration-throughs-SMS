package info.server.MainFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//import govt.fir.dbTemplate.JDBCTemplate;
import info.server.AdminServer.Server;
import info.server.log.SystemLogger;

public class Login extends JFrame implements ActionListener{
	//this is chang from remote
	private static Login _instance;
	public static synchronized Login getInstance(){
		if(_instance==null)
			_instance=new Login();
		return _instance;
	}
	private JTextField txtID;
	private JPasswordField txtPassword;
	private Login(){
		setTitle("LoginWindow");
		Image img=Toolkit.getDefaultToolkit().getImage("Icons\\control.png");
		setIconImage(img);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(200,200);
		//setLocationRelativeTo(null);
		setSize(800,400);
		JLabel lblImage=new JLabel(new ImageIcon("icons\\4.jpg"));
		add(lblImage,"West");
		
		JPanel panelComp=new JPanel();
		add(panelComp);
		panelComp.setLayout(null);
		
		JLabel lbl=new JLabel("Remote Network Administration Server Login run.....");
		lbl.setFont(new Font("Copperplate Gothic Light",Font.BOLD,12));
		lbl.setForeground(new Color(128,0,128));
		lbl.setBounds(10,80,600,20);
		panelComp.add(lbl);
		
		JLabel lbl1=new JLabel("Login Id");
		lbl1.setFont(new Font("Copperplate Gothic Light",Font.BOLD,14));
		lbl1.setForeground(new Color(128,0,128));
		lbl1.setBounds(10,130,150,20);
		panelComp.add(lbl1);
		
		txtID=new JTextField();
		txtID.setBounds(100,130,100,20);
		panelComp.add(txtID);
		
		

		JLabel lbl2=new JLabel("Password");
		lbl2.setFont(new Font("Copperplate Gothic Light",Font.BOLD,14));
		lbl2.setForeground(new Color(128,0,128));
		lbl2.setBounds(10,170,150,20);
		panelComp.add(lbl2);

		txtPassword=new JPasswordField();
		txtPassword.setBounds(100,170,100,20);
		panelComp.add(txtPassword);

		JLabel lbl3=new JLabel("<html><body><u> Login</u></body></html>");
		lbl3.setFont(new Font("Copperplate Gothic Light",Font.CENTER_BASELINE,14));
		lbl3.setForeground(Color.blue);
		lbl3.setBounds(60,210,150,20);
		lbl3.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				dispose();
			}
			public void mouseEntered(MouseEvent e){
				Cursor c=new Cursor (Cursor.HAND_CURSOR);
				setCursor (c);

			}
			public void mouseExited(MouseEvent e){
				Cursor c=new Cursor (Cursor.DEFAULT_CURSOR);
				setCursor (c);
			}
		});
//		panelComp.add(lbl3);

		JButton btnLogin=new JButton("Login");
		btnLogin.setBackground(Color.white);
		btnLogin.setForeground(new Color(128,0,128));
		btnLogin.setBounds(20,260,80,40);
		btnLogin.addActionListener(this);
		btnLogin.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if((e.getKeyCode())==KeyEvent.VK_ENTER)
				getCheckPass();
			}
		});
		panelComp.add(btnLogin);

		JButton btnCancel=new JButton("Cancel");
		btnCancel.setBackground(Color.white);
		btnCancel.setForeground(new Color(128,0,128));
		btnCancel.setBounds(120,260,80,40);
		btnCancel.addActionListener(this);
		btnCancel.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if((e.getKeyCode())==KeyEvent.VK_ENTER)
					System.exit (0);
			}
		});
		panelComp.add(btnCancel);
		
		JButton btnchangpwd=new JButton("chang ID & pwd");
		btnchangpwd.setBackground(Color.white);
		btnchangpwd.setForeground(new Color(128,0,128));
		btnchangpwd.setBounds(220,260,150,40);
		btnchangpwd.addActionListener(this);
		btnchangpwd.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if((e.getKeyCode())==KeyEvent.VK_ENTER)
					getchangpass();
			}
		});
		panelComp.add(btnchangpwd);
	}
	public static void main(String args[]){
		//SystemLogger.logger=new SystemLogger().getLoggerInstance("logger\\log.log");
		Login.getInstance().setVisible(true);


	}

	public void actionPerformed(ActionEvent e){
		String str=e.getActionCommand();
		if(str.equals("Login"))
		getCheckPass();
		else if(str.equals("Cancel"))
			System.exit(0);		
		else
			getchangpass();
	    
	
	}  
	private void getchangpass(){
		boolean f=false;
		
		
		String IDc=JOptionPane.showInputDialog(null,"Enter the old ID","chang ID & pwd",JOptionPane.QUESTION_MESSAGE);
	

   
      JPasswordField passwordField = new JPasswordField();
      passwordField.setEchoChar('*');
      Object[] obj = {"Please enter the  old password:\n\n", passwordField};
      Object stringArray[] = {"OK"};
      JOptionPane.showOptionDialog(null, obj, "Authentication",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, obj);
      char pwd[] = passwordField.getPassword();
     String pwdc=new String(pwd);
     System.out.print(pwdc);
     
		  try{
		  java.sql.Connection c=null;
		  c=JDBCTemplate.getConnection();
		  java.sql.Statement str=c.createStatement();
		  java.sql.ResultSet rs3=str.executeQuery("SELECT  LoginID FROM Login WHERE LoginID='"+IDc+"' AND password='"+pwdc+"'");
		    if(rs3.next())
		    {
		 	  f=true;System.out.print(f);
		     }
		    else
		     {
				JOptionPane.showMessageDialog (null, "Enter the valid LoginID and Password","authentication",JOptionPane.QUESTION_MESSAGE);
				Thread.sleep(100);
				//System.exit(0);
				System.out.print(pwdc);
				System.exit(0);
		     }
		  }
		   catch(Exception e)
		   {
			  
		   }
		
		
		String IDn=JOptionPane.showInputDialog(null,"Enter the new ID","chang ID & pwd",JOptionPane.QUESTION_MESSAGE);
		  JPasswordField passwordFieldl = new JPasswordField();
	      passwordFieldl.setEchoChar('*');
	      Object[] objl = {"Please enter the new password:\n\n", passwordFieldl};
	      Object stringArrayl[] = {"OK"};
	  
	      JOptionPane.showOptionDialog(null, objl, "Authentication",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArrayl, obj);
	      char pwdl[] = passwordFieldl.getPassword();
	      String pwdn=new String(pwdl);
	    	
	      System.out.print(pwdn);
		try{
		java.sql.Connection c1=null;
		c1=JDBCTemplate.getConnection();
		java.sql.Statement st1=c1.createStatement();
		boolean rs1=st1.execute("UPDATE Login SET LoginID='"+IDn+"'where LoginID='"+IDc+"'");
		boolean rs2=st1.execute("UPDATE Login SET password='"+pwdn+"'where password='"+pwdc+"'");
		System.out.print(rs1);
		
	    if(!rs1&&!rs2)
		     {
			   JOptionPane.showMessageDialog (null, "LoginID and Password chang succssesfully","chang ID & pwd",JOptionPane.QUESTION_MESSAGE);
			   System.out.print("data base updated succsessfully");
			 }
	   }
	   catch(Exception e)
		   {
		     System.out.print(e);
		   }
    }		
	private void getCheckPass(){
		String password = new String (txtPassword.getPassword());
		if(txtID.getText().equals("") && password.equals("")){
			JOptionPane.showMessageDialog (this, "Required Valid UserName and password");
		    txtID.requestFocus();
		}
		else if(txtID.getText().equals ("")){
			JOptionPane.showMessageDialog (this, "Required Valid UserName.");
			txtID.requestFocus();
		}
		else if (password.equals ("")){
			txtPassword.requestFocus();
			JOptionPane.showMessageDialog (null,"Required valid Password.");
		}
		else{
			java.sql.Connection con=null;
			try{
				String id=txtID.getText().trim();
				boolean flag=false;
				if(!id.equals(null)&&!id.equals("")&&!password.equals(null)&&!password.equals("")){
					con=JDBCTemplate.getConnection();
					java.sql.Statement st=con.createStatement();
					java.sql.ResultSet rs=st.executeQuery("SELECT  LoginID FROM Login WHERE LoginID='"+id+"' AND password='"+password+"'");
					if(rs.next())
					flag=true;
				}
				if(flag){
					dispose();
					//SystemLogger.logger=new SystemLogger().getLoggerInstance("logger\\log.log");
					ServerFrame sf=new ServerFrame();
					sf.setVisible(true);
			     	ServerFrame.setServerFrame(sf);
					try{
						//SystemLogger.logger=new SystemLogger().getLoggerInstance("logger\\log.log");
										}catch(Exception e){}					
				}
				else{
					JOptionPane.showMessageDialog (this, "Incorrect UserName and Password \n Please Provide the Correct Information.");
					txtID.setText ("");
					txtPassword.setText ("");
					txtID.requestFocus ();
				}
			}catch(Exception e){
				//SystemLogger.passException(e);
			}
			finally{
				try{
					if(con!=null)
					con.close();
				}catch(Exception ex){
					//SystemLogger.passException(ex);
				}
			}
		}
	}

	}


