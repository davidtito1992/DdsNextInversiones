package domaintest;

import java.math.BigDecimal;
import java.util.ArrayList;

import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;

import org.junit.Assert;
import org.junit.Test;

import RankingEmpresa.RankingEmpresa;
import app.AppData;
import condiciones.Condicion;
import condiciones.CondicionCuantitativaMayorOMenorA;
import condiciones.CondicionSumatoria.Criterio;
import condiciones.CondicionTaxativaMayorOMenorA;

public class ControladorDeMetodologiaTest {

	@Test
	public void metodologiaConCondicionCuantitativaMayorATest() throws Exception {
		new AppData().cargarEmpresas();
		new AppData().cargarIndicadores();
		
		ArrayList<Condicion> condicionesPrueba = new ArrayList<Condicion>();
		RegistroIndicador ingresoNeto = new AppData().getRepositorioIndicadores().getRegistroIndicador("IngresoNeto");
		Empresa facebook = new AppData().getRepositorioEmpresas().getEmpresa("Facebook");
		RankingEmpresa rEmpresa = new RankingEmpresa(BigDecimal.ZERO, facebook);
		
		condicionesPrueba.add(new CondicionCuantitativaMayorOMenorA(Criterio.mayorA,ingresoNeto,2,1));
		Metodologia metodologia = new Metodologia("Metodlogia Prueba",condicionesPrueba);
		
		Assert.assertEquals(0,BigDecimal.valueOf(16).compareTo(metodologia.calcularEmpresa(rEmpresa).getRanking()));		
	}
	
	@Test
	public void metodologiaConCondicionTaxativaMayorATest() throws Exception {
		new AppData().cargarEmpresas();
		new AppData().cargarIndicadores();
		
		ArrayList<Condicion> condicionesPrueba = new ArrayList<Condicion>();
		RegistroIndicador ingresoNeto = new AppData().getRepositorioIndicadores().getRegistroIndicador("IngresoNeto");
		Empresa facebook = new AppData().getRepositorioEmpresas().getEmpresa("Facebook");
		RankingEmpresa rEmpresa = new RankingEmpresa(BigDecimal.ZERO, facebook);
		
		condicionesPrueba.add(new CondicionTaxativaMayorOMenorA(Criterio.mayorA,ingresoNeto,2,BigDecimal.valueOf(17)));
		Metodologia metodologia = new Metodologia("Metodlogia Prueba",condicionesPrueba);
		
		Assert.assertTrue(metodologia.calcularEmpresa(rEmpresa).getErrorTaxativa());	
	}

}
