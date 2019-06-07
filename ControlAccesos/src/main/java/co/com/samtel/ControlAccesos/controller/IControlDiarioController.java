package co.com.samtel.ControlAccesos.controller;


import java.util.List;

import co.com.samtel.ControlAccesos.dto.ControlDiarioAlertaDto;
import co.com.samtel.ControlAccesos.service.IServiceCodigoUsuario;
import co.com.samtel.ControlAccesos.service.IServiceControlAccesoOrd;
import co.com.samtel.ControlAccesos.service.IServiceControlDiario;

public interface IControlDiarioController {

	
	IServiceControlDiario getControlDiarioService();
	IServiceControlAccesoOrd getControlAccesoOrdService();
	IServiceCodigoUsuario getCodigoUsuarioService();
	void alarmaControlDiario();
	
    List<ControlDiarioAlertaDto> convertEntityMenorH(int mes, int year, int diaI,  int diaF);
    List<ControlDiarioAlertaDto> convertEntityMayorH(int mes, int year, int diaI,  int diaF);
    List<ControlDiarioAlertaDto> convertEntityHoraLlegada(int mes, int year, int diaI,  int diaF);
}
