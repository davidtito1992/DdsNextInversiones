package model;

import java.util.List;

import parserIndicador.ParseException;
import condiciones.CondicionCualitativa;
import condiciones.CondicionTaxativa;

public class Metodologia{
	
	/********* ATRIBUTOS *********/
	
	public String nombre;
	public List<CondicionTaxativa> condicionesTaxativas;
	public List<CondicionCualitativa> condicionesCualitativas;

	/********* GETTERS/SETTERS *********/
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int analizarResultado(Empresa empresa) throws ParseException{
		if(!analizarCondicionesTaxativas(empresa)){
			return 0;
		} else {
			return analizarCondicionesCualitativas(empresa);
		}
	}

	private boolean analizarCondicionesTaxativas(Empresa empresa) throws ParseException{
		for (int i = 0; i < condicionesTaxativas.size(); i++) {
			if(!condicionesTaxativas.get(i).calcular(empresa)){
				return false;
			}
		}
		return true;
	}
	
	private int analizarCondicionesCualitativas(Empresa empresa) throws ParseException {
		int acumulador = 0;
		for (int i = 0; i < condicionesCualitativas.size(); i++) {
			acumulador += condicionesCualitativas.get(i).calcular(empresa);
		}
		return acumulador;
	}
}
