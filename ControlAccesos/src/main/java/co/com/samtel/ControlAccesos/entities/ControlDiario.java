package co.com.samtel.ControlAccesos.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the tblcontrol_diario database table.
 * 
 */
@Entity
@Table(name="tblcontrol_diario")
@NamedQuery(name="ControlDiario.findAll", query="SELECT c FROM ControlDiario c")
public class ControlDiario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="codigo_usuario")
	private int codigoUsuario;

	private Time entrada;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String nombre;

	private Time salida;

	private Time tiempo;

	public ControlDiario() {
	}
	

	public ControlDiario(int id, int codigoUsuario, Time entrada, Date fecha, String nombre, Time salida, Time tiempo) {
		super();
		this.id = id;
		this.codigoUsuario = codigoUsuario;
		this.entrada = entrada;
		this.fecha = fecha;
		this.nombre = nombre;
		this.salida = salida;
		this.tiempo = tiempo;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigoUsuario() {
		return this.codigoUsuario;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}



	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		return this.tiempo;
	}

	public void setTiempo(Time tiempo) {
		this.tiempo = tiempo;
	}


	@Override
	public String toString() {
		return "ControlDiario [id=" + id + ", codigoUsuario=" + codigoUsuario + ", entrada=" + entrada + ", fecha="
				+ fecha + ", nombre=" + nombre + ", salida=" + salida + ", tiempo=" + tiempo + "]";
	}

	
}