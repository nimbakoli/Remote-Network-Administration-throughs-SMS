package info.server.MainFrame;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
@SuppressWarnings("serial")
public class NP extends javax.swing.JPanel{
	public NP(){
		//setBackground(Color.white);
		JLabel lbl=new JLabel("Remote Network Administration Through SMS server");
		lbl.setFont(new Font("Arial",Font.BOLD,20));
		add(lbl);
	}
}
