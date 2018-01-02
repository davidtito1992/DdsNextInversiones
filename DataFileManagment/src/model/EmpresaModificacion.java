package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


import main.repositories.RepositorioUsuario;

public class EmpresaModificacion {
	/********* ATRIBUTOS *********/

	private String userMail;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empresaId;

	private String nombre;

	@JoinColumn
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Periodo> periodos;

	/********* GETTERS/SETTERS *********/

	public EmpresaModificacion() {

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

	public String getUser() {
		return userMail;
	}

	public void setUser(String user) {
		this.userMail = user;
	}
	
	/********* METODOS *********/
	
	public Long getUserId() {
		return RepositorioUsuario.getSingletonInstance().getUser(this.userMail).getUserId();

	}
}
