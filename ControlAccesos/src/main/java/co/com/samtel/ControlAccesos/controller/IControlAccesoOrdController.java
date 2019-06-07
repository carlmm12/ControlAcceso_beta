package co.com.samtel.ControlAccesos.controller;

import co.com.samtel.ControlAccesos.service.IServiceCodigoUsuario;
import co.com.samtel.ControlAccesos.service.IServiceControlAcceso;
import co.com.samtel.ControlAccesos.service.IServiceControlAccesoOrd;

public interface IControlAccesoOrdController {

	
	IServiceControlAccesoOrd getControlAccesoOrdService();
	IServiceControlAcceso getControlAccesoService();
	IServiceCodigoUsuario getCodigoUsuarioService();
	void register();
}
