package co.com.samtel.ControlAccesos.controllerImpl;

import java.text.SimpleDateFormat;
import java.util.List;

import co.com.samtel.ControlAccesos.controller.IControlAccesoOrdController;
import co.com.samtel.ControlAccesos.entities.CodigoUsuario;
import co.com.samtel.ControlAccesos.entities.ControlAcceso;
import co.com.samtel.ControlAccesos.entities.ControlAccesosOrd;
import co.com.samtel.ControlAccesos.entities.ControlAccesosOrdPK;
import co.com.samtel.ControlAccesos.service.IServiceCodigoUsuario;
import co.com.samtel.ControlAccesos.service.IServiceControlAcceso;
import co.com.samtel.ControlAccesos.service.IServiceControlAccesoOrd;
import co.com.samtel.ControlAccesos.util.BeanUtil;

public class ControlAccesoOrdController implements IControlAccesoOrdController {

	private IServiceControlAccesoOrd conAccOrdService;
	private IServiceControlAcceso conAccService;
	private IServiceCodigoUsuario codUserService;
	private List<ControlAcceso> accesos;
	private CodigoUsuario usuarioCod;
	private ControlAccesosOrd codigoAccessOrd;
	private int countAc = 0;

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

	/*
	 * Este metodo me permite generar usar el servicio de controlAcceso.
	 */
	@Override
	public IServiceControlAcceso getControlAccesoService() {
		if (conAccService == null) {
			conAccService = (IServiceControlAcceso) BeanUtil.getBeanName("ControlAccesoBean");
		}

		return conAccService;
	}

	/*
	 * Metodo que me traera de la tabal control de acceso todos los registros, los
	 * ordenara y los registrara agregando el tipo de acceso
	 */

	/*
	 * metodo para obtener los registros del control de accesos ordenados
	 */
	public int getcountAc() {
		// conteo de los registros ordenados
		
		return getControlAccesoOrdService().countOrd() + 1;
	}
	
	@Override
	public void register() {
		// contador que me permite saber si es par o impar el campo mode de la tabla tblcontrol_accesos
		int cont = 0;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// llamado al metodo que me trae el rando de fechas por el cual solo se hizo el
		// registro

		List<String> fechas = getControlAccesoService().countDate();
		for (String fechaDia : fechas) {

			// llamado al metodo que me trae los codigos de los usuario que se registraron
			// el la fecha del ciclo.

			List<Integer> codigos = getControlAccesoService().usersDate(fechaDia);
			System.out.println("--------------------------------");
			System.out.println("dia: " + fechaDia);

			for (Integer codigo : codigos) {
				cont = 0;

				// llamado al usuario de acuerdo a lo registros del control de acceso por fecha
				// especificada

				this.usuarioCod = getCodigoUsuarioService().findByCode(codigo);

				System.out.println(usuarioCod.toString());

				// llamado al metodo que me trae la lista de los registros por usuario de
				// acuerdo a la fecha especificada

				List<ControlAcceso> controlesDiarios = getControlAccesoService().registroUsers(fechaDia, codigo);

				System.out.println("registros del usuario" + codigo + "de la fecha" + fechaDia);
				
				// metodo que me configura el tipo de acceso de acuerdo a la cantidad de registros
				for (ControlAcceso controlesDiario : controlesDiarios) {

					cont++;
					if (cont % 2 == 0) {
						
						
						
						
						System.out.println(controlesDiario.toString() + "par (salida)");
						codigoAccessOrd = new ControlAccesosOrd(new ControlAccesosOrdPK(getControlAccesoOrdService().countOrd() + 1, controlesDiario.getId().getDatetime(), usuarioCod.getCodigo()), 6, usuarioCod);
						
						System.out.println(this.codigoAccessOrd.toString());
						getControlAccesoOrdService().save(codigoAccessOrd);

					} else {

						System.out.println(controlesDiario.toString() + "impar (entrada)");
						codigoAccessOrd = new ControlAccesosOrd(new ControlAccesosOrdPK(getControlAccesoOrdService().countOrd() + 1, controlesDiario.getId().getDatetime(), usuarioCod.getCodigo()), 5, usuarioCod);
						
						System.out.println(this.codigoAccessOrd.toString());
						getControlAccesoOrdService().save(codigoAccessOrd);
					}

				}

				
				//valida de los registros terminas en par o impar y de ser impar crea un nuevo controlAccesoOrd
				
				if (cont % 2 == 0) {

					System.out.println("termino par" + "par");
				} else {

					
					System.out.println("termino impar" + "impar");
					this.codigoAccessOrd.getId().setId(getControlAccesoOrdService().countOrd() + 1);
					this.codigoAccessOrd.setTipoAcceso(6);
					System.out.println(this.codigoAccessOrd.toString());
					System.out.println("termino el ciclo de los registros del usuario");
					getControlAccesoOrdService().saveImpar(codigoAccessOrd);
				}

			}

			System.out.println("--------------------------------");

		}

	}

}
