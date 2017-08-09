package domaintest;

import java.math.BigDecimal;
import java.util.ArrayList;

import model.Metodologia;
import model.RegistroIndicador;

import org.junit.Test;

import repositories.RepositorioIndicadores;
import condiciones.Condicion;
import condiciones.CondicionCuantitativaMayorOMenorA;
import condiciones.CondicionSumatoria.Criterio;
import condiciones.CondicionTaxativaMayorOMenorA;

public class ControladorDeMetodologiaTest {

	@Test
	public void getIndicadoresCondionadosOrdenadosTest() {

		ArrayList<Condicion> condicionesPrueba = new ArrayList<Condicion>();
		
		RegistroIndicador ingresoNeto = RepositorioIndicadores.repositorioMaestro().getRegistroIndicador("ImgresoNeto");
		
		condicionesPrueba.add(new CondicionTaxativaMayorOMenorA(Criterio.mayorA, ingresoNeto, 2, BigDecimal.valueOf(5)));
		condicionesPrueba.add(new CondicionCuantitativaMayorOMenorA(condiciones.CondicionCuantitativaMayorOMenorA.Criterio.mayorA,ingresoNeto,2,1));
		
		Metodologia metodologia = new Metodologia("Metodlogia Prueba",condicionesPrueba);
		
		

		/*Assert.assertEquals(indicadoresCondicionadosEsperado,
				indicadoresCondicionadosPrueba2);*/

	}

}
