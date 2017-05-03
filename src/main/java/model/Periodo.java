package model;

import java.util.List;
import org.uqbar.commons.utils.Observable;

@Observable
public class Periodo {

	/********* ATRIBUTOS *********/
	
	private int año;
	private int semestre;
	private List<Cuenta> cuentas;

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

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
}
