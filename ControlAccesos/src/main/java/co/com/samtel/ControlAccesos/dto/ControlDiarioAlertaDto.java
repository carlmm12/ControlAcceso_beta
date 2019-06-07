package co.com.samtel.ControlAccesos.dto;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ControlDiarioAlertaDto {

	
	
	
	private String fecha;

	private String codigo;
	
	private String nombre;
	
	private String entrada;

	private String salida;

	private String tiempo;
	
	private String Alerta;

	public ControlDiarioAlertaDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ControlDiarioAlertaDto(String fecha, String codigo, String nombre, String entrada, String salida,
			String tiempo, String alerta) {
		super();
		this.fecha = fecha;
		this.codigo = codigo;
		this.nombre = nombre;
		this.entrada = entrada;
		this.salida = salida;
		this.tiempo = tiempo;
		Alerta = alerta;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public String getAlerta() {
		return Alerta;
	}

	public void setAlerta(String alerta) {
		Alerta = alerta;
	}

	@Override
	public String toString() {
		return "ControlDiarioAlertaDto [fecha=" + fecha + ", codigo=" + codigo + ", nombre=" + nombre + ", entrada="
				+ entrada + ", salida=" + salida + ", tiempo=" + tiempo + ", Alerta=" + Alerta + "]";
	}
	
	
	
}
