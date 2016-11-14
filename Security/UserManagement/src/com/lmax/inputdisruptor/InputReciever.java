package com.lmax.inputdisruptor;

import com.lmax.utils.CircularQueueManager;
import com.lmax.vo.MarshalledEvent;

public class InputReciever {
	public static CircularQueueManager recieveEvent(CircularQueueManager inputQueue, String idList) throws Exception
	{	
		MarshalledEvent event = new MarshalledEvent(idList);
		inputQueue.addEvent(event);
		//System.out.println("Recieved event with IDs :" + idList);
		return inputQueue;
	}
}
