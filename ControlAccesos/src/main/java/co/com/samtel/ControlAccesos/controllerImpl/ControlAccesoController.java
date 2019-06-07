package co.com.samtel.ControlAccesos.controllerImpl;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import com.opencsv.CSVReader;

import co.com.samtel.ControlAccesos.controller.IControlAccessoController;
import co.com.samtel.ControlAccesos.entities.ControlAcceso;
import co.com.samtel.ControlAccesos.entities.ControlAccesoPK;
import co.com.samtel.ControlAccesos.properties.ListenProperties;
import co.com.samtel.ControlAccesos.service.IServiceControlAcceso;
import co.com.samtel.ControlAccesos.util.BeanUtil;
import co.com.samtel.ControlAccesos.util.folderVerify;

@Controller
public class ControlAccesoController implements IControlAccessoController {

	private IServiceControlAcceso conAccService;
	private static ListenProperties prop = new ListenProperties();

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
	 * este metodo registrara la entidad de control Acceso
	 */
	@Override
	public void create(ControlAcceso entity) {
		try {
			getControlAccesoService().save(entity);
			// System.out.println("Registro la entidad");
		} catch (DataIntegrityViolationException e) {
			System.out.println("Ignorando por duplicidad");
		}

	}

	/*
	 * Este metodo me permite cargar desde un acrhivo csv la data a la base de datos
	 */
	@Override
	public Boolean CSVReader(String name_file) {

		String csvFile = folderVerify.createRoutFile(name_file);

		System.out.println(csvFile);

		char coma = ';';

		// convertir fecha y hora

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(csvFile), coma);
			System.out.println(reader.toString());
			String[] line;
			while ((line = reader.readNext()) != null) {
				System.out.println("Entro");
				
				// convertir la fecha que me trae el archivo a un fecha legible para la base datos
				Date date = formatter.parse(line[5]);
				
				
				ControlAcceso cAcceso = new ControlAcceso(
						new ControlAccesoPK(new Timestamp(date.getTime()), Integer.parseInt(line[1])),
						Integer.parseInt(line[3]), Integer.parseInt(line[4]), line[2], Integer.parseInt(line[0]));
				System.out.println(cAcceso.toString() + cAcceso.getId().toString());
				create(cAcceso);
				// System.out.println(line[0] + line[1] + line[2] + line[3] + line[4] +
				// line[5]);

			}

			return true;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	

}
