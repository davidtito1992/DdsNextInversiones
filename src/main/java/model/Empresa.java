package model;

import java.util.Collection;


public class Empresa {
	
	private String nombre;
	private Collection<Periodo> periodos;
	
	/*****/
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Collection<Periodo> getPeriodos() {
		return periodos;
	}
	public void setPeriodos(Collection<Periodo> periodos) {
		this.periodos = periodos;
	}

}
