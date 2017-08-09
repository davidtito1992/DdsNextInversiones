package model;

import java.util.ArrayList;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.Observable;

import condiciones.Condicion;

@SuppressWarnings("serial")
@Observable
public class Metodologia extends Entity {

	// public Metodologia(String nombre, List<CondicionTaxativa> condTaxativas,
	// List<CondicionCualitativa> condCualitativas) {
	// this.nombre = nombre;
	// this.condicionesTaxativas = condTaxativas;
	// this.condicionesCualitativas = condCualitativas;
	// }
	public Metodologia(String nombre,
			ArrayList<Condicion> condiciones) {
		this.nombre = nombre;
		// this.condicionesTaxativas = condTaxativas;
		// this.condicionesCualitativas = condCualitativas;
		this.condiciones = condiciones;

	}

	/********* ATRIBUTOS *********/

	public String nombre;
	public ArrayList<Condicion> condiciones;

	/********* GETTERS/SETTERS *********/

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Condicion> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(
			ArrayList<Condicion> condiciones) {
		this.condiciones = condiciones;
	}
	public void calcular() {

	}


}
