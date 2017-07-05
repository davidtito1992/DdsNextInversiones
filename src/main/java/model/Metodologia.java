package model;

import java.util.List;

public class Metodologia {
	
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
