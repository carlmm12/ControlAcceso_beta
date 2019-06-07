package co.com.samtel.ControlAccesos.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.samtel.ControlAccesos.entities.ControlDiario;
import co.com.samtel.ControlAccesos.repository.IControlDiario;
import co.com.samtel.ControlAccesos.service.IServiceControlDiario;

@Service("ControlDiarioBean")
public class ControlDiarioImpl implements IServiceControlDiario {

	@Autowired
	public IControlDiario cdia;

	@Override
	public void save(ControlDiario entity) {

		List<ControlDiario> controles = cdia.findControlD(entity.getFecha(), entity.getCodigoUsuario());

		if (controles.size() < 1) {

			try {
				cdia.save(entity);
			} catch (Exception e) {
				System.out.println("ERROR  AL REGISTRAR  EL CONTROL DIARIO ");
			}
		} else {
			System.out.println("ERROR EL CONTROL DIARIO YA FUE REGISTRADO");
		}

	}

	@Override
	public Boolean saveAll(List<ControlDiario> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ControlDiario> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ControlDiario> findAll() {
		// TODO Auto-generated method stub
		return cdia.finndAll();
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
	public void delete(ControlDiario entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public int countC() {
		// TODO Auto-generated method stub
		return cdia.countC();
	}

	@Override
	public List<ControlDiario> findAllRange(int mes, int year, int diaI, int diaF) {
		// TODO Auto-generated method stub
		return cdia.findAllRange(mes, year, diaI, diaF);
	}

	@Override
	public ControlDiario findByDateAndCode(String fecha, int codigoUsuario) {
		return cdia.findByDateAndCode(fecha, codigoUsuario);
	}

}
