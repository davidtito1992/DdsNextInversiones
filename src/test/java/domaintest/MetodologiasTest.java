package domaintest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

public class MetodologiasTest {
	public ArrayList<Condicion> condicionesPrueba = new ArrayList<Condicion>();
	public RegistroIndicador ingresoNeto;
	public RegistroIndicador i4;
	public Empresa facebook;
	public Empresa twitter;
	public Empresa google;
	public RankingEmpresa rEmpresaFB;
	public RankingEmpresa rEmpresaTW;
	public RankingEmpresa rEmpresaGO;
	public ArrayList<RankingEmpresa> rEmpresas = new ArrayList<RankingEmpresa>();
	
	@Before 
	public void inicializar () throws Exception{
		new AppData().cargarEmpresas();
		new AppData().cargarIndicadores();
		
		ingresoNeto = new AppData().getRepositorioIndicadores().getRegistroIndicador("IngresoNeto");
		i4 = new AppData().getRepositorioIndicadores().getRegistroIndicador("i4");
		facebook = new AppData().getRepositorioEmpresas().getEmpresa("Facebook");
		twitter = new AppData().getRepositorioEmpresas().getEmpresa("Twitter");
		google = new AppData().getRepositorioEmpresas().getEmpresa("Google");
		rEmpresaFB = new RankingEmpresa(BigDecimal.ZERO, facebook);
		rEmpresaTW = new RankingEmpresa(BigDecimal.ZERO, twitter);
		rEmpresaGO = new RankingEmpresa(BigDecimal.ZERO, google);
		rEmpresas.add(rEmpresaGO);
		rEmpresas.add(rEmpresaTW);
		rEmpresas.add(rEmpresaFB);
	}
	
	@Test 
	public void metodologiaConCondicionCuantitativaMenorA(){
		condicionesPrueba.add(new CondicionCuantitativaMayorOMenorA(Criterio.menorA,ingresoNeto,5,10));
		Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);
		
		Assert.assertEquals(0,BigDecimal.valueOf(-160).compareTo(metodologia.calcularEmpresa(rEmpresaFB).getRanking()));	
	}
	
	@Test
	public void metodologiaConCondicionCuantitativaMayorATest() throws Exception {
	 	condicionesPrueba.add(new CondicionCuantitativaMayorOMenorA(Criterio.mayorA,ingresoNeto,2,1));
	 	Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);
	 		
	 	Assert.assertEquals(0,BigDecimal.valueOf(16).compareTo(metodologia.calcularEmpresa(rEmpresaFB).getRanking()));		
	}
	
	@Test
	public void metodologiaConCondicionTaxativaMenorATestOK() throws Exception {
	 	condicionesPrueba.add(new CondicionTaxativaMayorOMenorA(Criterio.menorA,ingresoNeto,2,BigDecimal.valueOf(16)));
	 	Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);
	 	
	 	Assert.assertFalse(metodologia.calcularEmpresa(rEmpresaFB).getErrorTaxativa());	
	}
	
	@Test
	public void metodologiaConCondicionDecrecienteERR() throws Exception {
	 	condicionesPrueba.add(new CondicionCrecienteODecreciente(CondicionCrecienteODecreciente.Criterio.DECRECIENTE,ingresoNeto,5));
	 	Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);
	
	 	Assert.assertFalse(metodologia.calcularEmpresa(rEmpresaTW).getErrorTaxativa());		
	}
	
	@Test
	public void metodologiaConCondicionesCuantitativas() throws Exception {
	 	condicionesPrueba.add(new CondicionCuantitativaMayorOMenorA(Criterio.mayorA,ingresoNeto,2,1));	//16
	 	condicionesPrueba.add(new CondicionAntiguedad(3.00));											//3
	 	Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);
	
	 	Assert.assertEquals(0,BigDecimal.valueOf(19).compareTo(metodologia.calcularEmpresa(rEmpresaFB).getRanking()));		
	}
	
	@Test
	public void metodologiaConCondicionesTaxativasOK() throws Exception {
	 	condicionesPrueba.add(new CondicionTaxativaMayorOMenorA(Criterio.mayorA,ingresoNeto,2,BigDecimal.valueOf(16)));
	 	condicionesPrueba.add(new CondicionTaxativaMayorOMenorA(Criterio.menorA,ingresoNeto,2,BigDecimal.valueOf(16)));	
	 	condicionesPrueba.add(new CondicionCrecienteODecreciente(CondicionCrecienteODecreciente.
	 											Criterio.CRECIENTE,ingresoNeto,5));
	 	Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);
	
	 	Assert.assertFalse(metodologia.calcularEmpresa(rEmpresaFB).getErrorTaxativa());		
	}
	
	@Test
	public void metodologiaConCondicionesTaxativasERR() throws Exception {
	 	condicionesPrueba.add(new CondicionTaxativaMayorOMenorA(Criterio.mayorA,ingresoNeto,2,BigDecimal.valueOf(16)));
	 	condicionesPrueba.add(new CondicionTaxativaMayorOMenorA(Criterio.menorA,ingresoNeto,2,BigDecimal.valueOf(16)));	
	 	condicionesPrueba.add(new CondicionCrecienteODecreciente(CondicionCrecienteODecreciente.
	 											Criterio.DECRECIENTE,ingresoNeto,5));//no se cumple
	 	Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);
	
	 	Assert.assertTrue(metodologia.calcularEmpresa(rEmpresaFB).getErrorTaxativa());		
	}
	
	@Test
	public void metodologiaConCondicionesTaxativasERRNoIndicador() throws Exception {
	 	condicionesPrueba.add(new CondicionTaxativaMayorOMenorA(Criterio.mayorA,ingresoNeto,2,BigDecimal.valueOf(16)));
	 	condicionesPrueba.add(new CondicionTaxativaMayorOMenorA(Criterio.menorA,ingresoNeto,2,BigDecimal.valueOf(16)));	
	 	Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);
	
	 	Assert.assertTrue(metodologia.calcularEmpresa(rEmpresaGO).getErrorTaxativa());		
	}
	
	@Test
	public void calcularEmpresasYSacarLaMasAlta(){
		condicionesPrueba.add(new CondicionCuantitativaMayorOMenorA(Criterio.mayorA,i4,5,1));
		Metodologia metodologia = new Metodologia("Metodologia Prueba",condicionesPrueba);
		
		Assert.assertEquals(google.getNombre(),metodologia.calcularEmpresas(rEmpresas).get(0).getEmpresa().getNombre());
	}
	
	
}















