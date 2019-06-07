package co.com.samtel.ControlAccesos.controller;

import co.com.samtel.ControlAccesos.service.IServiceCodigoUsuario;
import co.com.samtel.ControlAccesos.service.IServiceControlAccesoOrd;
import co.com.samtel.ControlAccesos.service.IServiceControlDiario;
import co.com.samtel.ControlAccesos.service.IServiceControlRegistro;

public interface IControlRegistroController {
	
	
	IServiceControlRegistro getControlRegistroService();
		
	IServiceControlDiario getControlDiarioService();
	IServiceControlAccesoOrd getControlAccesoOrdService();
	IServiceCodigoUsuario getCodigoUsuarioService();
	void controlRegistros();
	
	
}
