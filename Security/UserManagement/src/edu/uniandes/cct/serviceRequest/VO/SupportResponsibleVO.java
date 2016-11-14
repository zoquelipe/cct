package edu.uniandes.cct.serviceRequest.VO;

public class SupportResponsibleVO {
	
	String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public SupportResponsibleVO(String myName)
	{
		super();
		name = myName;
	}
}
