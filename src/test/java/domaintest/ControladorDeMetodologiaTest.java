package domaintest;

import indicadoresCondicionados.IndicadorCondicionado;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class ControladorDeMetodologiaTest {

	@Test
	public void getIndicadoresCondionadosOrdenadosTest() {

		ArrayList<IndicadorCondicionado> indicadoresCondicionadosPrueba = new ArrayList<IndicadorCondicionado>();
		indicadoresCondicionadosPrueba.add(new IndicadorCondicionado(3, "ROE",
				null));
		indicadoresCondicionadosPrueba.add(new IndicadorCondicionado(2,
				"DEUDA", null));
		indicadoresCondicionadosPrueba.add(new IndicadorCondicionado(4,
				"MARGEN", null));
		indicadoresCondicionadosPrueba.add(new IndicadorCondicionado(2,
				"LONGEVIDAD", null));

		indicadoresCondicionadosPrueba.sort(Comparator
				.comparing(IndicadorCondicionado::getPrioridad));

		ArrayList<String> indicadoresCondicionadosPrueba2 = indicadoresCondicionadosPrueba
				.stream().map(i -> i.getNombreIndicador())
				.collect(Collectors.toCollection(ArrayList::new));
		ArrayList<String> indicadoresCondicionadosEsperado = new ArrayList<String>();
		indicadoresCondicionadosEsperado.add("DEUDA");
		indicadoresCondicionadosEsperado.add("LONGEVIDAD");
		indicadoresCondicionadosEsperado.add("ROE");
		indicadoresCondicionadosEsperado.add("MARGEN");

		Assert.assertEquals(indicadoresCondicionadosEsperado,
				indicadoresCondicionadosPrueba2);

	}

}
