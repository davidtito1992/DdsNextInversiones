package domaintest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import DataManagment.DataAdapterFactory;
import DataManagment.DataLoaderFactory;
import DataManagment.FileLoader;
import DataManagment.JsonAdapter;

public class FactoryTests {

	@Test
	public void creoUnFileLoaderTest(){
		assertEquals(DataLoaderFactory.cargarData(DataLoaderFactory.ARCHIVO).getClass(),new FileLoader("empresas.json").getClass());
	}
	
	@Test
	public void creoUnJsonAdapterTest(){
		assertEquals(DataAdapterFactory.adaptarData(DataAdapterFactory.JSON).getClass(),new JsonAdapter().getClass());
	}
}
