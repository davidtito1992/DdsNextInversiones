package model;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import main.repositories.RepositorioEmpresa;

import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

@Entity
@Table(name = "Empresas")
@Transactional
@Observable
public class Empresa {

	/********* ATRIBUTOS *********/

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User user;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empresaId;

	private String nombre;

	@JoinColumn
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Periodo> periodos;

	/********* GETTERS/SETTERS *********/

	public Empresa() {

	}

	public Empresa(String nombre, List<Periodo> periodos, User user) {
		try {
			this.empresaId = RepositorioEmpresa.getInstance().getEmpresaId(
					nombre, user);
		} catch (Exception e) {
			this.empresaId = null;
		}
		this.nombre = nombre;
		this.periodos = periodos;
		this.user = user;
	}

	public Long getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Periodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<Periodo> periodos) {
		this.periodos = periodos;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
