package co.com.samtel.ControlAccesos.controllerImpl;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.samtel.ControlAccesos.controller.IControlDiarioController;
import co.com.samtel.ControlAccesos.dto.ControlDiarioAlertaDto;
import co.com.samtel.ControlAccesos.dto.ControlDiarioDto;
import co.com.samtel.ControlAccesos.entities.CodigoUsuario;
import co.com.samtel.ControlAccesos.entities.ControlDiario;
import co.com.samtel.ControlAccesos.properties.ListenProperties;
import co.com.samtel.ControlAccesos.service.IServiceCodigoUsuario;
import co.com.samtel.ControlAccesos.service.IServiceControlAccesoOrd;
import co.com.samtel.ControlAccesos.service.IServiceControlDiario;
import co.com.samtel.ControlAccesos.util.BeanUtil;
import co.com.samtel.ControlAccesos.util.convertDate;

public class ControlDiarioController implements IControlDiarioController {

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

//	@Override
//	public Date convertToDate(String date) {
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		Date converDate;
//		try {
//			converDate = formatter.parse(date);
//			return converDate;
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

	/*
	 * Este metodo es la alarma de horas que se realiza por d�a se tomada por dia
	 * cantidad de horas laboradas, fecha de inicio, fecha final de sus horas de
	 * trabajo.
	 */
	@Override
	public void alarmaControlDiario() {

		// lamado a todas las fechas registradas en la tabla controlAccessoOrd
		List<String> fechas = getControlAccesoOrdService().countDate();

		// llamado a todos los codigos de usuario de acuerdo a la fecha estipulada

		for (String fechasDia : fechas) {
			System.out.println("fecha: " + fechasDia);
			List<Integer> codUsers = getControlAccesoOrdService().usersDate(fechasDia);
			for (Integer codUser : codUsers) {

				// carga del codigo de usuario de acuerdo al codigo
				CodigoUsuario codigoUser = getCodigoUsuarioService().findByCodigo(codUser);

				// cargo el DTO que me permite traer el conteo de horas trabajadas y la fecha de
				// entrada y salida de acuerdo a una fecha y un codigo de usuario
				ControlDiarioDto controlDto = getControlAccesoOrdService().controlDia(fechasDia, codUser);

				// asignaci�n de valores a el control de dia lista para registrar
				ControlDiario controlDia = new ControlDiario(getControlDiarioService().countC() + 1,
						codigoUser.getCodigo(), controlDto.getEntrada(), convertDate.convertToDate(fechasDia),
						codigoUser.getTblusuario().getNombre(), controlDto.getSalida(), controlDto.getTiempo());
				System.out.println(controlDia.toString());

				// metodo que me permitira registrar el control diario de cada usuario por fecha
				// especifica
				getControlDiarioService().save(controlDia);
			}
		}

	}

	/*
	 * metodo que me devuele un lista de controlDiario DTO de acuerdo a un rango
	 * especifico y si el usuario no cumple con las 9 horas estipuladas por la
	 * empresa
	 */

	@Override
	public List<ControlDiarioAlertaDto> convertEntityMenorH(int mes, int year, int diaI, int diaF) {

		List<ControlDiarioAlertaDto> controlAlertas = new ArrayList<ControlDiarioAlertaDto>();

		try {

			SimpleDateFormat format = new SimpleDateFormat("hh:mm"); // if 24 hour format
			Date d1;
			Time ppstime;

			d1 = (java.util.Date) format.parse("09:00:00");

			ppstime = new java.sql.Time(d1.getTime());

			// System.out.println(d1);
			// System.out.println(ppstime);

			// Llamado al metodo que me trae todos los datos de la tabla tblcontrol_diario
			// de acuerdo al rango (mes, anio, dia inicial , dia final)
			List<ControlDiario> controlD = getControlDiarioService().findAllRange(mes, year, diaI, diaF);

			String alerta = "";

			ControlDiarioAlertaDto controlDA = null;
			for (ControlDiario controlDiario : controlD) {
				if (controlDiario.getTiempo().getHours() < prop.getHorasLaboradas()) {
					alerta = "EL USUARIO NO CUMPLE CON LAS 9 HORAS ESTABLECIDAS";
					// System.out.println("EL usuario :" + controlDiario.getNombre() + " no cumplio
					// las 9 horas correspondietes");

					// se carga el DTO solo si el usuario no cumple con las horas establecidas
					controlDA = new ControlDiarioAlertaDto(controlDiario.getFecha().toString(),
							String.valueOf(controlDiario.getCodigoUsuario()), controlDiario.getNombre(),
							controlDiario.getEntrada().toString(), controlDiario.getSalida().toString(),
							controlDiario.getTiempo().toString(), alerta);

					// Se agrega a la lista de controlDiarioalertaDTO para returnarlo para generar
					// el reporte
					controlAlertas.add(controlDA);

				} else {
					alerta = "";
				}

				// System.out.println(controlDA.toString());

			}

			return controlAlertas;

		} catch (Exception e) {

			System.out.println(e);

		}

		return controlAlertas;
	}

	/*
	 * metodo que me devuele un lista de controlDiario DTO de acuerdo a un rango
	 * especifico y si el usuario coloboro con mas de 9 horas
	 */
	@Override
	public List<ControlDiarioAlertaDto> convertEntityMayorH(int mes, int year, int diaI, int diaF) {

		List<ControlDiarioAlertaDto> controlAlertas = new ArrayList<ControlDiarioAlertaDto>();
		// Llamado al metodo que me trae todos los datos de la tabla tblcontrol_diario
		// de acuerdo al rango (mes, a�o, dia inicial , dia final)
		List<ControlDiario> controlD = getControlDiarioService().findAllRange(mes, year, diaI, diaF);
		String alerta = "";

		ControlDiarioAlertaDto controlDA = null;
		for (ControlDiario controlDiario : controlD) {
			if (controlDiario.getTiempo().getHours() >= prop.getHoraExtra()
					&& controlDiario.getTiempo().getMinutes() > prop.getMinutosExtra()) {
				alerta = "EL USUARIO COLABORO CON MAS DE LAS 9 HORAS ESTABLECIDAS ";

				// se carga el DTO solo si el usuario colaboro con mas de 9 horas
				controlDA = new ControlDiarioAlertaDto(controlDiario.getFecha().toString(),
						String.valueOf(controlDiario.getCodigoUsuario()), controlDiario.getNombre(),
						controlDiario.getEntrada().toString(), controlDiario.getSalida().toString(),
						controlDiario.getTiempo().toString(), alerta);

				// Se agrega a la lista de controlDiarioalertaDTO para returnarlo para generar
				// el reporte
				controlAlertas.add(controlDA);
			}

		}

		return controlAlertas;
	}

	/*
	 * metodo que me devuele un lista de controlDiario DTO de acuerdo a un rango
	 * especifico y si el usuario entro con una hora mayor a la estipulada por la
	 * empresa
	 */

	@Override
	public List<ControlDiarioAlertaDto> convertEntityHoraLlegada(int mes, int year, int diaI, int diaF) {

		List<ControlDiarioAlertaDto> controlAlertas = new ArrayList<ControlDiarioAlertaDto>();

		// Llamado al metodo que me trae todos los datos de la tabla tblcontrol_diario
		// de acuerdo al rango (mes, a�o, dia inicial , dia final)
		List<ControlDiario> controlD = getControlDiarioService().findAllRange(mes, year, diaI, diaF);
		String alerta = "";

		ControlDiarioAlertaDto controlDA = null;
		for (ControlDiario controlDiario : controlD) {
			if (controlDiario.getEntrada().getHours() >= prop.getHoraEntrada()
					&& controlDiario.getEntrada().getMinutes() > prop.getMinutosEntrada()) {
				alerta = "LA HORA DE ENTRADA ES MAYOR A LAS " + prop.getHoraEntrada() + ":" + prop.getMinutosEntrada()
						+ " AM";

				// se carga el DTO solo si el usuario llego tarde
				controlDA = new ControlDiarioAlertaDto(controlDiario.getFecha().toString(),
						String.valueOf(controlDiario.getCodigoUsuario()), controlDiario.getNombre(),
						controlDiario.getEntrada().toString(), controlDiario.getSalida().toString(),
						controlDiario.getTiempo().toString(), alerta);

				// Se agrega a la lista de controlDiarioalertaDTO para returnarlo para generar
				// el reporte
				controlAlertas.add(controlDA);
			}

		}

		return controlAlertas;
	}

} // fin de la clase
