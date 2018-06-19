package info.server.dto;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;

public class ChkBoxAList {
	private static ArrayList<JCheckBox> chk1;
	private static JTextArea ta=null;
	public static void setChkBoxList(ArrayList<JCheckBox> chk){
		chk1=chk;
	}
	public static ArrayList<JCheckBox> getChkBoxList(){
		return chk1;
	}	
	public static void setTextArea(JTextArea ta1){
		ta=ta1;
	}
	public static JTextArea getTextArea(){
		return ta;
	}
}
