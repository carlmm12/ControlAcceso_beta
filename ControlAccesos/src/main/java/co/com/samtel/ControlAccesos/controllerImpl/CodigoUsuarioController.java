package co.com.samtel.ControlAccesos.controllerImpl;

import co.com.samtel.ControlAccesos.controller.ICodigoUsuarioController;
import co.com.samtel.ControlAccesos.service.IServiceCodigoUsuario;
import co.com.samtel.ControlAccesos.util.BeanUtil;

public class CodigoUsuarioController implements ICodigoUsuarioController {

	private IServiceCodigoUsuario codUsService;
	
	@Override
	public IServiceCodigoUsuario getCodUsuarioService() {
		if (codUsService == null ) {
			
			codUsService = (IServiceCodigoUsuario) BeanUtil.getBeanName("CodigoUsuarioBean");
		}
		
		return codUsService;
	}

}
