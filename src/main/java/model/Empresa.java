package model;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

@SuppressWarnings("serial")
@Transactional
@Observable
public class Empresa extends Entity {

	/********* ATRIBUTOS *********/

	private String nombre;
	private List<Periodo> periodos;

	/********* GETTERS/SETTERS *********/

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
	
	/********* METODOS *********/
	
	public int getAntiguedadEmpresa(){
		Date fecha = new Date();
		int anioActual = fecha.getYear();
		ArrayList<Year> aniosPeriodos = (ArrayList<Year>) this.getPeriodos().stream()
		.map(periodo -> periodo.getAnio()).distinct().sorted().collect(Collectors.toCollection(ArrayList::new));
				
		return anioActual - aniosPeriodos.get(0).getValue();
	}

}
