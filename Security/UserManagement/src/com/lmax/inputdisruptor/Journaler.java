package com.lmax.inputdisruptor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import com.lmax.vo.MarshalledEvent;

public class Journaler{
    private final static Logger logger = Logger.getLogger(Journaler.class);
	
	 public static void logEvent(MarshalledEvent event)
	 {
		 File file = new File("C:\\test\\journal.txt");
		 FileWriter writer;
		 try {
			 writer = new FileWriter(file, true);
		     PrintWriter printer = new PrintWriter(writer);
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		     printer.append(sdf.format(new Date()));
		     printer.append("-");
		     printer.append("id:" + event.getId());
			 printer.append("-");
		     printer.append(event.getIdList());
			 printer.append(";");
		     printer.append("\n");
		     printer.close();
		 } catch (IOException e) {
	            System.out.println("Journalerr failed, please contact system admin!");
	            logger.fatal("Esto es información:"+ "Journaler failed!");
		 }
	 }
	 
	 public static void logEvents(MarshalledEvent[] events)
	 {
		 File file = new File("C:\\test\\journal.txt");
		 FileWriter writer;
		 try {
			 writer = new FileWriter(file, true);
		     PrintWriter printer = new PrintWriter(writer);
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		     printer.append(sdf.format(new Date()));
		     printer.append("-");
		     for(int i=0;i < events.length; i++)
		     {
		    	 MarshalledEvent event = events[i];
		    	 if(event != null)
		    	 {
		    		 printer.append("id:" + event.getId());
		    		 printer.append("-");
		    		 printer.append(event.getIdList());
		    		 printer.append(";");
		    	 }
		     }
		     printer.append("\n");
		     printer.close();
		 } catch (IOException e) {
	            System.out.println("Journalerr failed, please contact system admin!");
	            logger.fatal("Esto es información:"+ "Journaler failed!");
		 }
	 }
}
