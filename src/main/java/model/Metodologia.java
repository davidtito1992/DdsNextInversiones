package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.model.Entity;
import org.uqbar.commons.utils.Observable;

import parserIndicador.ParseException;
import RankingEmpresa.RankingEmpresa;
import RankingEmpresa.RankingEmpresasComparator;
import condiciones.Condicion;

@SuppressWarnings("serial")
@Observable
public class Metodologia extends Entity {

	public Metodologia(String nombre,
			List<Condicion> condiciones) {
		this.nombre = nombre;
		this.condiciones = condiciones;

	}

	/********* ATRIBUTOS *********/

	public String nombre;
	public List<Condicion> condiciones;

	/********* GETTERS/SETTERS *********/

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(
			List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}

	public RankingEmpresa calcularEmpresa(RankingEmpresa rEmpresa){
		try{
			for (Condicion condicion : condiciones) {
			    rEmpresa = condicion.calcular(rEmpresa);
			}
		}catch (ParseException e){
			//cuando no se cumple una taxativa
			rEmpresa.setErrorTaxativa(true);
			rEmpresa.setObservaciones("La condicion taxativa que no se cumplió fue: " + e.getMessage());
		}catch (RuntimeException e){
			//cuando falla una condicion o calculo de indicador
			rEmpresa.setErrorTaxativa(true);
			rEmpresa.setObservaciones("No se pudo evaluar una condicion sobre la empresa. Error: " + e.getMessage());
		}
		return rEmpresa;
	}
	
	public List<RankingEmpresa> calcularEmpresas(List<RankingEmpresa> rEmpresas) {
		return rEmpresas.stream()
				.map(empresa->calcularEmpresa(empresa))
				.sorted(new RankingEmpresasComparator())
				.collect(Collectors.toList());
	}


}
