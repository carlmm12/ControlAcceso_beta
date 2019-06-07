package co.com.samtel.ControlAccesos.controller;

import java.util.List;

import co.com.samtel.ControlAccesos.entities.Usuario;
import co.com.samtel.ControlAccesos.service.IServiceUsuario;

public interface IUserController {

	

	IServiceUsuario getUserService();

	List<Usuario> usuarios();

}
