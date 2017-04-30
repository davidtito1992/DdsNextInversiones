package model;

import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

@SuppressWarnings("serial")
@Transactional
@Observable
public class Cuenta {

	/********* ATRIBUTOS *********/
	private String nombre;
	private int valor;

	/********* GETTERS/SETTERS *********/
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}
