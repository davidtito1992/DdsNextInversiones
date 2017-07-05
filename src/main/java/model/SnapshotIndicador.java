package model;
import java.time.Year;
import org.uqbar.commons.utils.Observable;

@Observable
public class SnapshotIndicador {

	/********* ATRIBUTOS *********/

	private String nombreEmpresa;
	private Year anio;
	private int semestre;
	private String resultado;

	/********* GETTERS/SETTERS *********/

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombre) {
		this.nombreEmpresa = nombre;
	}

	public Year getAnio() {
		return anio;
	}

	public void setAnio(Year year) {
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
}