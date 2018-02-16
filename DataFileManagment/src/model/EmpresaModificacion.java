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

	private String nombre;

	private List<Periodo> periodos;

	/********* GETTERS/SETTERS *********/

	public EmpresaModificacion() {

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
