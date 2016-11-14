package edu.uniandes.cct.serviceRequest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import edu.uniandes.cct.serviceRequest.VO.SupportRequestVO;


public class ServiceDeskManager {

	private static AsyncProcessing asyncProcessing = null;
	private static QueueManager taskQueue = null;
	
	public ServiceDeskManager()
	{
		asyncProcessing = new AsyncProcessing();
		taskQueue = new QueueManager();
	}
	
	public String CreateRequest(int id, String nombre, String descripcion, String cargo, String area){
		final ExecutorService service;
        final Future<SupportRequestVO>  task;     
        String value = "";
        service = Executors.newFixedThreadPool(1);        
        task    = service.submit(new SyncThread(id, nombre,descripcion,cargo,area));
        
        try {

            final SupportRequestVO sr;
            sr = task.get(); // this raises ExecutionException if thread dies
            value = sr.getTicketNumber();
            ServiceDeskManager.taskQueue.addRequest(sr);
        } catch(final InterruptedException ex) {
            ex.printStackTrace();
        } catch(final ExecutionException ex) {
            ex.printStackTrace();
        }
        service.shutdownNow();
        return value;
	}
	
	public void StartAsyncProcessing()
	{
        ServiceDeskManager.asyncProcessing.setTaskQueue(taskQueue);
        ServiceDeskManager.asyncProcessing.createThreadPool();
	}
}

