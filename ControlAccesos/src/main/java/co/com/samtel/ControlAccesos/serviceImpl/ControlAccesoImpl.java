package co.com.samtel.ControlAccesos.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.samtel.ControlAccesos.entities.ControlAcceso;
import co.com.samtel.ControlAccesos.repository.IControlAcceso;
import co.com.samtel.ControlAccesos.service.IServiceControlAcceso;

@Service("ControlAccesoBean")
public class ControlAccesoImpl implements IServiceControlAcceso {

	@Autowired
	public IControlAcceso controlA;

	public ControlAccesoImpl() {

	}

	public void save(ControlAcceso entity) {
		// TODO Auto-generated method stub

		controlA.insertControlAcc(entity.getTmno(), entity.getId().getEnno(), entity.getName(), entity.getInout(),
				entity.getMode(), entity.getId().getDatetime());

	}

	public Boolean saveAll(List<ControlAcceso> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<ControlAcceso> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<ControlAcceso> findAll() {
		// TODO Auto-generated method stub
		return controlA.findAll();
	}

	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	public void delete(ControlAcceso entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ControlAcceso> findByMonth(int month) {
		// TODO Auto-generated method stub
		return controlA.findByMonth(month);
	}

	@Override
	public int countByDay(int id, String fecha) {
		// TODO Auto-generated method stub
		return controlA.countByDay(id, fecha);
	}

	@Override
	public List<String> countDate() {
		// TODO Auto-generated method stub
		return controlA.countDate();
	}

	@Override
	public List<ControlAcceso> registroUsers(String fecha, int codigo) {
		// TODO Auto-generated method stub
		return controlA.registroUsers(fecha, codigo);
	}

	@Override
	public List<Integer> usersDate(String fecha) {
		// TODO Auto-generated method stub
		return controlA.usersDate(fecha);
	}

	@Override
	public Integer getNumberData() {

		return controlA.getNumberData();
	}

	@Override
	public Boolean deleteDataControlAccesos() {
		try {
			controlA.deleteDataControlAccesos();
			return true ;
		} catch (Exception e) {
			System.out.println("Error al eliminar los datos de la tabla tblcontrol_accesos");
			return false;
		}
	}

}
