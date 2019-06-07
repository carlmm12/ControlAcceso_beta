package co.com.samtel.ControlAccesos.dto;

import java.sql.Time;

import javax.persistence.SqlResultSetMapping;

public class ControlDiarioDto {
	
	
	private Time entrada;

	private Time salida;

	private Time tiempo;

	public ControlDiarioDto() {
		// TODO Auto-generated constructor stub
	}





	public ControlDiarioDto(Time entrada, Time salida, Time tiempo) {
		super();
		this.entrada = entrada;
		this.salida = salida;
		this.tiempo = tiempo ;
	}





	public Time getEntrada() {
		return entrada;
	}

	public void setEntrada(Time entrada) {
		this.entrada = entrada;
	}

	public Time getSalida() {
		return salida;
	}

	public void setSalida(Time salida) {
		this.salida = salida;
	}

	public Time getTiempo() {
		return tiempo;
	}

	public void setTiempo(Time tiempo) {
		this.tiempo = tiempo;
	}

	@Override
	public String toString() {
		return "ControlDiarioDto [entrada=" + entrada + ", salida=" + salida + ", tiempo=" + tiempo + "]";
	}

	
	
}
