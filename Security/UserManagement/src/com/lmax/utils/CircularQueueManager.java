package com.lmax.utils;

import java.util.concurrent.LinkedBlockingQueue;

public class CircularQueueManager {
	private LinkedBlockingQueue<Object> eventQueue = null;

    public CircularQueueManager() {
    	eventQueue = new LinkedBlockingQueue<Object>();
    }

    public void addEvent(Object request){
    	eventQueue.add(request);
    }
    
    public Object takeEvent() throws InterruptedException{
        return eventQueue.take();
    }
    public Object pollEvent() throws InterruptedException{
        return eventQueue.poll();
    }

    public LinkedBlockingQueue<Object> getEventQueue() {
        return eventQueue;
    }

    public void setTaskQueue(LinkedBlockingQueue<Object> eventQueue) {
    	this.eventQueue = eventQueue;
    }
    
    public boolean isEmpty(){
    	return eventQueue.isEmpty();
    }

}
