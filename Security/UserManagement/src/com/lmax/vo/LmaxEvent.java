package com.lmax.vo;

import java.util.ArrayList;

public class LmaxEvent {

	private int id;
	private int parentID;
	private ArrayList<Integer> idList = new ArrayList<Integer>();
	private boolean processed;

	public LmaxEvent(int id, int parentID, ArrayList<Integer> idList, boolean processed) {
		super();
		this.id = id;
		this.parentID = parentID;
		this.idList = idList;
		this.processed = processed;
	}

	public ArrayList<Integer> getIdList() {
		return idList;
	}

	public void setIdList(ArrayList<Integer> idList) {
		this.idList = idList;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	
	
}