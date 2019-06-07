package co.com.samtel.ControlAccesos.service;

import co.com.samtel.ControlAccesos.entities.CodigoUsuario;

public interface IServiceCodigoUsuario extends IServiceRepo<CodigoUsuario, Integer> {

	CodigoUsuario findByCode(Integer codigo);
	void insertCodigo( Integer codigo);
	CodigoUsuario findByCodigo(Integer codigo);
	
}
	