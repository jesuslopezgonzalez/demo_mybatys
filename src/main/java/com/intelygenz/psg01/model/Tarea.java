package com.intelygenz.psg01.model;

import java.util.Date;

public class Tarea {

	private int id;
	private int fkIdRonda;
	private String nombre;
	private boolean estado;
	
	private String nameImage;
	private String pathImage;
	public String image;
	
	private Date fechaRegistro;
	

	public Tarea(){

	}
	public void finalize() throws Throwable {

	}

	public long getId(){
		return id;
	}
	public void setId(int newVal){
		id = newVal;
	}

	public int getFkIdRonda(){
		return fkIdRonda;
	}
	public void setFkIdRonda(int newVal){
		fkIdRonda = newVal;
	}
	
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String newVal){
		nombre = newVal;
	}
	
	public boolean getEstado(){
		return estado;
	}
	public void setEstado(boolean newVal){
		estado = newVal;
	}

	
	public String getNameImage(){
		return nameImage;
	}
	public void setNameImage(String newVal){
		nameImage = newVal;
	}
	public String getPath(){
		return pathImage;
	}
	public void setPath(String newVal){
		pathImage = newVal;
	}
	public String getImage(){
		return image;
	}
	public void setImage(String string){
		image = string;
	}
	
	public Date getFechaRegistro(){
		return fechaRegistro;
	}
	public void setFechaRegistro(Date newVal){
		fechaRegistro = newVal;
	}
}