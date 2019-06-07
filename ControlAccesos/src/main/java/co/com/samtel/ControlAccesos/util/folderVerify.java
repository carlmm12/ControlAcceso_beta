package co.com.samtel.ControlAccesos.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.com.samtel.ControlAccesos.properties.ListenProperties;



public class folderVerify {
	private static ListenProperties prop = new ListenProperties();


/*
 * metodo para crear la carpeta en caso de que no exista
 */

public static void createDirec() {
	// System.out.println(new File(".").getAbsolutePath());
	String desktopPath = System.getProperty("user.home") + prop.getRutaFiles();

	desktopPath = desktopPath.replace("\\", "/");
	File folder = new File(desktopPath + "/Reportes");

	if (!folder.exists()) {
		System.out.println("Creando la carpeta");
		folder.mkdir();
		System.out.println(folder.getAbsolutePath());
	} else {
		System.out.println("No se pudo crear la carpeta por que ya existe");
	}
}

/*
 * METODO QUE ME GENERA EL NOMBRE DEL ARCHIVO EXCEL CON LA FECHA DE ACTUAL Y
 * DEVUELVA UN STRING CON EL NOMBRE DE LA RUTA Y EL NOMBRE DEL ARCHIVO
 */

public  static String createFileName(String nombre) {
	Date date = new Date();
	DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
	String convertido = fecha.format(date);
	System.out.println(convertido);

	String nameFile = "ControlAcceso_" + nombre + "_" + convertido + ".xlsx";
	String rute = System.getProperty("user.home") + prop.getRutaFiles() + "/Reportes/";
	rute = rute.replace("\\", "/");
	String foldR = rute + nameFile;
	System.out.println(rute);
	System.out.println(foldR);
	return foldR;
}


/*
 * METODO QUE ME GENERA LA RUTA Y EL NOMBRE DEL ARCHIVO DEL CONTROL DE ACCESO QUE PROVIENE DE LA MAQUINA BIOMETRICA
 * 
 * 
 */

public  static String createRoutFile(String name_file) {
	

	
	String rutePath = System.getProperty("user.home") + prop.getRutaFiles();
	rutePath = rutePath.replace("\\", "/");
	File folder = new File(rutePath + prop.getNameFolder());
	String file = folder.getAbsolutePath() + "/" + name_file;
	System.out.println(file);
	return file;
}

}
