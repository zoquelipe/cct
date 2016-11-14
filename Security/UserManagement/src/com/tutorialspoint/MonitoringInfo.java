package com.tutorialspoint;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "monitoringinfo")
public class MonitoringInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int memoryAvailable = 0;
	private int coreAvailable = 0;
	private String DBStatus="";
	
	public int getCoreAvailable() {
		return coreAvailable;
	}

	@XmlElement
	public void setCoreAvailable(int coreAvailable) {
		this.coreAvailable = coreAvailable;
	}

	public String getDBStatus() {
		return DBStatus;
	}
	
	@XmlElement
	public void setDBStatus(String dBStatus) {
		DBStatus = dBStatus;
	}

	public int getMemoryAvailable() {
		return memoryAvailable;
	}

	@XmlElement
	public void setMemoryAvailable(int memoryAvailable) {
		this.memoryAvailable = memoryAvailable;
	}
	

}
