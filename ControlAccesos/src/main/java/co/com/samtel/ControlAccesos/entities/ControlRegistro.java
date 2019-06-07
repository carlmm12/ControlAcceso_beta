package co.com.samtel.ControlAccesos.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the tblcontrol_registros database table.
 * 
 */
@Entity
@Table(name="tblcontrol_registros")
@NamedQuery(name="ControlRegistro.findAll", query="SELECT c FROM ControlRegistro c")
public class ControlRegistro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="numero_registros_diarios", nullable = false)
	private int numeroResgitrosDiarios;
	
	@Column(name="tiempo_no_laborado")
	private Time tiempoNoLaborado;	
	
	@Column(name="tiempo_de_permanencia")
	private Time tiempoPermanencia;	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tblcontrol_diario_id", nullable = false)
	private ControlDiario controlDiario;	

	
	public ControlRegistro() {
	}
	


	public ControlRegistro(int numeroResgitrosDiarios, Time tiempoNoLaborado, Time tiempoPermanencia,
			ControlDiario controlDiario) {
		super();
		this.numeroResgitrosDiarios = numeroResgitrosDiarios;
		this.tiempoNoLaborado = tiempoNoLaborado;
		this.tiempoPermanencia = tiempoPermanencia;
		this.controlDiario = controlDiario;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getNumeroResgitrosDiarios() {
		return numeroResgitrosDiarios;
	}


	public void setNumeroResgitrosDiarios(int numeroResgitrosDiarios) {
		this.numeroResgitrosDiarios = numeroResgitrosDiarios;
	}


	public Time getTiempoNoLaborado() {
		return tiempoNoLaborado;
	}


	public void setTiempoNoLaborado(Time tiempoNoLaborado) {
		this.tiempoNoLaborado = tiempoNoLaborado;
	}


	public ControlDiario getControlDiario() {
		return controlDiario;
	}


	public void setControlDiario(ControlDiario controlDiario) {
		this.controlDiario = controlDiario;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	
	}
	



	public Time getTiempoPermanencia() {
		return tiempoPermanencia;
	}



	public void setTiempoPermanencia(Time tiempoPermanencia) {
		this.tiempoPermanencia = tiempoPermanencia;
	}



	@Override
	public String toString() {
		return "ControlRegistro [id=" + id + ", numeroResgitrosDiarios=" + numeroResgitrosDiarios
				+ ", tiempoNoLaborado=" + tiempoNoLaborado + ", tiempoPermanencia=" + tiempoPermanencia
				+ ", controlDiario=" + controlDiario + "]";
	}
	
	


	
}
