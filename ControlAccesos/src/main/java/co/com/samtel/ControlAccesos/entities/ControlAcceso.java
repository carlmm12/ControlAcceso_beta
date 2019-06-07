package co.com.samtel.ControlAccesos.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tblcontrol_accesos database table.
 * 
 */
@Entity
@Table(name="tblcontrol_accesos")
@NamedQuery(name="ControlAcceso.findAll", query="SELECT c FROM ControlAcceso c")
public class ControlAcceso implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ControlAccesoPK id;

	private int inout;

	private int mode;

	private String name;

	private int tmno;

	public ControlAcceso() {
	}
	
	

	public ControlAcceso(ControlAccesoPK id, int inout, int mode, String name, int tmno) {
		super();
		this.id = id;
		this.inout = inout;
		this.mode = mode;
		this.name = name;
		this.tmno = tmno;
	}



	public ControlAccesoPK getId() {
		return this.id;
	}

	public void setId(ControlAccesoPK id) {
		this.id = id;
	}

	public int getInout() {
		return this.inout;
	}

	public void setInout(int inout) {
		this.inout = inout;
	}

	public int getMode() {
		return this.mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTmno() {
		return this.tmno;
	}

	public void setTmno(int tmno) {
		this.tmno = tmno;
	}



	@Override
	public String toString() {
		return "ControlAcceso [id=" + id + ", inout=" + inout + ", mode=" + mode + ", name=" + name + ", tmno=" + tmno
				+ "]";
	}
	
	

}