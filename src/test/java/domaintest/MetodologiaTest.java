package domaintest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.commons.utils.ApplicationContext;

import parserIndicador.ParseException;
import repositories.RepositorioIndicadores;
import condiciones.CondicionCualitativa;
import condiciones.CondicionCualitativaAntiguedad;
import condiciones.CondicionCualitativaSumatoria;
import condiciones.CondicionTaxativa;
import condiciones.CondicionTaxativaMayorA;
import condiciones.CondicionTaxativaMenorA;
import dataManagment.FileLoader;

public class MetodologiaTest {
	List<Empresa> empresas;
	Empresa empresa;
	RepositorioIndicadores mockRepoIndicadores;
	CondicionTaxativaMayorA condicionTaxativaMayorA4;
	RegistroIndicador indicador;
	CondicionCualitativaSumatoria condicionCualitativaSumatoria;
	CondicionTaxativaMenorA condicionTaxativaMenorA1000;
	CondicionCualitativaAntiguedad condicionCualitativaAntiguedad1;
	List<CondicionTaxativa> condicionesTaxativas = new ArrayList<CondicionTaxativa>();
	List<CondicionCualitativa> condicionesCualitativas = new ArrayList<CondicionCualitativa>();
	Metodologia metodologia;
	
	
	@Before
	public void init() throws Exception{
		mockRepoIndicadores = mock(RepositorioIndicadores.class);
		ApplicationContext.getInstance().configureSingleton(RegistroIndicador.class, mockRepoIndicadores);
		empresas = new FileLoader().getDataEmpresas();
		empresa = empresas.get(0);
		indicador = new  RegistroIndicador("Prueba", "2+3");
		condicionTaxativaMayorA4 = new CondicionTaxativaMayorA(indicador, BigDecimal.valueOf(12), 6);
		condicionTaxativaMenorA1000 = new CondicionTaxativaMenorA(indicador, BigDecimal.valueOf(1000), 6);
		condicionCualitativaSumatoria = new CondicionCualitativaSumatoria(indicador,6,2);
		condicionCualitativaAntiguedad1 = new CondicionCualitativaAntiguedad(1);
		condicionesTaxativas.add(condicionTaxativaMayorA4);
		condicionesTaxativas.add(condicionTaxativaMenorA1000);
		condicionesCualitativas.add(condicionCualitativaSumatoria);
		condicionesCualitativas.add(condicionCualitativaAntiguedad1);
		metodologia = new Metodologia("prueba", condicionesTaxativas, condicionesCualitativas);
	}
	
	@Test
	public void condicionTaxativaMayorA4Test() throws ParseException{
		assertFalse(condicionTaxativaMayorA4.calcular(empresa));
	}
	
	@Test 
	public void condicionCualitativaSumatoria() throws ParseException{
		assertEquals("20.0",String.valueOf(condicionCualitativaSumatoria.calcular(empresa)));
	}
	
	@Test
	public void metodologiaTest() throws ParseException{
		assertEquals(0,metodologia.analizarResultado(empresa));
	}
	
	
	
	
	

}
