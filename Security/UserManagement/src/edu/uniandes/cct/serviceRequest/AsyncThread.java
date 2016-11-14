package edu.uniandes.cct.serviceRequest;


import edu.uniandes.cct.serviceRequest.VO.ClientVO;
import edu.uniandes.cct.serviceRequest.VO.SupportRequestVO;
import edu.uniandes.cct.serviceRequest.VO.SupportResponsibleVO;

public class AsyncThread extends Thread {

    private QueueManager taskQueue;

    public QueueManager getTaskQueue() {
        return taskQueue;
    }

    public void setTaskQueue(QueueManager taskQueue) {
        this.taskQueue = taskQueue;
    }

    public AsyncThread() {
    	
    }

    public void run() {

    	SupportRequestVO task = null;
        while (true) {

            try {
                //System.out.println("Async Thread running...");
                if(taskQueue != null)
                {
                	task = taskQueue.getRequest();
                	if(task != null)
                		execute(task);
                }
                else
                {
                	Thread.sleep(4000);
                }
            } catch (Exception e) {

                System.out.println("Exception on thread, please contact system admin!");
            }
        }
    }
    
    public void execute(SupportRequestVO task){
    	
       //System.out.println("Execute Asyn...");
       System.out.println("Asynchronous processing of ticket :" + task.getTicketNumber() + "-" + task.getCreationText());
       try {
    	   //Simulando el procesamiento de SR.
    	   
    	   String id = task.getId()+"";
    	   String name = task.getNombre();
    	   String description = task.getDescription();
    	   String job = task.getCargo();
    	   String area = task.getArea();
    	   
    	   ClientVO client = new ClientVO(id,name,job,area);
    	   SupportResponsibleVO responsible = new SupportResponsibleVO("Support Agent");
    	   task.setClient(client);
    	   task.setAssignedTo(responsible);
    	   task.setDescription(description);
    	   task.save();
    	   
       } catch (Exception e) {
    	   System.err.println(e);
    	   System.out.println("esta fallando antes de llamar la DB");
    	   
       }
       
    }
}
