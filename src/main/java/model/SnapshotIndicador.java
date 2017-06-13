package model;

import java.math.BigDecimal;

import org.uqbar.commons.utils.Observable;

@Observable
public class SnapshotIndicador {

	/********* ATRIBUTOS *********/

	private String nombre;
	private int anio;
	private int semestre;
	private BigDecimal resultado;

	/********* GETTERS/SETTERS *********/

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
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
