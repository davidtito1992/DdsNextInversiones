package model;

import java.util.List;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

@SuppressWarnings("serial")
@Transactional
@Observable
public class RegistroIndicador extends Entity {

	/********* ATRIBUTOS *********/

	private String nombre;
	private String formula;
	private List<String> variables;

	public RegistroIndicador(String nombre, String formula) {
		this.nombre = nombre;
		this.formula = formula;
	}

	public RegistroIndicador(String nombre, String formula,
			List<String> variables) {
		this.nombre = nombre;
		this.formula = formula;
		this.variables = variables;
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

	public List<String> getVariables() {
		return variables;
	}

	public void setVariables(List<String> variables) {
		this.variables = variables;
	}
	/********* METODOS *********/

}
