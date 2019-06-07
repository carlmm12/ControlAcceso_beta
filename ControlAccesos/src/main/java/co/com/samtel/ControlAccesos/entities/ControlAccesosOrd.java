package co.com.samtel.ControlAccesos.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tblcontrol_accesos_ord database table.
 * 
 */
@Entity
@Table(name="tblcontrol_accesos_ord")
@NamedQuery(name="ControlAccesosOrd.findAll", query="SELECT c FROM ControlAccesosOrd c")
public class ControlAccesosOrd implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ControlAccesosOrdPK id;

	@Column(name="tipo_acceso")
	private int tipoAcceso;

	//bi-directional many-to-one association to CodigoUsuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tblcodigo_usuarios_codigo" , insertable=false, updatable=false)
	private CodigoUsuario tblcodigoUsuario;

	public ControlAccesosOrd() {
	}

	
	public ControlAccesosOrd(ControlAccesosOrdPK id, int tipoAcceso, CodigoUsuario tblcodigoUsuario) {
		super();
		this.id = id;
		this.tipoAcceso = tipoAcceso;
		this.tblcodigoUsuario = tblcodigoUsuario;
	}


	public ControlAccesosOrdPK getId() {
		return this.id;
	}

	public void setId(ControlAccesosOrdPK id) {
		this.id = id;
	}

	public int getTipoAcceso() {
		return this.tipoAcceso;
	}

	public void setTipoAcceso(int tipoAcceso) {
		this.tipoAcceso = tipoAcceso;
	}

	public CodigoUsuario getTblcodigoUsuario() {
		return this.tblcodigoUsuario;
	}

	public void setTblcodigoUsuario(CodigoUsuario tblcodigoUsuario) {
		this.tblcodigoUsuario = tblcodigoUsuario;
	}


	@Override
	public String toString() {
		return "ControlAccesosOrd [id=" + id + ", tipoAcceso=" + tipoAcceso + ", tblcodigoUsuario=" + tblcodigoUsuario
				+ "]";
	}
	
	

}