package co.com.samtel.ControlAccesos.serviceImpl;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.samtel.ControlAccesos.entities.ControlDiario;
import co.com.samtel.ControlAccesos.entities.ControlRegistro;
import co.com.samtel.ControlAccesos.repository.IControlDiario;
import co.com.samtel.ControlAccesos.repository.IControlRegistro;
import co.com.samtel.ControlAccesos.service.IServiceControlDiario;
import co.com.samtel.ControlAccesos.service.IServiceControlRegistro;

@Service("ControlRegistroBean")
public class ControlRegistroImpl implements IServiceControlRegistro {

	@Autowired
	private IControlRegistro controlRegistro;

	@Override
	public void save(ControlRegistro entity) {
		try {
			System.out.println(entity.toString());
			controlRegistro.save(entity);
		} catch (Exception e) {
			System.out.println("no sirvio el registro de la entidad control de registrol");
			e.printStackTrace();
		}
		
	}

	@Override
	public Boolean saveAll(List<ControlRegistro> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ControlRegistro> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ControlRegistro> findAll() {
		// TODO Auto-generated method stub
		return null;
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
	public void delete(ControlRegistro entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Time findAddBetweenHours(Time horaUno, Time horaDos) {
		return controlRegistro.findAddBetweenHours(horaUno, horaDos);
	}

	@Override
	public List<ControlRegistro> findAllControlRegistros(int mes, int year, int diaI, int diaF) {
		
		return controlRegistro.findAllControlRegistros(mes, year, diaI, diaF);
	}
	

}
