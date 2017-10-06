package model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

@Transactional
@Observable
@Table(name= "Indicadores")
@Entity
public class RegistroIndicador {
	
	public RegistroIndicador(){
	}
	
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

	/********* ATRIBUTOS *********/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long registroIndicadorId;

	private String nombre;
	
	private String formula;
	
	@ElementCollection
	private List<String> variables;

	/********* GETTERS/SETTERS *********/
	
	public String getNombre() {
		return nombre;
	}
	
	public Long getRegistroIndicadorId() {
		return registroIndicadorId;
	}

	public void setRegistroIndicadorId(Long registroIndicadorId){
		this.registroIndicadorId = registroIndicadorId;
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