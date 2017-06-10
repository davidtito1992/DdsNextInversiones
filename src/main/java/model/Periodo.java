package model;

import java.time.Year;
import java.util.List;

import org.uqbar.commons.utils.Observable;

@Observable
public class Periodo {

	/********* ATRIBUTOS *********/

	private Year año;
	private int semestre;
	private List<Cuenta> cuentas;

	/********* GETTERS/SETTERS *********/

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public Year getAño() {
		return año;
	}

	public void setAño(Year año) {
		this.año = año;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
}
