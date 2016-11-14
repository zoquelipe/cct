package com.lmax.vo;

import com.lmax.utils.UniqueIdGenerator;

public class MarshalledEvent {
	private int id;
	private String idList = new String();

	public MarshalledEvent(String idList) {
		super();
		int id = UniqueIdGenerator.getNewId();
		this.setId(id);
		this.idList = idList;
	}
	
	public MarshalledEvent(int id, String idList) {
		super();
		this.id = id;
		this.idList = idList;
	}
	
	public String getIdList() {
		return idList;
	}

	public void setIdList(String idList) {
		this.idList = idList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
