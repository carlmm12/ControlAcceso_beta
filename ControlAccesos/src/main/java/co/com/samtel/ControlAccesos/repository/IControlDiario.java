package co.com.samtel.ControlAccesos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.samtel.ControlAccesos.entities.ControlDiario;

@Repository
public interface IControlDiario extends JpaRepository<ControlDiario, Integer> {

	
	/*
	 * Metodo que me trae todos los datos de la tabla tblcontrol_diario de acuerdo a la fecha y el codigo de usuario
	 */
	@Query(value="SELECT * FROM tblcontrol_diario  WHERE fecha = :fecha AND codigo_usuario = :codigo", nativeQuery=true)
	List<ControlDiario> findControlD (@Param("fecha") Date fecha, @Param("codigo") int codigo);
	
	/*
	 * Metodo que hace un conteo de la cantidad de data que existe en la tabla de tblcontrol_diario
	 */
	
	@Query(value="SELECT count(*) FROM tblcontrol_diario",nativeQuery=true)
	int countC();
	
	/*
	 * Metodo que me consulta todos los datos del control diario
	 */
	@Query(value="SELECT cd FROM ControlDiario cd ")
	List<ControlDiario> finndAll();
	
	
	@Query(value="SELECT   * FROM tblcontrol_diario WHERE MONTH(DATE(fecha)) = :mes AND YEAR(DATE(fecha)) = :year AND DAY(DATE(fecha))  BETWEEN  :diaI AND  :diaF ",nativeQuery=true)
	List<ControlDiario> findAllRange(@Param("mes") int mes, @Param("year") int year, @Param("diaI") int diaI, @Param("diaF") int diaF);
	
	@Query(value="select * from tblcontrol_diario where DATE(fecha) = :fecha and codigo_usuario = :codigoUsuario" ,nativeQuery=true)
	ControlDiario findByDateAndCode(@Param("fecha") String fecha, @Param("codigoUsuario") int codigoUsuario);
	
}
