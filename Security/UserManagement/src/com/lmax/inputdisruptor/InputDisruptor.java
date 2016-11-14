package com.lmax.inputdisruptor;

import com.lmax.utils.CircularQueueManager;

public class InputDisruptor {
	
	private static CircularQueueManager inputQueue;
	private static InputAsyncProcessing asyncProcessing = null;
	
	public static void startProcessing() throws Exception
	{
		inputQueue = new CircularQueueManager();
		asyncProcessing =  new InputAsyncProcessing();
		asyncProcessing.setEventQueue(inputQueue);
		asyncProcessing.createThreadPool();
	}
	
	public static void recieveEvent(String idList) throws Exception
	{
		InputReciever.recieveEvent(inputQueue, idList);
	}
}
