package domaintest;

import java.io.FileNotFoundException;

import main.dataManagment.dataLoader.FileLoader;

import org.junit.Test;

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
		new FileLoader().getDataEmpresas();
	}

}
