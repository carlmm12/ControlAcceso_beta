package co.com.samtel.ControlAccesos.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.samtel.ControlAccesos.entities.ResumenMensual;
import co.com.samtel.ControlAccesos.repository.IResumenMensual;
import co.com.samtel.ControlAccesos.service.IServiceResumenMensual;
import co.com.samtel.ControlAccesos.util.convertDate;

@Service("ResumenMensualBean")
public class ResumenMensualImpl implements IServiceResumenMensual {

	@Autowired
	IResumenMensual rmen;

	@Override
	public void save(ResumenMensual entity) {
		try {
			rmen.save(entity);
		} catch (Exception e) {
			System.out.println("No se pudo registrar la entidad");
		}

	}

	@Override
	public Boolean saveAll(List<ResumenMensual> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ResumenMensual> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ResumenMensual> findAll() {
		// TODO Auto-generated method stub
		return rmen.findAll();
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(ResumenMensual entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer findnumAlertas(int codigo, int mes, int year, int tipoAlerta) {
		Integer dato = rmen.findnumAlertas(codigo, mes, year, tipoAlerta);
		System.out.println("ESTO ES LO QUE TRAE LA CONSULTA" + dato);
		if (dato == null || dato == 0) {
			dato = 0;
		}

		return dato;
	}

	@Override
	public void updateEntity(ResumenMensual entity) {

		
		try {
			
			// CONVIERTO LA FECHA DE TIPO COT A TIPO UTC CON FORMATO "YYYY-MM-DD" Y EXTRAIGO EL MES PARA LA ACTUALIZACI�N
			LocalDate ld = convertDate.converDateCot(entity.getFecha());
			System.out.println("Este es el mes de la consulta: " +ld.getMonthValue() );
			// CREO UNA VARIABLE PARA ASIGNAR EL MES
			int mes = ld.getMonthValue();
			
			// ACTUALIZADO LA ENTIDAD DE LA BASE DE DATOS
			rmen.updateEntity(entity.getNroAlertas(), entity.getPorcentajeAlertas(), entity.getId().getCodigo(), mes,
					entity.getId().getTipoAlerta());
			System.out.println(entity.toString());
		} catch (Exception e) {
			
			System.out.println("No se pudo  actualizar los datos");
		}

	}

	@Override
	public void deleteRegistros(int mes, int year) {
		try {
			
			// elimino los registros del mes y el a�o
			rmen.deleteRegistros(mes, year);
		} catch (Exception e) {
			System.out.println("Error al intenta eliminar los registros con el a�o:" + year + " y el mes: " + mes);
		}

	}

	@Override
	public List<ResumenMensual> findbyAlertType(int tipoAlerta, int porcentaje) {

		return rmen.findbyAlertType(tipoAlerta, porcentaje);
	}

		
	
}
