package domaintest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import DataManagment.DataAdapterFactory;
import DataManagment.DataLoaderFactory;
import DataManagment.FileLoader;
import DataManagment.JsonAdapter;

public class FactoryTests {

	@Test
	public void creoUnFileLoaderTest() throws Exception {
		assertEquals(DataLoaderFactory.cargarData(DataLoaderFactory.ARCHIVO)
				.getClass(), new FileLoader().getClass());
	}

	@Test(expected = Exception.class)
	public void noCreoUnFileLoaderTest() throws Exception {
		DataLoaderFactory.cargarData("tipo inexistente");
	}

	@Test
	public void creoUnJsonAdapterTest() throws Exception {
		assertEquals(DataAdapterFactory.adaptarData(DataAdapterFactory.JSON)
				.getClass(), new JsonAdapter().getClass());
	}

	@Test(expected = Exception.class)
	public void noCreoUnJsonAdapterTest() throws Exception {
		DataAdapterFactory.adaptarData("tipo inexistente");
	}
}
