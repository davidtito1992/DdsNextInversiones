package model;

import java.math.BigDecimal;

import org.uqbar.commons.utils.Observable;

@Observable
public class SnapshotIndicador {

	/********* ATRIBUTOS *********/

	private String nombre;
	private int año;
	private int semestre;
	private BigDecimal resultado;

	/********* GETTERS/SETTERS *********/

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public BigDecimal getResultado() {
		return resultado;
	}

	public void setResultado(BigDecimal resultado) {
		this.resultado = resultado;
	}
}
