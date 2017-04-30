package model;

import java.util.Collection;

import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;
@SuppressWarnings("serial")
@Transactional
@Observable
public class Periodo {

	/********* ATRIBUTOS *********/
	
	private int año;
	private int semestre;
	private Collection<Cuenta> cuentas;

	/********* GETTERS/SETTERS *********/
	
	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public Collection<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Collection<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
}
