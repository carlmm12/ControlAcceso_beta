package co.com.samtel.ControlAccesos.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tblusuarios database table.
 * 
 */
@Entity
@Table(name="tblusuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String cedula;

	private int estado;

	private String nombre;

	//bi-directional one-to-one association to CodigoUsuario
	@OneToOne(mappedBy="tblusuario", fetch=FetchType.LAZY)
	private CodigoUsuario tblcodigoUsuario;

	public Usuario() {
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public CodigoUsuario getTblcodigoUsuario() {
		return this.tblcodigoUsuario;
	}

	public void setTblcodigoUsuario(CodigoUsuario tblcodigoUsuario) {
		this.tblcodigoUsuario = tblcodigoUsuario;
	}

}