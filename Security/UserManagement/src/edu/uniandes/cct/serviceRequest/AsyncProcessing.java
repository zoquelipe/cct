package edu.uniandes.cct.serviceRequest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncProcessing{

    private QueueManager taskQueue;
    public QueueManager getTaskQueue() {
        return taskQueue;
    }

    public void setTaskQueue(QueueManager taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void createThreadPool() {

        ExecutorService ex = Executors.newFixedThreadPool(10);
        AsyncThread t;
        for (int i = 0; i < 10; i++) {
            t = new AsyncThread();
            t.setTaskQueue(taskQueue);
            ex.execute(t);
        }
        ex.shutdown();
    }
}
