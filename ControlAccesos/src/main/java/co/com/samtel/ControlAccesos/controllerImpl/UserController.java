package co.com.samtel.ControlAccesos.controllerImpl;

import java.util.List;

import org.springframework.stereotype.Controller;

import co.com.samtel.ControlAccesos.controller.IUserController;
import co.com.samtel.ControlAccesos.entities.Usuario;
import co.com.samtel.ControlAccesos.service.IServiceUsuario;
import co.com.samtel.ControlAccesos.util.BeanUtil;

@Controller
public class UserController implements IUserController {
   
	
	private IServiceUsuario userService;


	public UserController() {
		
	}
     
	/*
	 * Este metodo me permite generar usar el servicio de Usuario.
	 */
   @Override
	public IServiceUsuario getUserService() {
		if(userService == null ) {
			userService = (IServiceUsuario) BeanUtil.getBeanName("UsuarioBean");
		}
		return userService;
	}
    
	/*
	 * Metodo para obtener todos los usuarios
	 */
   @Override
	public List<Usuario> usuarios(){
		
		return getUserService().findAll();
		
	}

	
	
	
	
	
}
