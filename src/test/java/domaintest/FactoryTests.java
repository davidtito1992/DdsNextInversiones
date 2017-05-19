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
		assertEquals(DataLoaderFactory.cargarData(DataLoaderFactory.archivo).getClass(),new FileLoader().getClass());
	}
	
	@Test
	public void creoUnJsonAdapterTest(){
		assertEquals(DataAdapterFactory.adaptarData(DataAdapterFactory.json).getClass(),new JsonAdapter().getClass());
	}
}
