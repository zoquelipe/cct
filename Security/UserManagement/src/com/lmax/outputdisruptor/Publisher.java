package com.lmax.outputdisruptor;

import com.lmax.vo.MarshalledEvent;
import com.redis.Proyecto;

public class Publisher {
	public static void publishEvent(Object eventResult)throws Exception
	{
		// Call interface and then result
		Proyecto proyecto = (Proyecto) eventResult;
        System.out.println("Call interface and then result :" + proyecto.getValor());
	}
	public static void publishEvents(Object[] eventsResult)throws Exception
	{
		// Call interface and then result
		for(int i=0; i< eventsResult.length ; i++)
		{
			Object eventResult = eventsResult[i];
			MarshalledEvent event = (MarshalledEvent) eventResult;
			String[] ids = event.getIdList().split(";");
			for(int j=0 ; i < ids.length;i++)
			{
				Proyecto cargarProy = new Proyecto();
			    cargarProy = Proyecto.cargarProyecto(""+ids[j]);
			    System.out.println("Call interface and then result :" + cargarProy.getId());
//			    System.out.println("Call interface and then result :" + ids[j]);
			}
		}
	}
}
