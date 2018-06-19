package info.server.dto;

public class MobileConfigDTO {
	private String gatewayName,comPortNo,compName,modelNo,mobileNo;
	public String getGatewayName() {
		return gatewayName;
	}

	public String getComPortNo() {
		return comPortNo;
	}

	public String getCompName() {
		return compName;
	}

	public String getModelNo() {
		return modelNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}

	public void setComPortNo(String comPortNo) {
		this.comPortNo = comPortNo;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
}
