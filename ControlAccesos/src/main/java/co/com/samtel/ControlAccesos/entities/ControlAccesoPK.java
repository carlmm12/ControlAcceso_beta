package co.com.samtel.ControlAccesos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The primary key class for the tblcontrol_accesos database table.
 * 
 */
@Embeddable
public class ControlAccesoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date datetime;

	private int enno;

	public ControlAccesoPK() {
	}
	
	
	public ControlAccesoPK(Date datetime, int enno) {
		super();
		this.datetime = datetime;
		this.enno = enno;
	}


	public java.util.Date getDatetime() {
		return this.datetime;
	}
	public void setDatetime(java.util.Date datetime) {
		this.datetime = datetime;
	}
	public int getEnno() {
		return this.enno;
	}
	public void setEnno(int enno) {
		this.enno = enno;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ControlAccesoPK)) {
			return false;
		}
		ControlAccesoPK castOther = (ControlAccesoPK)other;
		return 
			this.datetime.equals(castOther.datetime)
			&& (this.enno == castOther.enno);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.datetime.hashCode();
		hash = hash * prime + this.enno;
		
		return hash;
	}


	@Override
	public String toString() {
		return "ControlAccesoPK [datetime=" + datetime + ", enno=" + enno + "]";
	}
	
	
}