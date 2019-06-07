package co.com.samtel.ControlAccesos.service;

import java.util.List;

import co.com.samtel.ControlAccesos.entities.ResumenMensual;

public interface IServiceResumenMensual extends IServiceRepo<ResumenMensual, Integer> {

	
	Integer findnumAlertas (int codigo, int mes, int year, int tipoAlerta);
    void updateEntity(ResumenMensual entity);
    void deleteRegistros ( int mes, int year);
    List<ResumenMensual> findbyAlertType (int tipoAlerta,int porcentaje);
}
