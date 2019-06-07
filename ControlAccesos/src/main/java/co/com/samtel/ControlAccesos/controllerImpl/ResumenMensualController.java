package co.com.samtel.ControlAccesos.controllerImpl;

import java.time.LocalDate;
import java.util.List;

import co.com.samtel.ControlAccesos.controller.IResumenMensualController;
import co.com.samtel.ControlAccesos.dto.ControlDiarioDto;
import co.com.samtel.ControlAccesos.entities.CodigoUsuario;
import co.com.samtel.ControlAccesos.entities.ResumenMensual;
import co.com.samtel.ControlAccesos.entities.ResumenMensualPK;
import co.com.samtel.ControlAccesos.properties.ListenProperties;
import co.com.samtel.ControlAccesos.service.IServiceCodigoUsuario;
import co.com.samtel.ControlAccesos.service.IServiceControlAccesoOrd;
import co.com.samtel.ControlAccesos.service.IServiceResumenMensual;
import co.com.samtel.ControlAccesos.util.BeanUtil;
import co.com.samtel.ControlAccesos.util.convertDate;

public class ResumenMensualController implements IResumenMensualController {

	IServiceResumenMensual resMensualService;
	private IServiceControlAccesoOrd conAccOrdService;
	private IServiceCodigoUsuario codUserService;
	private static ListenProperties prop = new ListenProperties();

	@Override
	public IServiceResumenMensual getResumenMensualServices() {
		if (resMensualService == null) {
			resMensualService = (IServiceResumenMensual) BeanUtil.getBeanName("ResumenMensualBean");
		}
		return resMensualService;
	}

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
	 * METODO QUE ME PERMITE DE CUERDO A LOS PARAMETROS HACER LA BUSQUEDA DE LOS
	 * RGISTROS EN LA TABLA RESUMEN MENSUAL Y ME PERMITE HACER LA INSERCION O LA
	 * ACTUALIZACI�N DE ACUERDO A LOS RESULTADOS QUE ME TRAEN DE LA BASE DE DATOS
	 */
	public void addUpdate(int codUser, String fechasDia, int tipAlerta, int totaldias, CodigoUsuario codigoUser) {

		// ARREGLAR EL METODO QUE ME TRAE EL NUMERO DE ALERTAS Y EL PORNCETAJE DE
		// ALERTAS

		System.out.println(fechasDia);

		// se hace un parse de la fecha que viene en formato COT para que aparezca en forma UTC separado por guiones
		LocalDate fec = convertDate.converToDat(fechasDia);
		System.out.println("---------------  ESTE ES EL INICIO DEL REGISTRO DE RESUMEN MENSUAL --------------");
        
		int mes = fec.getMonthValue();
		int year = fec.getYear();

		// se hace una busqueda de la cantidad de registros que existen de acuerdo al codigo de usuaro, el tipo de alerta, el mes y el a�o
		int nroAlertas = getResumenMensualServices().findnumAlertas(codUser, mes, year, tipAlerta);
		System.out.println(nroAlertas);

		// se toma el porcentaje por alerta tomando como 100% el numero de dias en el
		// rango estipulado
		int porcentajeAlertas = 0;

		
		// si entra al metodo se le agrega uno  m�s  para poder registrar el alerta. 
		nroAlertas = nroAlertas + 1;
		System.out.println(nroAlertas);
		
		// Se realiza el porcentaje de acuerdo al numero de d�as y como 100 % el total de di�s 
		porcentajeAlertas = (nroAlertas* 100) / totaldias ;

		System.out.println(porcentajeAlertas);

		// se crea la entidad resumenMensual lo cual ser� o insertado o actualizado.

		ResumenMensual resMen = new ResumenMensual(new ResumenMensualPK(codigoUser.getCodigo(), tipAlerta),
				convertDate.convertToDate(fechasDia), nroAlertas, porcentajeAlertas, totaldias,
				codigoUser.getTblusuario().getNombre());

		System.out.println(resMen.toString());
        
		
		System.out.println("---------------  ESTE ES EL FINAL DEL REGISTRO DE RESUMEN MENSUAL --------------");
		
		// se verifica que el numero de alertas que nos trae la base de datos de ser
		// cero lo insertamos debido
		// a que no esta registrado ningun dato aparente en la base de datos y por el
		// otro lado de ser mayor a cero
		// lo actualizamos con el fin que por mes solo quede un registro por alerta
		
		if (nroAlertas == 1) {

			getResumenMensualServices().save(resMen);
           System.out.println("entro a registrarse");
		} else {

		
			getResumenMensualServices().updateEntity(resMen);
			System.out.println("entro a actualizarce");

		}

	}

	
	
	
	
	/*
	 * metoo que me permite registrar en la tabla tblresumenes_mensual la informaci�
	 * pertienende resumida por cada una de las alertas, para este metodo es
	 * necesario conocer el mes, el a�o, el dia inicial y el dia final por el cual
	 * se generara el reporte resumido
	 * 
	 */

	@Override
	public void resumenMenRegisterR(int mes, int year, int diaI, int diaF) {

		
		// ELIMINAR LOS REGISTROS DE ACUERDO AL MES Y A LA FECHA PARA QUE NO SE GENERE UN CONTEO CON DATOS YA REGISTRADOS
		
		 getResumenMensualServices().deleteRegistros(mes, year);
		
		// lamado a todas las fechas registradas en la tabla controlAccessoOrd
		List<String> fechas = getControlAccesoOrdService().controlDiasR(mes, year, diaI, diaF);

		
		// se valida que las fechas sean mayor a 0 para poder ejecutar el procedimiento de almacenado y de actualizaci�n
		if (fechas.size() > 0) {
			// total de dias laborados en la empresa
			int totaldias = fechas.size();

			// llamado a todos los codigos de usuario de acuerdo a la fecha estipulada

			for (String fechasDia : fechas) {

				System.out.println("fecha: " + fechasDia);

				// llamado al metodo para traer la lista de los codigos de los usuarios en la
				// fecha especifica
				List<Integer> codUsers = getControlAccesoOrdService().usersDate(fechasDia);

				for (Integer codUser : codUsers) {

					// carga del codigo de usuario de acuerdo al codigo
					CodigoUsuario codigoUser = getCodigoUsuarioService().findByCodigo(codUser);

					// cargo el DTO que me permite traer el conteo de horas trabajadas y la fecha de
					// entrada y salida de acuerdo a una fecha y un codigo de usuario
					ControlDiarioDto controlDto = getControlAccesoOrdService().controlDia(fechasDia, codUser);

//					// llamado a todos los controles de acceso en una lista de acuerdo a el usuario
//					// y la fecha
					//
//					 List<ControlAccesosOrd> controles = getControlAccesoOrdService().registroUsers(fechasDia, codUser);

					// se crea el reporte de acuerdo al tipo de alerta (mayor horas laboradas)(menor
					// horas laboradas) (hora de ingreso)

					if (controlDto.getEntrada().getHours() >= prop.getHoraEntrada() && controlDto.getEntrada().getMinutes() > prop.getMinutosEntrada()) {

						System.out.println("USUARIOS CON RETARDOS");
						addUpdate(codUser, fechasDia, 1, totaldias, codigoUser);
					} 
					
					if (controlDto.getTiempo().getHours() < prop.getHorasLaboradas()) {

						System.out.println("USUARIOS CON MENOR HORAS TRABAJADAS");
						addUpdate(codUser, fechasDia, 2, totaldias, codigoUser);

					} 
					
					if (controlDto.getTiempo().getHours() >= prop.getHoraExtra() &&  controlDto.getTiempo().getMinutes() >= prop.getMinutosExtra()) {

						System.out.println("USUARIOS CON MAYOR HORAS TRABAJADAS");

						addUpdate(codUser, fechasDia, 3, totaldias, codigoUser);

					}

				}
			}

		} else {
			System.out.println("No se encuentra datos con los parametros" + " - mes" + mes + " - anio" + year
					+ "- dia inicial" + diaI + " - dia final" + diaF);
		}

	}

	@Override
	public void resumenMenRegisterR1(int diaI, int diaF) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ResumenMensual> ResumenEntity(int tipoAlerta , int porcentaje) {
		
		return getResumenMensualServices().findbyAlertType(tipoAlerta , porcentaje);
	}

}
