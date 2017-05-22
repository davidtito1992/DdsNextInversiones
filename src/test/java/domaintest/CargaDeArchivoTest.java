package domaintest;

import java.io.FileNotFoundException;

import org.junit.Test;

import DataManagment.FileLoader;

public class CargaDeArchivoTest {
	
	@Test(expected = FileNotFoundException.class)
	public void cargarArchivoInexistente() throws Exception {
		new StubFileLoader().getData();
	}

	@Test
	public void cargaExistosaDeArchivo() throws Exception {
		new FileLoader().getData();
	}

}
