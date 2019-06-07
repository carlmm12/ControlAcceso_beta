package co.com.samtel.ControlAccesos.controller;

import java.util.List;

import co.com.samtel.ControlAccesos.entities.ResumenMensual;
import co.com.samtel.ControlAccesos.service.IServiceCodigoUsuario;
import co.com.samtel.ControlAccesos.service.IServiceControlAccesoOrd;
import co.com.samtel.ControlAccesos.service.IServiceResumenMensual;

public interface IResumenMensualController {
	
	IServiceResumenMensual getResumenMensualServices();	
	IServiceControlAccesoOrd getControlAccesoOrdService();
	IServiceCodigoUsuario getCodigoUsuarioService();
	void resumenMenRegisterR(int mes,int year , int diaI , int diaF);
	void resumenMenRegisterR1(int diaI , int diaF);
	List<ResumenMensual> ResumenEntity(int tipoAlerta, int porcentaje);
	

}
