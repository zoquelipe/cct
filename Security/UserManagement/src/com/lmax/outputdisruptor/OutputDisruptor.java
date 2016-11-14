package com.lmax.outputdisruptor;

import java.util.ArrayList;

import com.lmax.outputdisruptor.OutputAsyncProcessing;
import com.lmax.utils.CircularQueueManager;

public class OutputDisruptor {
	
	private static CircularQueueManager outputQueue;
	private static OutputAsyncProcessing asyncProcessing = null;
	
	public static void startProcessing() throws Exception
	{
		outputQueue = new CircularQueueManager();
		asyncProcessing =  new OutputAsyncProcessing();
		asyncProcessing.setEventQueue(outputQueue);
		asyncProcessing.createThreadPool();
	}
	
	public static void recieveEventResult(Object eventResult) throws Exception
	{
		OutputReciever.recieveEventResult(outputQueue, eventResult);
	}
	
	public static void recieveEventsResult(ArrayList<Object> joinedEventResultList) throws Exception
	{
		OutputReciever.recieveEventsResult(outputQueue, joinedEventResultList);
	}
}
