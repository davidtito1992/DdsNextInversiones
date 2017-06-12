package model;

import org.uqbar.commons.utils.Observable;

import java.math.BigDecimal;

@Observable
public class Cuenta {

	/********* ATRIBUTOS *********/
	
	private String nombre;
	private double valor;

	/********* GETTERS/SETTERS *********/
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getValor() {
		return BigDecimal.valueOf(valor);
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor.doubleValue();
	}
	
	/********* METODOS *********/

	public Cuenta(String nombre, BigDecimal valor) {
		this.nombre = nombre;
		this.valor = valor.doubleValue();
	}
	
}
