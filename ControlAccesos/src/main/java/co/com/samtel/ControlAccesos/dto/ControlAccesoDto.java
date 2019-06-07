package co.com.samtel.ControlAccesos.dto;

import java.util.Date;



public class ControlAccesoDto {
	
	private Integer tmno;
	private long enno;
	private String name;
	private Integer inout;
	private Integer mode; 
	private Date datetime;
	
	public ControlAccesoDto() {
		
	}

	public Integer getTmno() {
		return tmno;
	}

	public void setTmno(Integer tmno) {
		this.tmno = tmno;
	}

	public long getEnno() {
		return enno;
	}

	public void setEnno(long enno) {
		this.enno = enno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getInout() {
		return inout;
	}

	public void setInout(Integer inout) {
		this.inout = inout;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	} 
	
	
	
	

}
