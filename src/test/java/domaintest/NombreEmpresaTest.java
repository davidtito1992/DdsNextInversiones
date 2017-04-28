package domaintest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.*;

import org.junit.Before;
import org.junit.Test;

import app.*;


public class NombreEmpresaTest {
	
	private ArrayList<String> nombresEsperados = new ArrayList<String>();
	private ArrayList<String> nombresTodasLasCuentas= new ArrayList<String>();
	private ArrayList<Integer> CantidadTodosLosAnios= new ArrayList<Integer>(); 
	
	@Before
	public void generarNombresEsperados(){
		nombresEsperados.add("Facebook");
		nombresEsperados.add("Google");
		nombresEsperados.add("Twitter");
		
	}
	
	public void generarNombresTodasLasCuentas(){
		nombresTodasLasCuentas.add("EBITDA");
		nombresTodasLasCuentas.add("FDS");
		nombresTodasLasCuentas.add("FreeCashFlow");
		nombresTodasLasCuentas.add("Neto Continuas");
		nombresTodasLasCuentas.add("Neto Discontinuas");
	}
	public void generarCantidadDeTodasAnios(){
		CantidadTodosLosAnios.add(2014);
		CantidadTodosLosAnios.add(2015);
		CantidadTodosLosAnios.add(2016);
		
	}
	
	@Test	
	public void NombreEmpresaTest() {
		Mixin.cargarEmpresas();
		assertEquals(nombresEsperados, RepositorioMaestro.dameNombresEmpresas());
	}
	
	public void NombresDeTodasLasCuentasTest() {
		Mixin.cargarEmpresas();
		assertEquals(nombresTodasLasCuentas, RepositorioMaestro.dameCuentasEmpresas());
	}
	
	public void CantidadDeTodosLosAniosTest() {
		Mixin.cargarEmpresas();
		assertEquals(nombresTodasLasCuentas, RepositorioMaestro.dameCuentasEmpresas());
	}

}

