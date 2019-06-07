package co.com.samtel.ControlAccesos.dto;

import javax.persistence.Column;
import javax.persistence.Id;



public class UsuarioDto {
	
	private Integer id;
	private String nombreCompleto;
	private String estado;
	
	public UsuarioDto() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}
