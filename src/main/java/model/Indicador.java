package model;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

@SuppressWarnings("serial")
@Transactional
@Observable
public class Indicador extends Entity {

	/********* ATRIBUTOS *********/

	private String nombre;
	private String formula;

	public Indicador(String nombre, String formula) {
		this.nombre = nombre;
		this.formula = formula;
	}

	/********* GETTERS/SETTERS *********/

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	/********* METODOS *********/

}
