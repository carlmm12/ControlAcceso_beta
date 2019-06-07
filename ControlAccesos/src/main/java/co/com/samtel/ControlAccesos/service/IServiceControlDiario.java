package co.com.samtel.ControlAccesos.service;

import java.util.List;

import co.com.samtel.ControlAccesos.entities.ControlDiario;

public interface IServiceControlDiario extends IServiceRepo<ControlDiario,Integer> {
	
	int countC();
	List<ControlDiario> findAllRange( int mes, int year, int diaI,  int diaF);
	ControlDiario findByDateAndCode( String fecha, int codigoUsuario);

}
