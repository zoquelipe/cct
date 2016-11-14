package edu.uniandes.cct.serviceRequest.VO;

public class ClientVO {

	private String id;
	private String name;
	private String cargo;
	private String area;
	
	public String getId() {
		return id;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ClientVO(String id, String name, String cargo, String area) {
		super();
		this.id = id;
		this.name = name;
		this.cargo = cargo;
		this.area = area;
	}
}
