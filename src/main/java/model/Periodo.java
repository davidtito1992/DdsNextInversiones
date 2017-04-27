package model;

import java.util.Collection;

public class Periodo {

	private int año;
	private int semestre;
	private Collection<Cuenta> cuentas;
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
