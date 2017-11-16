package model;

import java.time.Year;

import javax.persistence.Convert;

import org.uqbar.commons.utils.Observable;

import main.converter.YearConverter;

@Observable
public class SnapshotIndicador {

	/********* ATRIBUTOS *********/

	private RegistroIndicador registroIndicador;
	private String nombreEmpresa;
	@Convert(converter = YearConverter.class)
	private Integer anio;
	private int semestre;
	private String resultado;

	/********* GETTERS/SETTERS *********/

	public SnapshotIndicador(RegistroIndicador registroIndicador,
			String nombreEmpresa, Integer anio, int semestre, String resultado) {
		super();
		this.registroIndicador = registroIndicador;
		this.nombreEmpresa = nombreEmpresa;
		this.anio = anio;
		this.semestre = semestre;
		this.resultado = resultado;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombre) {
		this.nombreEmpresa = nombre;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer year) {
		this.anio = year;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String string) {
		this.resultado = string;
	}

	public RegistroIndicador getRegistroIndicador() {
		return registroIndicador;
	}

	public void setRegistroIndicador(RegistroIndicador registroIndicador) {
		this.registroIndicador = registroIndicador;
	}

}
