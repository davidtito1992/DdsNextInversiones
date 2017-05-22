package domaintest;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

import DataManagment.DataAdapterFactory;
import DataManagment.DataLoaderFactory;
import DataManagment.FileLoader;
import DataManagment.JsonAdapter;

public class FactoryTests {

	@Test 
	public void creoUnFileLoaderTest() throws Exception{
		assertEquals(DataLoaderFactory.cargarData(DataLoaderFactory.ARCHIVO).getClass(),new FileLoader().getClass());
	}
	
	@Test(expected = Exception.class)
	public void noCreoUnFileLoaderTest() throws Exception{
		assertEquals(DataLoaderFactory.cargarData("tipo inexistente").getClass(),null);
	}
	
	@Test
	public void creoUnJsonAdapterTest() throws Exception{
		assertEquals(DataAdapterFactory.adaptarData(DataAdapterFactory.JSON).getClass(),new JsonAdapter().getClass());
	}
	
	@Test(expected = Exception.class)
	public void noCreoUnJsonAdapterTest() throws Exception{
		assertEquals(DataAdapterFactory.adaptarData("tipo inexistente").getClass(),null);
	}
}
