package domaintest;

import java.io.FileNotFoundException;

import org.junit.Test;

import dataManagment.dataLoader.FileLoader;

public class CargaDeArchivoTest {

	@Test(expected = FileNotFoundException.class)
	public void cargarArchivoInexistente() throws Exception {
		new StubFileLoader().getDataEmpresas();
	}

	@Test
	public void cargaExistosaDeArchivoEmpresa() throws Exception {
		new FileLoader().getDataEmpresas();
	}

	@Test
	public void cargaExistosaDeArchivoIndicadores() throws Exception {
		new FileLoader().getDataIndicadores();
	}

}
