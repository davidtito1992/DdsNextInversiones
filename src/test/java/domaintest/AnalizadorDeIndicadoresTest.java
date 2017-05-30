package domaintest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.Cuenta;
import model.Indicador;

import org.junit.Test;

public class AnalizadorDeIndicadoresTest {

	@Test
	public void CalculoIndicadorSimpleTest(){
		Indicador indicador = new Indicador("IndicadorPrueba","EBITDA + FDS");
		List<Cuenta> listaDeCuentas = new ArrayList<Cuenta>();
		listaDeCuentas.add(new Cuenta("EBITDA",10));
		listaDeCuentas.add(new Cuenta("FDS",20));
		assertEquals("30.00",indicador.analizarResultado(listaDeCuentas).toString());
	}
}
