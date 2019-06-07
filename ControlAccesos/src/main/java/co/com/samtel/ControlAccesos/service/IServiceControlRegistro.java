package co.com.samtel.ControlAccesos.service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.samtel.ControlAccesos.entities.ControlRegistro;

public interface IServiceControlRegistro extends IServiceRepo<ControlRegistro,Integer> {
	
	
	public Time findAddBetweenHours( Time horaUno,  Time horaDos);
	
	/**
	 * este metodo tiene como funcion consultar los registros de la tabla tblcontrol_Registros dependiendo la fecha
	 * @param mes
	 * @param year
	 * @param diaI
	 * @param diaF
	 * @return
	 */
	public List<ControlRegistro> findAllControlRegistros(@Param("mes") int mes, @Param("year") int year,  @Param("diaI") int diaI,  @Param("diaF") int diaF);
	

}
