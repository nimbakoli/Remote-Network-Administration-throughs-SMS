package info.server.MainFrame;





import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class WP extends javax.swing.JPanel {
	public WP(){
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
		//setBackground(Color.white);
		setLayout(new GridLayout(10,1,5,10));
		add(new JLabel("Connected Client List"));
	}
}
