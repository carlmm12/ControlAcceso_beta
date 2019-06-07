package co.com.samtel.ControlAccesos.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tblresumenes_mensual database table.
 * 
 */
@Entity
@Table(name="tblresumenes_mensual")
@NamedQuery(name="ResumenMensual.findAll", query="SELECT r FROM ResumenMensual r")
public class ResumenMensual implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ResumenMensualPK id;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="nro_alertas")
	private int nroAlertas;

	@Column(name="porcentaje_alertas")
	private int porcentajeAlertas;

	@Column(name="total_dias")
	private int totalDias;

	private String usuario;

	public ResumenMensual() {
	}

	
	
	public ResumenMensual(ResumenMensualPK id, Date fecha, int nroAlertas, int porcentajeAlertas, int totalDias,
			String usuario) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.nroAlertas = nroAlertas;
		this.porcentajeAlertas = porcentajeAlertas;
		this.totalDias = totalDias;
		this.usuario = usuario;
	}



	public ResumenMensualPK getId() {
		return this.id;
	}

	public void setId(ResumenMensualPK id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getNroAlertas() {
		return this.nroAlertas;
	}

	public void setNroAlertas(int nroAlertas) {
		this.nroAlertas = nroAlertas;
	}

	public int getPorcentajeAlertas() {
		return this.porcentajeAlertas;
	}

	public void setPorcentajeAlertas(int porcentajeAlertas) {
		this.porcentajeAlertas = porcentajeAlertas;
	}

	public int getTotalDias() {
		return this.totalDias;
	}

	public void setTotalDias(int totalDias) {
		this.totalDias = totalDias;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	@Override
	public String toString() {
		return "ResumenMensual [id=" + id + ", fecha=" + fecha + ", nroAlertas=" + nroAlertas + ", porcentajeAlertas="
				+ porcentajeAlertas + ", totalDias=" + totalDias + ", usuario=" + usuario + "]";
	}
	
	

}