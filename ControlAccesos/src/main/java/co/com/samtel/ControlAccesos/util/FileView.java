package co.com.samtel.ControlAccesos.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import co.com.samtel.ControlAccesos.properties.ListenProperties;



public class FileView {

	private String[] f = new String[2];
	private JFileChooser chooser = new JFileChooser();
	private static String fileName = "";
    private static ListenProperties prop = new ListenProperties();
   
	/*
	 * metodo para copiar el archivo a la carpeta de destino
	 */

	public static Boolean saveFile(String urlS, String urlD) {

		System.out.println(urlS);
		
		System.out.println(urlD);
		
	
		if (urlS != null && urlD != null) {
			// System.out.println("paso correctamente");
			Path source = Paths.get(urlS);
			// System.out.println(source);
			Path destination = Paths.get(urlD);
			// System.out.println(urlD);

			try {
				java.nio.file.Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
            
		return false;
	
	}

	/*
	 * metodo para crear la carpeta en caso de que no exista
	 */

	public  Boolean createDirec(String fileOrg,String fileDest) {
		// System.out.println(new File(".").getAbsolutePath());
		String desktopPath = System.getProperty("user.home") + prop.getRutaFiles();

		desktopPath = desktopPath.replace("\\", "/");
		File folder = new File(desktopPath + prop.getNameFolder());
		String file = folder.getAbsolutePath() + "/" + fileDest;

		if (!folder.exists()) {
			System.out.println("Creando la carpeta");
			folder.mkdir();
			System.out.println(folder.getAbsolutePath());
			
		} else {
			System.out.println("No se pudo crear la carpeta por que ya existe");
			
		}
		
		Boolean val = saveFile(fileOrg, file);
		
		
		return val;
	}

	/*
	 * METODO QUE ME TRAE LA RUTA DEL ARCHIVO CSV QUE LA MAQUIN BIOMETRICA PARA EL
	 * CONTROL DE ACCESO
	 */
	public JFileChooser openfolders() throws IOException {
		chooser = new JFileChooser();

		// Filtro que permite solo archivos csv.
		FileFilter filtro = new FileNameExtensionFilter("Archivos csv", "csv");
		chooser.setFileFilter(filtro);

		int status = chooser.showOpenDialog(chooser);
		if (status == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			if (file == null) {
				return null;
			}

		}

		return chooser;
	}

//	JFileChooser jf = new JFileChooser();
//	jf.showOpenDialog(btnAbrir);
//	File file = jf.getSelectedFile();
//	String nameFile = jf.getSelectedFile().getName();
//	if(file  != null) {
//		txtRuta.setText(nameFile);
//	}

}
