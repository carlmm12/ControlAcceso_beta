package co.com.samtel.ControlAccesos.controller;

import co.com.samtel.ControlAccesos.entities.ControlAcceso;
import co.com.samtel.ControlAccesos.service.IServiceControlAcceso;

public interface IControlAccessoController {
	
	/**
	 * Permite llamar al beanUtil y generar el bean
	 * 
	 * @return IServiceControlAcceso
	 */
	IServiceControlAcceso getControlAccesoService();
	/**
	 * Metodo que me permite leer el archivo CSV y regisrar a la base de datos
	 * @param nombre del archivo con su extension
	 * @return Boolean
	 */
    Boolean CSVReader(String name_file);
    
    /**
     * Metoodo que me permite registrar en la base de datos
     * @param entity de controlAcceso
     */
    void create(ControlAcceso entity);
    
}
