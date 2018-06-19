package info.server.MainFrame;


import info.server.AdminServer.ReadMessages;
import info.server.dto.MobileConfigDTO;
import info.server.log.SystemLogger;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MobileConfig extends JDialog {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	//JDialog(Frame owner, String title, boolean modal)
	public MobileConfig(ServerFrame sf,String title,boolean b){
		setSize(500,500);
		setVisible(true);
		setBackground(Color.white);
		setTitle(title);
		Container contentPane=getContentPane();
		JLabel lbl=new JLabel("Mobile Configuration Frame",JLabel.CENTER);
		lbl.setFont(new Font("Arial",Font.BOLD,15));
		contentPane.add(lbl,"North");
		contentPane.add(new javax.swing.JLabel(new javax.swing.ImageIcon("images\\server.jpg")),"West");
		contentPane.add(new CompPanel(),"Center");
		contentPane.add(new BtnPanel(),"South");
		System.out.println("HelloWorld");

	}
	private JTextField txtGatewayName,txtComPortNo,txtCompName,txtModelNo,txtMobileNo;
	private JLabel lblIcon[],err;
	class CompPanel extends JPanel{
		CompPanel(){
			Image img=Toolkit.getDefaultToolkit().getImage("icons\\network2.png");
			setIconImage(img);
			setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
			setBackground(Color.white);
			setLayout(null);
			lblIcon=new JLabel[6];
			for(int i=0;i<6;i++)
				lblIcon[i]=new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("icons\\err.png")));

			JLabel lbl=new JLabel("Gateway Name ");
			lbl.setBounds(40,40,150,20);
			add(lbl);
			txtGatewayName=new JTextField();
			txtGatewayName.setBounds(150,40,150,20);
			add(txtGatewayName);
			lblIcon[0].setBounds(315,40,40,20);
			add(lblIcon[0]);

			JLabel lbl1=new JLabel("ComPortNo");
			lbl1.setBounds(40,80,150,20);
			add(lbl1);
			txtComPortNo=new JTextField();
			txtComPortNo.setBounds(150,80,150,20);
			add(txtComPortNo);
			lblIcon[1].setBounds(315,80,40,20);
			add(lblIcon[1]);

			JLabel lbl2=new JLabel("CompName");
			lbl2.setBounds(40,120,150,20);
			add(lbl2);
			txtCompName=new JTextField();
			txtCompName.setBounds(150,120,150,20);
			add(txtCompName);
			lblIcon[2].setBounds(315,120,40,20);
			add(lblIcon[2]);

			JLabel lbl3=new JLabel("Model No");
			lbl3.setBounds(40,160,150,20);
			add(lbl3);
			txtModelNo=new JTextField();
			txtModelNo.setBounds(150,160,150,20);
			add(txtModelNo);
			lblIcon[3].setBounds(315,160,40,20);
			add(lblIcon[3]);

			JLabel lbl4=new JLabel("Mobile No");
			lbl4.setBounds(40,200,150,20);
			add(lbl4);
			txtMobileNo=new JTextField();
			txtMobileNo.setBounds(150,200,150,20);
			add(txtMobileNo);
			lblIcon[4].setBounds(315,200,40,20);
			add(lblIcon[4]);

			err=new JLabel("Required data");
			err.setForeground(Color.red);
			err.setBounds(175,300,80,20);
			add(err);

			lblIcon[5].setBounds(75,300,40,20);
			add(lblIcon[5]);
		}
	}
	public boolean isValid1(){
		boolean flag=true;
		String strGN=txtGatewayName.getText().trim();
		String strCPNo=txtComPortNo.getText().trim();
		String strCompN=txtCompName.getText().trim();
		String strModelNo=txtModelNo.getText().trim();
		String strMobileNo=txtMobileNo.getText().trim();
		if(strGN==null||strGN.equals("")||strGN.length()>10){
			lblIcon[0].setVisible(true);
			flag=false;
		}else if(strCPNo==null||strCPNo.equals("")||strCPNo.length()>10){
			lblIcon[0].setVisible(false);
			lblIcon[1].setVisible(true);
			flag=false;
		}else if(strCompN==null||strCompN.equals("")||strCompN.length()>15){
			lblIcon[0].setVisible(false);
			lblIcon[1].setVisible(false);
			lblIcon[2].setVisible(true);
			flag=false;
		}else if(strModelNo==null||strModelNo.equals("")||strModelNo.length()>10){
			lblIcon[0].setVisible(false);
			lblIcon[1].setVisible(false);
			lblIcon[2].setVisible(false);
			lblIcon[3].setVisible(true);
			flag=false;
		}else if(strMobileNo==null||strMobileNo.equals("")||strMobileNo.length()<10){
			lblIcon[0].setVisible(false);
			lblIcon[1].setVisible(false);
			lblIcon[2].setVisible(false);
			lblIcon[3].setVisible(false);
			lblIcon[4].setVisible(true);
			flag=false;
		}else{
			lblIcon[4].setVisible(false);
			MobileConfigDTO dto=new MobileConfigDTO();
			System.out.println("Connect");
			dto.setGatewayName(strGN);
			dto.setComPortNo(strCPNo);
			dto.setCompName(strCompN);
			dto.setModelNo(strModelNo);
			dto.setMobileNo("91"+strMobileNo);
			ReadMessages app = new ReadMessages();
			dispose();
			try{
				app.doIt(dto);
				app.start();
			}
			catch (Exception e){
				//SystemLogger.passException(e);
				e.printStackTrace();
			}
		}
		return flag;
	}
	class BtnPanel extends JPanel{
		JButton btnConnect,btnReset,btnExit;
		BtnPanel(){
			btnConnect=new JButton("Connect",new ImageIcon("icons\\connect.png"));
			btnConnect.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(isValid1()){
						txtGatewayName.setText("");
						txtComPortNo.setText("");
						txtCompName.setText("");
						txtModelNo.setText("");
						txtMobileNo.setText("");
						for(int i=0;i<6;i++)
							lblIcon[i].setVisible(true);
						lblIcon[5].setVisible(true);
						err.setText("Required Above data");
						dispose();
					}
				}
			});
			add(btnConnect);
			btnReset=new JButton("Reset",new ImageIcon("icons\\reset.png"));
			btnReset.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					txtGatewayName.setText("");
					txtComPortNo.setText("");
					txtCompName.setText("");
					txtModelNo.setText("");
					txtMobileNo.setText("");
					for(int i=0;i<6;i++)
						lblIcon[i].setVisible(true);
					//lbl.setText("Required data");
				}
			});
			add(btnReset);
			btnExit=new JButton("Exit",new ImageIcon("icons\\exit.png"));
			btnExit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					dispose();
				}
			});
			add(btnExit);
		}
	}
}
