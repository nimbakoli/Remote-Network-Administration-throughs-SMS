package info.server.MainFrame;

import info.server.AdminServer.Server;
import info.server.log.SystemLogger;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCTemplate {
	public static Connection getConnection(){
		Connection conn=null;
		try{
			//JDBCODBC Connection Stream 
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn=DriverManager.getConnection("jdbc:odbc:RDSN");
			System.out.println("successfully connect to Database");
		}catch(Exception e){
			try{
			conn.close();
			}catch(Exception ee){}
			SystemLogger.passException(e);
		}
		return conn;
	}
	public static void main(String args[]) {
		JDBCTemplate ob=new JDBCTemplate();
		System.out.println(JDBCTemplate.getConnection());
	}
}
