package co.com.samtel.ControlAccesos.repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.samtel.ControlAccesos.entities.ControlRegistro;

@Repository
public interface IControlRegistro extends JpaRepository<ControlRegistro, Integer> {
	
	/**
	 * Esta consulta me suma dos fechas 
	 * @param horaUno
	 * @param horaDos
	 * @return el total de las horas sumadas 
	 */
	@Query(value="SELECT ADDTIME(:horaUno, :horaDos);", nativeQuery = true )
	public Time findAddBetweenHours(@Param("horaUno") Time horaUno, @Param("horaDos") Time horaDos);
	
	/**
	 * este metodo tiene como funcion consultar los registros de la tabla tblcontrol_Registros dependiendo la fecha
	 * @param mes
	 * @param year
	 * @param diaI
	 * @param diaF
	 * @return
	 */
	@Query(value = "FROM ControlRegistro r INNER JOIN fetch r.controlDiario WHERE MONTH(DATE(r.controlDiario.fecha)) = :mes AND YEAR(DATE(r.controlDiario.fecha)) = :year AND DAY(DATE(r.controlDiario.fecha))  BETWEEN  :diaI AND  :diaF")
	public List<ControlRegistro> findAllControlRegistros(@Param("mes") int mes, @Param("year") int year,  @Param("diaI") int diaI,  @Param("diaF") int diaF);
	
	
}
