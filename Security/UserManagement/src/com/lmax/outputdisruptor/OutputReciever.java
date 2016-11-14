package com.lmax.outputdisruptor;

import java.util.ArrayList;

import com.lmax.utils.CircularQueueManager;

public class OutputReciever {
	public static CircularQueueManager recieveEventResult(CircularQueueManager outputQueue, Object eventResult) throws Exception
	{	
		outputQueue.addEvent(eventResult);
		return outputQueue;
	}
	
	public static CircularQueueManager recieveEventsResult(CircularQueueManager outputQueue, ArrayList<Object>  joinedEventResultList) throws Exception
	{	
		outputQueue.addEvent(joinedEventResultList);
		return outputQueue;
	}
}
