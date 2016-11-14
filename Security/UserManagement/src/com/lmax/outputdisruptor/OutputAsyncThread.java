package com.lmax.outputdisruptor;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import com.lmax.utils.CircularQueueManager;
import com.lmax.vo.LmaxEvent;

public class OutputAsyncThread extends Thread {

    private CircularQueueManager outputQueue;
    private final static Logger logger = Logger.getLogger(OutputAsyncThread.class);
    
    public CircularQueueManager getEventQueue() {
        return outputQueue;
    }

    public void setEventQueue(CircularQueueManager outputQueue) {
        this.outputQueue = outputQueue;
    }

    public OutputAsyncThread() {
    	
    }

    public void run() {

    	boolean joinFlag = false;
        while (true) {

            try {
                //System.out.println("Async Thread running...");
                if(outputQueue != null)
                {
                	if(joinFlag)
                		pullOutputEvents();
                	else
                		pullOutputEvent();
                }
                else
                {
                	Thread.sleep(10);
                }
            } catch (Exception e) {

                System.out.println("Exception on output thread, please contact system admin!");
                logger.fatal("Esto es información:"+ "Exception on input thread, please contact system admin!");
            }
        }
    }

	private void pullOutputEvent() throws InterruptedException, Exception {
		Object eventResult = null;
		eventResult = (Object) outputQueue.takeEvent();
		if(eventResult != null)
			executeOutputEvent(eventResult);
	}
	
	private void pullOutputEvents() throws InterruptedException, Exception {
		ArrayList<?> events = null;
		boolean atLeastOne= false;
		int maxEvents = 5;
		LmaxEvent[] eventList = new LmaxEvent[maxEvents]; 
		events = (ArrayList<?>) outputQueue.pollEvent();
		if(events != null)
		{
			for(int i = 0; i < events.size(); i++)
			{
				eventList[i] = (LmaxEvent)events.get(i);
			}
			atLeastOne = true;
		}
	    else
	    {
	    	Thread.sleep(10);
	    }
		if(atLeastOne)
			executeOutputEvents(eventList);
	}
    
    public void executeOutputEvent(Object eventResult)throws Exception{
    	Object newEventResult = Marshaller.marshallEvent(eventResult);
    	Publisher.publishEvent(newEventResult); 
    }
    
    public void executeOutputEvents(Object[] eventList)throws Exception{
    	Object[] newEventsResult = Marshaller.marshallEvents(eventList);
    	Publisher.publishEvents(newEventsResult);

		//System.gc();
	 }
}
