package co.com.samtel.ControlAccesos.properties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ListenProperties {

	private static Properties prop = null;
	private static InputStream inputStream = null;
	//private static String propFileName = "resources/config.properties";
	private static String propFileName = "config.properties";
	private int year;
	private int horasLaboradas;
	private int horaEntrada;
	private int minutosEntrada;
	private int horaExtra;
	private int minutosExtra;

	private String rutaFiles;
	private String nameFolder;
	private int porcentajeReporte;
	private String codigoBorrar;

	public ListenProperties() {

	}

	public static Properties getProperties() {

		try {
			prop = new Properties();
			inputStream = ListenProperties.class.getClassLoader().getResourceAsStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			return prop;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public int getPorcentajeReporte() {
		this.porcentajeReporte = Integer.parseInt(getProperties().getProperty("porcentajeReporte"));
		return porcentajeReporte;
	}

	public int getYear() {
		this.year = Integer.parseInt(getProperties().getProperty("year"));
		return year;
	}

	public int getHorasLaboradas() {
		this.horasLaboradas = Integer.parseInt(getProperties().getProperty("horasLaboradas"));
		return horasLaboradas;
	}

	public int getHoraEntrada() {
		this.horaEntrada = Integer.parseInt(getProperties().getProperty("horaEntrada"));
		return horaEntrada;
	}

	public int getMinutosEntrada() {
		this.minutosEntrada = Integer.parseInt(getProperties().getProperty("minutosEntrada"));
		return minutosEntrada;
	}

	public int getHoraExtra() {
		this.horaExtra = Integer.parseInt(getProperties().getProperty("horaExtra"));
		return horaExtra;
	}

	public int getMinutosExtra() {
		this.minutosExtra = Integer.parseInt(getProperties().getProperty("minutosExtra"));
		return minutosExtra;
	}

	public String getRutaFiles() {
		this.rutaFiles = getProperties().getProperty("rutaFiles");
		return rutaFiles;
	}

	public String getNameFolder() {
		this.nameFolder = getProperties().getProperty("nameFolder");
		return nameFolder;
	}

	public String getCodigoBorrar() {
		this.codigoBorrar = getProperties().getProperty("codigoBorrar");
		return codigoBorrar;
	}

	/*
	 * METOODO QUE ME GENERA LA RUTA GENERICA DEL PROPERTIES
	 */

	public static String executedFromWithinJar(String name) {
		Boolean withinJar = null;
		String path = null;
		try {
			String location = ListenProperties.class.getResource(name).toString();
			System.out.println("este es lde la variable location : " + location);
			if (location.startsWith("rsrc:") || location.endsWith(".jar") || location.startsWith("file:")) {

				File f = new File(location.substring(location.indexOf(':') + 1));
				System.out.println(f.getCanonicalPath());
				path = f.getCanonicalPath();
				System.out.println();
			}
			return path;
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return "";
	}

}
