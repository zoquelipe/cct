package edu.uniandes.cct.serviceRequest;

import java.util.Date;
import java.util.concurrent.Callable;

import edu.uniandes.cct.serviceRequest.VO.SupportRequestVO;


public class SyncThread 
	implements Callable<SupportRequestVO> {
	
    private volatile String nombre;
    private volatile String descripcion;
    private volatile String cargo;
    private volatile String area;
	private volatile int id;
    
    public SyncThread(int id, String nombre, String descripcion, String cargo, String area) {
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.cargo=cargo;
        this.area=area;
        this.id=id;
    }

    public SupportRequestVO call() {
    	SupportRequestVO sr1 = new SupportRequestVO(new Date(), id, nombre,descripcion,cargo,area);
    	return sr1;
    }
}