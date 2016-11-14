package com.lmax.simulator;

import org.apache.log4j.Logger;
import com.lmax.inputdisruptor.InputDisruptor;
import com.lmax.outputdisruptor.OutputDisruptor;

public class LmaxSimulator {
    private final static Logger logger = Logger.getLogger(LmaxSimulator.class);
	public static void main(String[] args) {
		System.gc();
		try
		{
			InputDisruptor.startProcessing();
			OutputDisruptor.startProcessing();
			int retries = 100;
			int retry = 0;
			int totalRequests = 11;
			while(retry < retries)
			{
				String idList = "";
				for(int i= 1; i < totalRequests; i++)
				{
					if(i == totalRequests-1)
						idList += (i+(totalRequests*retry)) ;
					else
						idList += (i+(totalRequests*retry)) + ";";
				}
				//System.out.println("Recieved event with IDs :" + idList);
				InputDisruptor.recieveEvent(idList);
				retry++;
			}
		}
		catch(Exception e)
		{
            System.out.println("Lmax simulator failed, please contact system admin!");
            logger.fatal("Esto es información:"+ "Lmax simulator failed!");
		}
	}
}
