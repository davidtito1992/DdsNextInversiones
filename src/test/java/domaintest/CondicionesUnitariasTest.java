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
	
	public ArrayList<Condicion> condicionesPrueba = new ArrayList<Condicion>();
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
		condicionesPrueba.add(new CondicionCuantitativaMayorOMenorA(Criterio.mayorA,ingresoNeto,2,1));
		Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);
		
		Assert.assertEquals(0,BigDecimal.valueOf(16).compareTo(metodologia.calcularEmpresa(rEmpresaFB).getRanking()));		
	}
	
	@Test
	public void metodologiaConCondicionTaxativaMayorATestERR() throws Exception {
		condicionesPrueba.add(new CondicionTaxativaMayorOMenorA(Criterio.mayorA,ingresoNeto,2,BigDecimal.valueOf(17)));
		Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);
		
		Assert.assertTrue(metodologia.calcularEmpresa(rEmpresaFB).getErrorTaxativa());	
	}
	
	@Test
	public void metodologiaConCondicionTaxativaMayorATestOK() throws Exception {
		condicionesPrueba.add(new CondicionTaxativaMayorOMenorA(Criterio.mayorA,ingresoNeto,2,BigDecimal.valueOf(16)));
		Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);
		
		Assert.assertFalse(metodologia.calcularEmpresa(rEmpresaFB).getErrorTaxativa());	
	}
	
	@Test
	public void metodologiaConCondicionTaxativaMenorATestERR() throws Exception {
		condicionesPrueba.add(new CondicionTaxativaMayorOMenorA(Criterio.menorA,ingresoNeto,2,BigDecimal.valueOf(15)));
		Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);
		
		Assert.assertTrue(metodologia.calcularEmpresa(rEmpresaFB).getErrorTaxativa());	
	}
	
	@Test
	public void metodologiaConCondicionTaxativaMenorATestOK() throws Exception {
		condicionesPrueba.add(new CondicionTaxativaMayorOMenorA(Criterio.menorA,ingresoNeto,2,BigDecimal.valueOf(16)));
		Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);
		
		Assert.assertFalse(metodologia.calcularEmpresa(rEmpresaFB).getErrorTaxativa());	
	}
	
	@Test
	public void metodologiaConCondicionAntiguedad() throws Exception {
		condicionesPrueba.add(new CondicionAntiguedad(3.00));
		Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);

		Assert.assertEquals(0,BigDecimal.valueOf(3).compareTo(metodologia.calcularEmpresa(rEmpresaFB).getRanking()));	
	}
	
	@Test
	public void metodologiaConCondicionCrecienteOK() throws Exception {
		condicionesPrueba.add(new CondicionCrecienteODecreciente(CondicionCrecienteODecreciente.Criterio.CRECIENTE,ingresoNeto,5));
		Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);

		Assert.assertFalse(metodologia.calcularEmpresa(rEmpresaFB).getErrorTaxativa());	
	}
	
	@Test
	public void metodologiaConCondicionCrecienteERR() throws Exception {
		condicionesPrueba.add(new CondicionCrecienteODecreciente(CondicionCrecienteODecreciente.Criterio.CRECIENTE,ingresoNeto,5));
		Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);

		Assert.assertTrue(metodologia.calcularEmpresa(rEmpresaTW).getErrorTaxativa());	
	}
	
	@Test
	public void metodologiaConCondicionDecrecienteOK() throws Exception {
		condicionesPrueba.add(new CondicionCrecienteODecreciente(CondicionCrecienteODecreciente.Criterio.DECRECIENTE,ingresoNeto,5));
		Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);

		Assert.assertTrue(metodologia.calcularEmpresa(rEmpresaFB).getErrorTaxativa());	
	}
	
	@Test
	public void metodologiaConCondicionDecrecienteERR() throws Exception {
		condicionesPrueba.add(new CondicionCrecienteODecreciente(CondicionCrecienteODecreciente.Criterio.DECRECIENTE,ingresoNeto,5));
		Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);

		Assert.assertFalse(metodologia.calcularEmpresa(rEmpresaTW).getErrorTaxativa());		
	}
	
	@Test
	public void metodologiaConCuantitativaMenorA() throws Exception {
		condicionesPrueba.add(new CondicionCuantitativaMayorOMenorA(Criterio.menorA,ingresoNeto,5,10));
		Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);
		
		Assert.assertEquals(0,BigDecimal.valueOf(-160).compareTo(metodologia.calcularEmpresa(rEmpresaFB).getRanking()));	
	}
	
}
