package co.com.samtel.ControlAccesos.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.samtel.ControlAccesos.entities.ControlDiario;
import co.com.samtel.ControlAccesos.entities.ResumenMensual;

@Repository
public interface IResumenMensual extends JpaRepository<ResumenMensual, Integer> {

	
	/*
	 * Metodo que me permite buscar la cantidad de alertas que existe de acuerdo al tipo de usuario y el tipo de alerta que se presente.
	 */
	@Query(value="SELECT  nro_alertas FROM tblresumenes_mensual WHERE codigo = :codigo AND MONTH(DATE(fecha)) = :mes AND YEAR(DATE(fecha)) = :year AND tipo_alerta = :tp", nativeQuery=true)
	Integer findnumAlertas ( @Param("codigo") int codigo, @Param("mes") int mes,@Param("year") int year ,@Param("tp") int tipoAlerta);
	
	
	/*
	 * metodo que me permite actualizar los datos almacenados por cada alerta que se presente.
	 */
	@Modifying
	@Query(value="UPDATE tblresumenes_mensual SET  nro_alertas= :numalertas, porcentaje_alertas= :porcentaje WHERE codigo= :codigo AND MONTH(DATE(fecha)) = :mes AND tipo_alerta = :tp", nativeQuery=true)
	@Transactional
	void updateEntity ( @Param("numalertas") int numalertas, @Param("porcentaje") int porcentaje,@Param("codigo") int codigo,  @Param("mes") int mes, @Param("tp") int tp);
	
	/*
	 * Metodo que me permite eliminar lso registros anteriores 
	 */
	
	@Modifying
	@Query(value="DELETE FROM tblresumenes_mensual WHERE   MONTH(DATE(fecha)) = :mes AND YEAR(DATE(fecha)) = :year", nativeQuery=true)
	@Transactional
	void deleteRegistros ( @Param("mes") int mes, @Param("year") int year);
	
	/*
	 *  Metodo que me trae todos la entidades de ResumenMensual de acuerdo al tipo de alerta.
	 */
	@Query(value= "SELECT * FROM tblresumenes_mensual as r WHERE r.tipo_alerta = :tipoAlerta and r.porcentaje_alertas > :porcentajeAlertas ORDER BY r.fecha, r.codigo", nativeQuery=true)
	List<ResumenMensual> findbyAlertType ( @Param("tipoAlerta") int tipoAlerta , @Param("porcentajeAlertas") int porcentajeAlertas );
	
	
}
