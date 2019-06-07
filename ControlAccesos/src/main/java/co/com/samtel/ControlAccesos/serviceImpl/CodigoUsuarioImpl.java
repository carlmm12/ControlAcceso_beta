package co.com.samtel.ControlAccesos.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.samtel.ControlAccesos.entities.CodigoUsuario;
import co.com.samtel.ControlAccesos.repository.ICodigoUsuario;
import co.com.samtel.ControlAccesos.service.IServiceCodigoUsuario;

@Service("codigoUsuarioBean")
public class CodigoUsuarioImpl implements IServiceCodigoUsuario {
	
	@Autowired
	public ICodigoUsuario codigoUs;

	@Override
	public void save(CodigoUsuario entity) {
		
	}


	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CodigoUsuario> findAll() {
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
	public void delete(CodigoUsuario entity) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Boolean saveAll(List<CodigoUsuario> list) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<CodigoUsuario> findById(Integer id) {
		// TODO Auto-generated method stub
		return codigoUs.findById(id);
	}


	@Override
	public CodigoUsuario findByCode(Integer codigo) {
		
		CodigoUsuario codeUser = codigoUs.findByCode(codigo);
		
		
		
		if(codeUser != null) {
			
			return codeUser;
		}else {
			 try {
				 System.out.println("error vamos a registrar");
				 codigoUs.insertCodigo(codigo);
				 codeUser = codigoUs.findByCode(codigo);
			} catch (Exception e) {
				System.out.println("error de registro");
			}
			
		}
		
		return codeUser;
	}


	@Override
	public void insertCodigo(Integer codigo) {
		try {
			codigoUs.insertCodigo(codigo);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		
	}


	@Override
	public CodigoUsuario findByCodigo(Integer codigo) {
		// TODO Auto-generated method stub
		return codigoUs.findByCod(codigo);
	}

}
