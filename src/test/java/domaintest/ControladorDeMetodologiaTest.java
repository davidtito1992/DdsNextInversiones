package domaintest;

import java.math.BigDecimal;
import java.util.ArrayList;

import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;

import org.junit.Assert;
import org.junit.Test;

import app.AppData;
import RankingEmpresa.RankingEmpresa;
import condiciones.Condicion;
import condiciones.CondicionCuantitativaMayorOMenorA;
import condiciones.CondicionSumatoria.Criterio;
import condiciones.CondicionTaxativaMayorOMenorA;

public class ControladorDeMetodologiaTest {

	@Test
	public void getIndicadoresCondionadosOrdenadosTest() throws Exception {
		new AppData().cargarEmpresas();
		new AppData().cargarIndicadores();
		
		ArrayList<Condicion> condicionesPrueba = new ArrayList<Condicion>();
		
		RegistroIndicador ingresoNeto = new AppData().getRepositorioIndicadores().getRegistroIndicador("IngresoNeto");
		Empresa facebook = new AppData().getRepositorioEmpresas().getEmpresa("Facebook");
		RankingEmpresa rEmpresa = new RankingEmpresa(BigDecimal.ZERO, facebook);
		
		condicionesPrueba.add(new CondicionTaxativaMayorOMenorA(Criterio.mayorA, ingresoNeto, 2, BigDecimal.valueOf(5)));
		condicionesPrueba.add(new CondicionCuantitativaMayorOMenorA(condiciones.CondicionCuantitativaMayorOMenorA.Criterio.mayorA,ingresoNeto,2,1));
		
		Metodologia metodologia = new Metodologia("Metodlogia Prueba",condicionesPrueba);

		Assert.assertEquals(0,BigDecimal.valueOf(16).compareTo(metodologia.calcularEmpresa(rEmpresa).getRanking()));
				

	}

}
