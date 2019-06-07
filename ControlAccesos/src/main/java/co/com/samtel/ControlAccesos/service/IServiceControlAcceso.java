package co.com.samtel.ControlAccesos.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.samtel.ControlAccesos.entities.ControlAcceso;

public interface IServiceControlAcceso extends IServiceRepo<ControlAcceso,Integer> {
  
	public List<ControlAcceso> findByMonth(int month);
	
	public int countByDay(int id, String fecha);
	public List<String> countDate();
	List<ControlAcceso> registroUsers(String fecha , int codigo );
	List<Integer> usersDate(@Param("date") String fecha);
	public Integer getNumberData();
	/**
	 * este metodo es el encargado de eliminarme toda la informacion de la tabla tblcontrol_accesos
	 */
	public Boolean deleteDataControlAccesos();
}
