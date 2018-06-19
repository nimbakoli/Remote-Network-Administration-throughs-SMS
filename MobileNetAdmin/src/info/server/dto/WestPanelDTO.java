package info.server.dto;

import info.server.MainFrame.WP;
public class WestPanelDTO {
	private static WP wp;
	public static void setWestPDTO(WP wp1){
		wp=wp1;
	}
	public static WP getWestPDTO(){
		return wp;
	}
}
