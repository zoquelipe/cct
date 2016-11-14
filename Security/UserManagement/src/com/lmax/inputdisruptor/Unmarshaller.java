package com.lmax.inputdisruptor;

import java.util.ArrayList;
import com.lmax.utils.UniqueIdGenerator;
import com.lmax.vo.LmaxEvent;
import com.lmax.vo.MarshalledEvent;

public class Unmarshaller {
	
	public static LmaxEvent unmarshallEvent(MarshalledEvent event) throws Exception
	{
		int newId = UniqueIdGenerator.getNewId();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		String ids = event.getIdList();
		String[] ides = ids.split(";");
		for(int i=0; i < ides.length; i++)
		{
			idList.add(Integer.parseInt(ides[i]));
		}
		LmaxEvent newEvent =  new LmaxEvent(newId,-1,idList, false);
		return newEvent;
	}
	
	public static LmaxEvent[] unmarshallEvents(MarshalledEvent[] events) throws Exception
	{
		LmaxEvent[] newEvents = new LmaxEvent[5];
		for(int i = 0; i < events.length  ; i++)
		{
			int newId = UniqueIdGenerator.getNewId();
			ArrayList<Integer> idList = new ArrayList<Integer>();
			MarshalledEvent event = events[i];
			String ids = event.getIdList();
			int id = event.getId();
			String[] ides = ids.split(";");
			for(int j=0; j < ides.length; j++)
			{
				if(!ides[j].isEmpty())
					idList.add(Integer.parseInt(ides[j]));
			}
			LmaxEvent newEvent =  new LmaxEvent(newId,id,idList, false);
			newEvents[i] = newEvent;
		}
		return newEvents;
	}
}
