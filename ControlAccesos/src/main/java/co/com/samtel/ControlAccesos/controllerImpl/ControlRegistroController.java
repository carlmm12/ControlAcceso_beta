package co.com.samtel.ControlAccesos.controllerImpl;

import java.sql.Time;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import co.com.samtel.ControlAccesos.controller.IControlRegistroController;
import co.com.samtel.ControlAccesos.entities.ControlAccesosOrd;
import co.com.samtel.ControlAccesos.entities.ControlDiario;
import co.com.samtel.ControlAccesos.entities.ControlRegistro;
import co.com.samtel.ControlAccesos.properties.ListenProperties;
import co.com.samtel.ControlAccesos.service.IServiceCodigoUsuario;
import co.com.samtel.ControlAccesos.service.IServiceControlAccesoOrd;
import co.com.samtel.ControlAccesos.service.IServiceControlDiario;
import co.com.samtel.ControlAccesos.service.IServiceControlRegistro;
import co.com.samtel.ControlAccesos.util.BeanUtil;
import co.com.samtel.ControlAccesos.util.convertDate;

public class ControlRegistroController implements IControlRegistroController {

	private IServiceControlRegistro serviceControlRegistro;
	private IServiceControlDiario conAccDiarService;
	private IServiceControlAccesoOrd conAccOrdService;
	private IServiceCodigoUsuario codUserService;
	private ListenProperties prop = new ListenProperties();

	@Override
	public IServiceControlDiario getControlDiarioService() {
		if (conAccDiarService == null) {
			conAccDiarService = (IServiceControlDiario) BeanUtil.getBeanName("ControlDiarioBean");
		}
		return conAccDiarService;
	}

	/*
	 * Este metodo me permite generar usar el servicio de codigoUsuario.
	 */

	@Override
	public IServiceCodigoUsuario getCodigoUsuarioService() {
		if (codUserService == null) {
			codUserService = (IServiceCodigoUsuario) BeanUtil.getBeanName("codigoUsuarioBean");
		}
		return codUserService;
	}

	/*
	 * Este metodo me permite generar usar el servicio de controlAccesoOrd.
	 */

	@Override
	public IServiceControlAccesoOrd getControlAccesoOrdService() {
		if (conAccOrdService == null) {
			conAccOrdService = (IServiceControlAccesoOrd) BeanUtil.getBeanName("ControlAccesoOrdBean");
		}
		return conAccOrdService;
	}

	@Override
	public IServiceControlRegistro getControlRegistroService() {

		if (serviceControlRegistro == null) {
			serviceControlRegistro = (IServiceControlRegistro) BeanUtil.getBeanName("ControlRegistroBean");
		}
		return serviceControlRegistro;
	}

	@Override
	public void controlRegistros() {
		// lamado a todas las fechas registradas en la tabla controlAccessoOrd
		List<String> fechas = getControlAccesoOrdService().countDate();
		Date entrada = null;
		Date salida = null;
		Time permanencia = convertDate.converDateZero();
		Time noLaborado = convertDate.converDateZero();
		int band5 = 0 ;
		int band6 = 0;
		int cont = 0;
		// llamado a todos los codigos de usuario de acuerdo a la fecha estipulada

		for (String fechasDia : fechas) {
			System.out.println("fecha: " + fechasDia);
			List<Integer> codUsers = getControlAccesoOrdService().usersDate(fechasDia);
			for (Integer codUser : codUsers) {
				 permanencia = convertDate.converDateZero();
				 noLaborado = convertDate.converDateZero();
				ControlDiario controlDiario = new ControlDiario();
				controlDiario = getControlDiarioService().findByDateAndCode(fechasDia, codUser);
				Integer numeroRegistros = getControlAccesoOrdService().findNumRegistros(codUser, fechasDia);
				List<ControlAccesosOrd> controlAccesosOrd = getControlAccesoOrdService().registroUsers(fechasDia, codUser);
				
				for (ControlAccesosOrd controlAccesoO : controlAccesosOrd) {
					cont ++;
					if (controlAccesoO.getTipoAcceso() == 5) {
						entrada = controlAccesoO.getId().getFecha();
						if (band6 == 1) {
							band5 = 1;
						}
					}
					if (controlAccesoO.getTipoAcceso() == 6) {
						salida = controlAccesoO.getId().getFecha();
						band6 = 1;
					}
					if (cont == 2) {		
						
						System.out.println(getControlAccesoOrdService().findDifBetweenHours(entrada,salida ));
						permanencia =  getControlRegistroService().findAddBetweenHours(permanencia, getControlAccesoOrdService().findDifBetweenHours(entrada, salida));
						System.out.println(permanencia);
						cont = 0;
					}
					if (band5 == 1 && band6 == 1){
						noLaborado =  getControlRegistroService().findAddBetweenHours(noLaborado, getControlAccesoOrdService().findDifBetweenHours(salida,entrada));
						System.out.println(noLaborado);
						band5  =  0;
						band6 = 0;
						
						
					}
				}
				ControlRegistro controlRegistro = new ControlRegistro(numeroRegistros,noLaborado, permanencia,controlDiario);			
				getControlRegistroService().save(controlRegistro);
				band5  =  0;
				band6 = 0;
				cont = 0;
			}
		}
	}
	
	
	

}
