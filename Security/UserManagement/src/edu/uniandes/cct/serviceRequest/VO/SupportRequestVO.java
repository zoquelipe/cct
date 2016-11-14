package edu.uniandes.cct.serviceRequest.VO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.uniandes.cct.serviceRequest.DAO.SupportRequest;

public class SupportRequestVO {
	private String nombre;
	private String ticketNumber;
	private Date creationDate;
	private Date closeDate;
	private String category;
	private String priority;
	private String description;
	private double slaHours;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	private SupportResponsibleVO assignedTo;
	private ClientVO client;
	
	private String cargo;
	private String area;
	private int id;
	
	public SupportRequestVO(Date creaationDate,int id, String nombre, String descripcion, String cargo, String area) {
		super();
		this.nombre = nombre;
		this.creationDate = creaationDate;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		this.ticketNumber = sdf.format(this.creationDate);
		this.category = "";
		this.priority = "";
		this.description = descripcion;
		this.slaHours = 0;
		this.assignedTo = null;
		this.client = null;
		
		this.area=area;
		this.cargo=cargo;
		this.id=id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SupportRequestVO(String creationText, Date creaationDate, String type, String priority, String description,
			ArrayList<String> attachments, double slaHours, SupportResponsibleVO assignedTo, ClientVO client) {
		super();
		this.nombre = creationText;
		this.creationDate = creaationDate;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		this.ticketNumber = sdf.format(this.creationDate);
		this.category = type;
		this.priority = priority;
		this.description = description;
		this.slaHours = slaHours;
		this.assignedTo = assignedTo;
		this.client = client;
	}

	public String getCreationText() {
		return nombre;
	}
	public void setCreationText(String creationText) {
		this.nombre = creationText;
	}
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public Date getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getSlaHours() {
		return slaHours;
	}
	public void setSlaHours(double slaHours) {
		this.slaHours = slaHours;
	}
	public SupportResponsibleVO getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(SupportResponsibleVO assignedTo) {
		this.assignedTo = assignedTo;
	}
	public ClientVO getClient() {
		return client;
	}
	public void setClient(ClientVO client) {
		this.client = client;
	}
	public void save() {
		
        try {
    		SupportRequest dao = new SupportRequest();
			dao.readDataBase(this.ticketNumber, this.description);
		} catch (Exception e) {

		}
		
	}
	
}
