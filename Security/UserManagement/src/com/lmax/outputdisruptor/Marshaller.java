package com.lmax.outputdisruptor;

import java.util.ArrayList;

import com.lmax.vo.LmaxEvent;
import com.lmax.vo.MarshalledEvent;

public class Marshaller {
	public static Object marshallEvent(Object eventResult)throws Exception
	{
		return eventResult;
	}
	public static Object[] marshallEvents(Object[] eventResult)throws Exception
	{
		Object[] marshalledEvents =  new Object[eventResult.length];
		LmaxEvent[] events = (LmaxEvent[]) eventResult;
		for(int i=0; i < events.length; i++)
		{
			LmaxEvent event = events[i];
			int id = event.getID();
			ArrayList<Integer> idList = event.getIdList();
			String idListString = "";
			for(int j=0; j < idList.size() ; j++)
				idListString += idList.get(j) + ";";
			MarshalledEvent marshalledEvent = new MarshalledEvent(id, idListString);
			marshalledEvents[i] = marshalledEvent;
		}
		return marshalledEvents;
	}

}
