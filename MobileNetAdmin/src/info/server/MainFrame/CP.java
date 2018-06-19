package info.server.MainFrame;




import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CP extends JPanel{
	/**
	 *
	 */
	public CP(){
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),""));
		add(new javax.swing.JLabel(new javax.swing.ImageIcon("images\\mobilenw.png")));
	}
	/*public void paintComponent(Graphics g){
		Toolkit kit=Toolkit.getDefaultToolkit();
		Image img=kit.getImage("images\\server.jpg");
		MediaTracker t=new MediaTracker(this);
		t.addImage(img,1);
		while(true){
			try{
				t.waitForID(1);
				break;
			}
			catch(Exception e){}
		}
		g.drawImage(img,0,0,680,500,null);
	}*/

}
