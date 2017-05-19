package domaintest;

import java.io.FileNotFoundException;

import org.junit.Test;

import app.AppData;
import DataManagment.FileLoader;

public class CargarEmpresasTest {
	@Test(expected = FileNotFoundException.class)
	public void cargarArchivoInexistente() throws Exception {
		new FileLoader().setNombreArchivo("NombreQueNoExiste.json").getData();
    }
	
	/*@Test
	public void cargaExistosaAlRepo() throws Exception{
		new AppData().cargarEmpresas();
		
	}*/

}
