package model;

import java.util.List;
import java.util.stream.Collectors;

import javaccCalculator.ArithmeticParser;
import javaccCalculator.ParseException;
import model.Indicador;

import org.uqbar.commons.utils.ApplicationContext;
import org.uqbar.commons.utils.Observable;

import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;
import calculator.Value;

@Observable
public class SnapshotIndicador {

	/********* ATRIBUTOS *********/

	private String nombre;
	private int año;
	private int semestre;
	private Value resultado;

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

	public Value getResultado() {
		return resultado;
	}

	public void setResultado(Value resultado) {
		this.resultado = resultado;
	}
}
