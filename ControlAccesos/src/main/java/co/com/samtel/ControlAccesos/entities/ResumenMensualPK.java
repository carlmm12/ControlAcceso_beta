package co.com.samtel.ControlAccesos.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tblresumenes_mensual database table.
 * 
 */
@Embeddable
public class ResumenMensualPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int codigo;

	@Column(name="tipo_alerta")
	private int tipoAlerta;

	public ResumenMensualPK() {
	}
	
	
	public ResumenMensualPK(int codigo, int tipoAlerta) {
		super();
		this.codigo = codigo;
		this.tipoAlerta = tipoAlerta;
	}


	public int getCodigo() {
		return this.codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getTipoAlerta() {
		return this.tipoAlerta;
	}
	public void setTipoAlerta(int tipoAlerta) {
		this.tipoAlerta = tipoAlerta;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ResumenMensualPK)) {
			return false;
		}
		ResumenMensualPK castOther = (ResumenMensualPK)other;
		return 
			(this.codigo == castOther.codigo)
			&& (this.tipoAlerta == castOther.tipoAlerta);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigo;
		hash = hash * prime + this.tipoAlerta;
		
		return hash;
	}


	@Override
	public String toString() {
		return "ResumenMensualPK [codigo=" + codigo + ", tipoAlerta=" + tipoAlerta + "]";
	}
	
	
}