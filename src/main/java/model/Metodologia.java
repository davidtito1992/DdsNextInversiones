package model;

import java.util.List;

import org.uqbar.commons.model.Entity;

public class Metodologia extends Entity {
	
	/********* ATRIBUTOS *********/
	
	public String nombre;
	public List<CondicionMetodologia> condiciones;

	/********* GETTERS/SETTERS *********/
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
