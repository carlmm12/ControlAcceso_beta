package co.com.samtel.ControlAccesos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The primary key class for the tblcontrol_accesos_ord database table.
 * 
 */
@Embeddable
public class ControlAccesosOrdPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date fecha;

	@Column(name="tblcodigo_usuarios_codigo", insertable=false, updatable=false)
	private int tblcodigoUsuariosCodigo;

	public ControlAccesosOrdPK() {
	}
	
	
	
	public ControlAccesosOrdPK(int id, Date fecha, int tblcodigoUsuariosCodigo) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.tblcodigoUsuariosCodigo = tblcodigoUsuariosCodigo;
	}



	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public java.util.Date getFecha() {
		return this.fecha;
	}
	public void setFecha(java.util.Date fecha) {
		this.fecha = fecha;
	}
	public int getTblcodigoUsuariosCodigo() {
		return this.tblcodigoUsuariosCodigo;
	}
	public void setTblcodigoUsuariosCodigo(int tblcodigoUsuariosCodigo) {
		this.tblcodigoUsuariosCodigo = tblcodigoUsuariosCodigo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ControlAccesosOrdPK)) {
			return false;
		}
		ControlAccesosOrdPK castOther = (ControlAccesosOrdPK)other;
		return 
			(this.id == castOther.id)
			&& this.fecha.equals(castOther.fecha)
			&& (this.tblcodigoUsuariosCodigo == castOther.tblcodigoUsuariosCodigo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.fecha.hashCode();
		hash = hash * prime + this.tblcodigoUsuariosCodigo;
		
		return hash;
	}



	@Override
	public String toString() {
		return "ControlAccesosOrdPK [id=" + id + ", fecha=" + fecha + ", tblcodigoUsuariosCodigo="
				+ tblcodigoUsuariosCodigo + "]";
	}
	
	
}
