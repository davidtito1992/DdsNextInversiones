package model;

import java.util.ArrayList;
import java.util.Collection;


public class Empresa {
	
	private String nombre;
	
	//Para Probar ****/
	public Empresa (String nombre){
		
		this.setNombre(nombre);
	}
	/*****/
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	private Collection<Cuenta> cuentas;
}
