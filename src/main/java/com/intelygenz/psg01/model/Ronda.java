package com.intelygenz.psg01.model;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Ronda implements  Serializable{

	private static final long serialVersionUID = 2776645181429901373L;
	
	@Override
	public boolean equals(Object obj) {
		if((obj instanceof Ronda) 
				&& ((Ronda)obj).getId() == this.id
				&& ((Ronda)obj).getCodigo() == this.codigo)
				{
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int hash = 1;
		hash += this.id;
		hash += this.codigo;
		return hash;
	}	
	
	
	private int id;
	private long codigo;
	
	private Date activacion;
	private Date proceso;
	private Date aviso;
	
	private List<Tarea> al_Tarea = new ArrayList<Tarea>();
	
	public List<Tarea> getAl_Tarea() {
		return al_Tarea;
	}

	public void setAl_Tarea(List<Tarea> al_Tarea) {
		this.al_Tarea = al_Tarea;
	}

	public Ronda(){

	}

	public void finalize() throws Throwable {

	}

	public int getId(){
		return id;
	}

	public void setId(int newVal){
		id = newVal;
	}

	public long getCodigo(){
		return codigo;
	}

	public void setCodigo(long newVal){
		codigo = newVal;
	}
	
	public Date getActivacion(){
		return activacion;
	}

	public void setActivacion(Date newVal){
		activacion = newVal;
	}
	
	public Date getProceso(){
		return proceso;
	}

	public void setProceso(Date newVal){
		proceso = newVal;
	}
	
	public Date getAviso(){
		return aviso;
	}

	public void setAviso(Date newVal){
		aviso = newVal;
	}
	

}