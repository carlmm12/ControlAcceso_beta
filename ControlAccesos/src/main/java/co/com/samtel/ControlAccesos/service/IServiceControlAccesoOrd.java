package co.com.samtel.ControlAccesos.service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.samtel.ControlAccesos.dto.ControlDiarioDto;
import co.com.samtel.ControlAccesos.entities.ControlAccesosOrd;

public interface IServiceControlAccesoOrd extends IServiceRepo<ControlAccesosOrd,Integer> {

	int countOrd();
	void saveImpar(ControlAccesosOrd entity);
	List<String> countDate();
	List<Integer> usersDate( String fecha);
	List<ControlAccesosOrd> registroUsers(String fecha , int codigo );
	ControlDiarioDto controlDia(String fecha ,  int codigo );
	List<String> controlDiasR(int mes , int year, int diaI ,int diaF );
	List<String> controlDiasR1( int diaI ,int diaF );
	List<Integer> findYear();
	List<Integer> findMes( int year );
	List<Integer> findDay( int year, int mes );
	/**
	 * metodo que se encarga de consultar el numero de registro que tiene un usuario en un dia
	 * @param fecha
	 * @param codigoUsuario
	 * @return me retorna el numero de registros por dia 
	 */
	public Integer findNumRegistros(int codigoUsuario,  String fecha );
	
	/**
	 * este metodo me permite traer la diferencia entre dos fechas en horas 
	 * @param inicio
	 * @param fin
	 * @return retorna la diferencia entre las horas inicio y fin  
	 */
	public Time findDifBetweenHours(Date inicio,  Date fin);

	
	
}
