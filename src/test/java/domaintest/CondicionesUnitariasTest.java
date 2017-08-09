package domaintest;

import java.math.BigDecimal;
import java.util.ArrayList;

import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import RankingEmpresa.RankingEmpresa;
import app.AppData;
import condiciones.Condicion;
import condiciones.CondicionAntiguedad;
import condiciones.CondicionCrecienteODecreciente;
import condiciones.CondicionCuantitativaMayorOMenorA;
import condiciones.CondicionSumatoria.Criterio;
import condiciones.CondicionTaxativaMayorOMenorA;

public class CondicionesUnitariasTest {
	
	public RegistroIndicador ingresoNeto;
	public Empresa facebook;
	public Empresa twitter;
	public RankingEmpresa rEmpresaFB;
	public RankingEmpresa rEmpresaTW;
	
	@Before 
	public void inicializar () throws Exception{
		new AppData().cargarEmpresas();
		new AppData().cargarIndicadores();
		
		ingresoNeto = new AppData().getRepositorioIndicadores().getRegistroIndicador("IngresoNeto");
		facebook = new AppData().getRepositorioEmpresas().getEmpresa("Facebook");
		twitter = new AppData().getRepositorioEmpresas().getEmpresa("Twitter");
		rEmpresaFB = new RankingEmpresa(BigDecimal.ZERO, facebook);
		rEmpresaTW = new RankingEmpresa(BigDecimal.ZERO, twitter);
	}
	
	@Test
	public void metodologiaConCondicionCuantitativaMayorATest() throws Exception {
		RankingEmpresa rEmpResul = new CondicionCuantitativaMayorOMenorA(Criterio.mayorA,ingresoNeto,2,1).calcular(rEmpresaFB);
		
		Assert.assertEquals(0,BigDecimal.valueOf(16).compareTo(rEmpResul.getRanking()));		
	}
	
	@Test(expected = RuntimeException.class)
	public void metodologiaConCondicionTaxativaMayorATestERR() throws Exception {
		new CondicionTaxativaMayorOMenorA(Criterio.mayorA,ingresoNeto,2,BigDecimal.valueOf(17)).calcular(rEmpresaFB);

	}
	
	@Test
	public void metodologiaConCondicionTaxativaMayorATestOK() throws Exception {
		RankingEmpresa rEmpResul = new CondicionTaxativaMayorOMenorA(Criterio.mayorA,ingresoNeto,2,BigDecimal.valueOf(16))
																		.calcular(rEmpresaFB);
		
		Assert.assertFalse(rEmpResul.getErrorTaxativa());	
	}
	
	@Test(expected = RuntimeException.class)
	public void metodologiaConCondicionTaxativaMenorATestERR() throws Exception {
		new CondicionTaxativaMayorOMenorA(Criterio.menorA,ingresoNeto,2,BigDecimal.valueOf(15)).calcular(rEmpresaFB);
	}
	
	@Test
	public void metodologiaConCondicionTaxativaMenorATestOK() throws Exception {
		RankingEmpresa rEmpResul = new CondicionTaxativaMayorOMenorA(Criterio.menorA,ingresoNeto,2,BigDecimal.valueOf(16)).calcular(rEmpresaFB);
		
		Assert.assertFalse(rEmpResul.getErrorTaxativa());	
	}
	
	@Test
	public void metodologiaConCondicionAntiguedad() throws Exception {
		RankingEmpresa rEmpResul = new CondicionAntiguedad(3.00).calcular(rEmpresaFB);

		Assert.assertEquals(0,BigDecimal.valueOf(3).compareTo(rEmpResul.getRanking()));	
	}
	
	@Test
	public void metodologiaConCondicionCrecienteOK() throws Exception {
		RankingEmpresa rEmpResul = new CondicionCrecienteODecreciente(CondicionCrecienteODecreciente.
				Criterio.CRECIENTE,ingresoNeto,5)
				.calcular(rEmpresaFB);

		Assert.assertFalse(rEmpResul.getErrorTaxativa());	
	}
	
	@Test(expected = RuntimeException.class)
	public void metodologiaConCondicionCrecienteERR() throws Exception {
		new CondicionCrecienteODecreciente(CondicionCrecienteODecreciente.
				Criterio.CRECIENTE,ingresoNeto,5)
				.calcular(rEmpresaTW);
	}
	
	@Test(expected = RuntimeException.class)
	public void metodologiaConCondicionDecrecienteERR() throws Exception {
		new CondicionCrecienteODecreciente(CondicionCrecienteODecreciente.
				Criterio.DECRECIENTE,ingresoNeto,5)
				.calcular(rEmpresaFB);
	}
	
	@Test
	public void metodologiaConCondicionDecrecienteOK() throws Exception {
		RankingEmpresa rEmpResul = new CondicionCrecienteODecreciente(CondicionCrecienteODecreciente.
																Criterio.DECRECIENTE,ingresoNeto,5)
																.calcular(rEmpresaTW);

		Assert.assertFalse(rEmpResul.getErrorTaxativa());		
	}
	
	@Test
	public void metodologiaConCuantitativaMenorA() throws Exception {
		RankingEmpresa resultado = new CondicionCuantitativaMayorOMenorA(Criterio.menorA,ingresoNeto,5,10).calcular(rEmpresaFB);
		
		Assert.assertEquals(0,BigDecimal.valueOf(-160).compareTo(resultado.getRanking()));
		//condicionesPrueba.add(new CondicionCuantitativaMayorOMenorA(Criterio.menorA,ingresoNeto,5,10));
		//Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);
		
		//Assert.assertEquals(0,BigDecimal.valueOf(-160).compareTo(metodologia.calcularEmpresa(rEmpresaFB).getRanking()));	
	}
	
}
