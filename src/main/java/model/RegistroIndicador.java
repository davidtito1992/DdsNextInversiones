package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;


@SuppressWarnings("serial")
@Transactional
@Observable
@Table(name= "RegistroIndicador")
@Entity
public class RegistroIndicador {

	/********* ATRIBUTOS *********/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "registroIndicadorId")
	private Long registroIndicadorId;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "formula")
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
	
	public RegistroIndicador(){
		
	}
	
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