package co.com.samtel.ControlAccesos.repository;


import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.samtel.ControlAccesos.entities.ControlAccesosOrd;

@Repository
public interface IcontrolAcessoOrd  extends JpaRepository<ControlAccesosOrd, Integer>{

	/*
	 * metodo que me permite traer todo el conteo de los registros en la tabla
	 */
	
	@Query(value="SELECT count(*) FROM tblcontrol_accesos_ord", nativeQuery = true )
	int countOrd();
	 
	/*
	 * query que me permite traer el control de acceso de acuerdo a un a fecha y usuario determinado
	 */
	
	@Query(value="SELECT * FROM tblcontrol_accesos_ord WHERE fecha = :fecha AND tblcodigo_usuarios_codigo = :id", nativeQuery = true )
	ControlAccesosOrd findControlAcc(@Param("fecha") Date fecha, @Param("id") int id  );
	
	/*
	 * metodo que me trae todos los controles de acceso de acuerdo a una fecha y un usario determinado
	 */
	@Query(value="SELECT * FROM tblcontrol_accesos_ord WHERE fecha = :fecha AND tblcodigo_usuarios_codigo = :id", nativeQuery = true )
	List<ControlAccesosOrd> findAllControlAcc(@Param("fecha") Date fecha, @Param("id") int id  );
	
	
	/*
	 * metodo para traer todas las fechas 
	 */
	
	@Query(value="select DATE(fecha) AS fechas FROM tblcontrol_accesos_ord GROUP BY fechas", nativeQuery = true )
	public List<String> countDate();
	
	/*
	 * Metodo que me trae todos los usuarios que se registraron en la fecha determinada 
	 */
	@Query(value="SELECT tblcodigo_usuarios_codigo FROM tblcontrol_accesos_ord WHERE DATE(fecha) = :fecha GROUP BY tblcodigo_usuarios_codigo", nativeQuery = true )
	public List<Integer> usersDate(@Param("fecha") String fecha);
	
	
	/*
	 * Metodo que me trae todos los registros que se realizdo de acuerdo al usuario y a la fecha determinada
	 */
	@Query(value="SELECT * FROM tblcontrol_accesos_ord WHERE DATE(fecha) = :fecha AND tblcodigo_usuarios_codigo = :id ORDER BY fecha asc", nativeQuery = true )
	public List<ControlAccesosOrd> registroUsers(@Param("fecha") String fecha , @Param("id") int codigo );
	
	/*
	 * Metodo que me trae la hora de entrada y salida adem�s del tiempo laborado por fecha y  usuario especifico
	 */
	@Query(value="SELECT MIN(TIME(fecha)) AS entrada, max(TIME(fecha)) AS salida, TIMEDIFF(MAX(fecha), MIN(fecha)) AS tiempo  FROM tblcontrol_accesos_ord  WHERE DAte(fecha) = :fecha AND tblcodigo_usuarios_codigo = :codigo ", nativeQuery = true )
	public List<Object[]> controlDia(@Param("fecha") String fecha , @Param("codigo") int codigo );
	
	@Query(value="SELECT MIN(TIME(fecha)) AS entrada, max(TIME(fecha)) AS salida, TIMEDIFF(TIMEDIFF(MAX(fecha), MIN(fecha)) , '01:00:00') AS tiempo  FROM tblcontrol_accesos_ord  WHERE DAte(fecha) = :fecha AND tblcodigo_usuarios_codigo = :codigo", nativeQuery = true )
	public List<Object[]> controlDias(@Param("fecha") String fecha , @Param("codigo") int codigo );
	
	/*
	 * query que me trae una lista de fechas de acuerdo a el rango de busqueda  (mes, dia incial ,dia final)
	 * 
	 */
	@Query(value="SELECT   DATE(fecha) AS fechas FROM tblcontrol_accesos_ord  WHERE MONTH(DATE(fecha)) = :mes  AND YEAR(DATE(fecha)) = :year AND DAY(DATE(fecha))  BETWEEN  :diaI AND  :diaF  GROUP BY fechas;", nativeQuery = true )
	public List<String> controlDiasR(@Param("mes") int mes ,@Param("year") int year , @Param("diaI") int diaI ,@Param("diaF") int diaF );
	
	/*
	 * query que me trae una lista de fechas de acuerdo a el rango de busqueda  ( dia incial ,dia final)
	 */
	@Query(value="SELECT   DATE(fecha) AS fechas FROM tblcontrol_accesos_ord  WHERE DAY(DATE(fecha))  BETWEEN  :diaI AND  :diaF  GROUP BY fechas;", nativeQuery = true )
	public List<String> controlDiasR1( @Param("diaI") int diaI ,@Param("diaF") int diaF );
	
	/*
	 * Metdo que trae todos los a�os que estan en la tabla tblCOntrolAccesoOrd
	 */
	
	@Query(value="SELECT YEAR(DATE(fecha)) AS anio FROM tblcontrol_accesos_ord GROUP BY anio", nativeQuery = true )
	public List<Integer> findYear();
	
	/*
	 * Metodo que me trae todos los meses que estan registrado en la tblControlAccesoOrd de acuerdo al a�o
	 */
	
	@Query(value="SELECT MONTH(DATE(fecha)) AS mes FROM tblcontrol_accesos_ord WHERE YEAR(DATE(fecha)) = :year  GROUP BY mes", nativeQuery = true )
	public List<Integer> findMes(@Param("year") int year );
	
	
	/*
	 * Metodo que me trea todos los dias que estan registrados en la tblcontrolaccesoOrd de acuerdo al a�o y el mes especificado
	 */
	
	@Query(value="SELECT DAY(DATE(fecha)) AS dia FROM tblcontrol_accesos_ord WHERE YEAR(DATE(fecha)) = :year AND  MONTH(DATE(fecha)) = :mes   GROUP BY dia; ", nativeQuery = true )
	public List<Integer> findDay(@Param("year") int year, @Param("mes") int mes  );
	
	/**
	 * metodo que se encarga de consultar el numero de registro que tiene un usuario en un dia
	 * @param fecha
	 * @param codigoUsuario
	 * @return me retorna el numero de registros por dia 
	 */
	@Query(value="SELECT count(distinct tblcontrol_accesos_ord.fecha) as num_registros FROM tblcontrol_accesos_ord WHERE tblcodigo_usuarios_codigo = :codigoUsuario  and DATE(fecha) = :fecha ", nativeQuery = true )
	public Integer findNumRegistros(@Param("codigoUsuario") int codigoUsuario, @Param("fecha") String fecha  );
	
	/**
	 * este metodo me permite traer la diferencia entre dos fechas en horas 
	 * @param inicio
	 * @param fin
	 * @return retorna la diferencia entre las horas inicio y fin  
	 */
	@Query(value="SELECT TIMEDIFF(:fin, :inicio) AS tiempo", nativeQuery = true )
	public Time findDifBetweenHours(@Param("inicio") Date inicio, @Param("fin") Date fin);
	
}
