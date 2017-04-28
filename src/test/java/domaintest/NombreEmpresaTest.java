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
		
		nombresTodasLasCuentas.add("EBITDA");
		nombresTodasLasCuentas.add("FDS");
		nombresTodasLasCuentas.add("FreeCashFlow");
		nombresTodasLasCuentas.add("Neto Continuas");
		nombresTodasLasCuentas.add("Neto Discontinuas");
		
		CantidadTodosLosAnios.add(2014);
		CantidadTodosLosAnios.add(2015);
		CantidadTodosLosAnios.add(2016);

		Mixin.cargarEmpresas();
	}

	@Test
	public void NombresDeTodasLasCuentasTest() {
		assertEquals(nombresTodasLasCuentas, RepositorioMaestro.dameCuentasEmpresas());
	}
	
	@Test
	public void CantidadDeTodosLosAniosTest() {
		assertEquals(CantidadTodosLosAnios, RepositorioMaestro.dameAniosPeriodos());
	}


	@Test	
	public void NombreDeTodasLasEmpresaTest() {
		
		assertEquals(nombresEsperados, RepositorioMaestro.dameNombresEmpresas());
	}
	

}

