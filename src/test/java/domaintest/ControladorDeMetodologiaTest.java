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
import condiciones.CondicionCuantitativaMayorOMenorA;
import condiciones.CondicionSumatoria.Criterio;
import condiciones.CondicionTaxativaMayorOMenorA;

public class ControladorDeMetodologiaTest {
	
	public ArrayList<Condicion> condicionesPrueba = new ArrayList<Condicion>();
	public RegistroIndicador ingresoNeto;
	public Empresa facebook;
	public RankingEmpresa rEmpresa;
	
	@Before 
	public void inicializar () throws Exception{
		new AppData().cargarEmpresas();
		new AppData().cargarIndicadores();
		
		ingresoNeto = new AppData().getRepositorioIndicadores().getRegistroIndicador("IngresoNeto");
		facebook = new AppData().getRepositorioEmpresas().getEmpresa("Facebook");
		rEmpresa = new RankingEmpresa(BigDecimal.ZERO, facebook);
	}
	
	@Test
	public void metodologiaConCondicionCuantitativaMayorATest() throws Exception {
		condicionesPrueba.add(new CondicionCuantitativaMayorOMenorA(Criterio.mayorA,ingresoNeto,2,1));
		Metodologia metodologia = new Metodologia("Metodlogia Prueba",condicionesPrueba);
		
		Assert.assertEquals(0,BigDecimal.valueOf(16).compareTo(metodologia.calcularEmpresa(rEmpresa).getRanking()));		
	}
	
	@Test
	public void metodologiaConCondicionTaxativaMayorATestERR() throws Exception {
		condicionesPrueba.add(new CondicionTaxativaMayorOMenorA(Criterio.mayorA,ingresoNeto,2,BigDecimal.valueOf(17)));
		Metodologia metodologia = new Metodologia("Metodlogia Prueba",condicionesPrueba);
		
		Assert.assertTrue(metodologia.calcularEmpresa(rEmpresa).getErrorTaxativa());	
	}
	
	@Test
	public void metodologiaConCondicionTaxativaMayorATestOK() throws Exception {
		condicionesPrueba.add(new CondicionTaxativaMayorOMenorA(Criterio.mayorA,ingresoNeto,2,BigDecimal.valueOf(16)));
		Metodologia metodologia = new Metodologia("Metodlogia Prueba",condicionesPrueba);
		
		Assert.assertFalse(metodologia.calcularEmpresa(rEmpresa).getErrorTaxativa());	
	}
	
	@Test
	public void metodologiaConCondicionTaxativaMenorATestERR() throws Exception {
		condicionesPrueba.add(new CondicionTaxativaMayorOMenorA(Criterio.menorA,ingresoNeto,2,BigDecimal.valueOf(15)));
		Metodologia metodologia = new Metodologia("Metodlogia Prueba",condicionesPrueba);
		
		Assert.assertTrue(metodologia.calcularEmpresa(rEmpresa).getErrorTaxativa());	
	}
	
	@Test
	public void metodologiaConCondicionTaxativaMenorATestOK() throws Exception {
		condicionesPrueba.add(new CondicionTaxativaMayorOMenorA(Criterio.menorA,ingresoNeto,2,BigDecimal.valueOf(16)));
		Metodologia metodologia = new Metodologia("Metodlogia Prueba",condicionesPrueba);
		
		Assert.assertFalse(metodologia.calcularEmpresa(rEmpresa).getErrorTaxativa());	
	}
	
	@Test
	public void metodologiaConCondicionAntiguedad() throws Exception {
		condicionesPrueba.add(new CondicionAntiguedad(3.00));
		Metodologia metodologia = new Metodologia("Metodlogia Prueba",condicionesPrueba);

		Assert.assertEquals(0,BigDecimal.valueOf(3).compareTo(metodologia.calcularEmpresa(rEmpresa).getRanking()));	
	}
	

}
