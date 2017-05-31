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
    Indicador indicadorIngresoNeto;
	@Before
	public void initialize() {
		repoIndicadores = new ArrayList<Indicador>();
		indicadorSimple = new Indicador("IndicadorSimple", "EBITDA + FDS");
		indicadorIngresoNeto = new Indicador("Ingreso Neto", "NetoContinuas + NetoDiscontinuas");
		indicadorComplejo = new Indicador("IndicadorComplejo",
				"IndicadorSimple * 3");
		repoIndicadores.add(indicadorSimple);
		repoIndicadores.add(indicadorComplejo);
		repoIndicadores.add(indicadorIngresoNeto);
	}

	@Test
	public void CalculoIndicadorSimpleTest() {

		List<Cuenta> listaDeCuentas = new ArrayList<Cuenta>();
		listaDeCuentas.add(new Cuenta("EBITDA", 10));
		listaDeCuentas.add(new Cuenta("FDS", 20));
		assertEquals("30.0", String.valueOf(indicadorSimple.analizarResultadoTest(
				listaDeCuentas, repoIndicadores)));
	}

	@Test
	public void CalculoIndicadorComplejoTest() {
		List<Cuenta> listaDeCuentas = new ArrayList<Cuenta>();
		listaDeCuentas.add(new Cuenta("EBITDA", 10));
		listaDeCuentas.add(new Cuenta("FDS", 20));
		assertEquals("90.0", String.valueOf(indicadorComplejo.analizarResultadoTest(
				listaDeCuentas, repoIndicadores)));
	}
	
	@Test
	public void CalculoIndicadorIngresoNeto() {

		List<Cuenta> listaDeCuentas = new ArrayList<Cuenta>();
		listaDeCuentas.add(new Cuenta("NetoDiscontinuas", 10));
		listaDeCuentas.add(new Cuenta("NetoContinuas", 20));
		assertEquals("30.0", String.valueOf(indicadorIngresoNeto.analizarResultadoTest(
				listaDeCuentas, repoIndicadores)));
	}
	
}
