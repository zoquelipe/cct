package com.lmax.inputdisruptor;

import com.lmax.utils.CircularQueueManager;
import com.lmax.vo.LmaxEvent;
import com.lmax.vo.MarshalledEvent;
import org.apache.log4j.Logger;

public class InputAsyncThread extends Thread {

    private CircularQueueManager inputQueue;
    private final static Logger logger = Logger.getLogger(InputAsyncThread.class);

    public CircularQueueManager getEventQueue() {
        return inputQueue;
    }

    public void setEventQueue(CircularQueueManager inputQueue) {
        this.inputQueue = inputQueue;
    }

    public InputAsyncThread() {
    	
    }

    public void run() {
    	boolean joinFlag = false;
        while (true) {

            try {
                //System.out.println("Async Thread running...");
                if(inputQueue != null)
                {
                	if(joinFlag)
                		pullInputEvents();
                	else
                		pullInputEvent();
                }
                else
                {
                	Thread.sleep(10);
                }
            } catch (Exception e) {
    			e.printStackTrace();
                System.out.println("Exception on input thread, please contact system admin!");
                logger.fatal("Esto es información:"+ "Exception on input thread, please contact system admin!");
            }
        }
    }
    private void pullInputEvent() throws InterruptedException,Exception {
    	MarshalledEvent event = null;
		event = (MarshalledEvent) inputQueue.takeEvent();
		if(event != null)
			executeInputEvent(event);
	}
    
	private void pullInputEvents() throws InterruptedException,Exception {
		MarshalledEvent event = null;
		boolean atLeastOne= false;
		int count = 0;
		int maxEvents = 5;
		MarshalledEvent[] eventList = new MarshalledEvent[maxEvents]; 
		while(count != maxEvents)
		{
			event = (MarshalledEvent) inputQueue.pollEvent();
			if(event != null)
			{
				eventList[count] = event;
				atLeastOne = true;
				count++;
			}
            else
            {
            	Thread.sleep(10);
            }
		}
		if(atLeastOne)
			executeInputEvents(eventList);
	}
    
    public void executeInputEvent(MarshalledEvent event) throws Exception{
    	Journaler.logEvent(event);
		LmaxEvent newEvent = Unmarshaller.unmarshallEvent(event);
		Replicator.replicateEvent(newEvent);
	 }
    
    public void executeInputEvents(MarshalledEvent[] events) throws Exception{
    	Journaler.logEvents(events);
		LmaxEvent[] newEvents = Unmarshaller.unmarshallEvents(events);
		Replicator.replicateEvents(newEvents);
	 }
}
