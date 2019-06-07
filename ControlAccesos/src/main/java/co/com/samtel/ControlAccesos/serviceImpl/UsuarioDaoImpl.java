package co.com.samtel.ControlAccesos.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.samtel.ControlAccesos.entities.Usuario;
import co.com.samtel.ControlAccesos.repository.IUsuarioDao;
import co.com.samtel.ControlAccesos.service.IServiceUsuario;

@Service("UsuarioBean")
public class UsuarioDaoImpl implements IServiceUsuario {



	@Autowired
	public IUsuarioDao iUS;
	
	
	public UsuarioDaoImpl() {

	}

	public void save(Usuario entity) {
        try {
			iUS.findAllByID(entity.getCedula());
		} catch (Exception e) {
			System.out.println(e);
		}
           
	
	}

	public Boolean saveAll(List<Usuario> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuario findById(String id) {
		// TODO Auto-generated method stub
		return iUS.findAllByID(id);
	}

	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return iUS.findAll();
	}

	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void deleteById(String id) {
		// TODO Auto-generated method stub

	}

	public void delete(Usuario entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<Usuario> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
