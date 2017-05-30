package domaintest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.Cuenta;
import model.Indicador;

import org.junit.Before;
import org.junit.Test;

public class AnalizadorDeIndicadoresTest {
	ArrayList<Indicador> repoIndicadores;
	Indicador indicadorSimple;
	Indicador indicadorComplejo;
    @Before public void initialize() {
    	repoIndicadores= new ArrayList<Indicador>();
		indicadorSimple = new Indicador("IndicadorSimple","EBITDA + FDS");
		indicadorComplejo = new Indicador("IndicadorComplejo","IndicadorSimple");
		repoIndicadores.add(indicadorSimple);
		repoIndicadores.add(indicadorComplejo);
    }
    
	@Test
	public void CalculoIndicadorSimpleTest(){

		List<Cuenta> listaDeCuentas = new ArrayList<Cuenta>();
		listaDeCuentas.add(new Cuenta("EBITDA",10));
		listaDeCuentas.add(new Cuenta("FDS",20));
		assertEquals("30.00",indicadorSimple.analizarResultadoTest(listaDeCuentas,repoIndicadores).toString());
	}
	
	
	@Test
	public void CalculoIndicadorComplejoTest(){
		List<Cuenta> listaDeCuentas = new ArrayList<Cuenta>();
		listaDeCuentas.add(new Cuenta("EBITDA",10));
		listaDeCuentas.add(new Cuenta("FDS",20));
		assertEquals("30.00",indicadorComplejo.analizarResultadoTest(listaDeCuentas,repoIndicadores).toString());
	}
	
}
