package domaintest;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import model.Cuenta;
import model.Indicador;

import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;
import calculator.ParseException;

public class AnalizadorDeIndicadoresTest {
	ArrayList<Indicador> repoIndicadores;
	Indicador indicadorSimple;
	Indicador indicadorComplejo;

	@Before
	public void initialize() {
		repoIndicadores = new ArrayList<Indicador>();
		indicadorSimple = new Indicador("IndicadorSimple", "EBITDA + FDS");
		indicadorComplejo = new Indicador("IndicadorComplejo",
				"IndicadorSimple");
		repoIndicadores.add(indicadorSimple);
		repoIndicadores.add(indicadorComplejo);
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
		assertEquals("30.0", String.valueOf(indicadorComplejo.analizarResultadoTest(
				listaDeCuentas, repoIndicadores)));
	}

	@Test
	public void pruebaDouble() {

		Calculator calculator = new Calculator(new StringReader("1/2"));
		try {
			assertEquals("0.5", String.valueOf(calculator.calculate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
