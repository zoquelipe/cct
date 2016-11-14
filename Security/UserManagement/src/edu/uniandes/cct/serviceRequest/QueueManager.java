package edu.uniandes.cct.serviceRequest;

import java.util.concurrent.LinkedBlockingQueue;

import edu.uniandes.cct.serviceRequest.VO.SupportRequestVO;

public class QueueManager {
	
    private static LinkedBlockingQueue<SupportRequestVO> taskQueue = null;

    public QueueManager() {
        taskQueue = new LinkedBlockingQueue<SupportRequestVO>();
    }

    public void addRequest(SupportRequestVO request){
        taskQueue.add(request);
    }
    
    public SupportRequestVO getRequest() throws InterruptedException{
        return taskQueue.take();
    }

    public static LinkedBlockingQueue<SupportRequestVO> getTaskQueue() {
        return taskQueue;
    }

    public static void setTaskQueue(LinkedBlockingQueue<SupportRequestVO> taskQueue) {
    	QueueManager.taskQueue = taskQueue;
    }
    
    public boolean isEmpty(){
    	return taskQueue.isEmpty();
    }

}
