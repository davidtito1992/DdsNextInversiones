package model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import main.repositories.RepositorioIndicador;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

@Transactional
@Observable
@Table(name = "Indicadores")
@Entity
public class RegistroIndicador {

	public RegistroIndicador() {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User user;

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

	public void setRegistroIndicadorId(Long registroIndicadorId) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/********* METODOS *********/

	public String getUrl() {
		return this.registroIndicadorId.toString();
	}

	public Boolean getPuedeSerBorrado() {
		return !RepositorioIndicador.getSingletonInstance().existCondicionWith(
				this.getRegistroIndicadorId());
	}
}