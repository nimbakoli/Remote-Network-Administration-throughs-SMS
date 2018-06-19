package info.server.dto;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

public class ServerDTO {
	private static ArrayList<Socket> socketA=new ArrayList<Socket>();
	private static ServerSocket ss1=null;
	public static void setServerSocket(ServerSocket ss){
		ss1=ss;		
	}
	public static ServerSocket getServerSocket(){
		return ss1;
	}
	public static void setASocketList(Socket s){
		socketA.add(s);
	}
	public static ArrayList<Socket> getASocketList(){
		return socketA;
	}

}
