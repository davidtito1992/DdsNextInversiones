package domaintest;

import java.io.File;

import main.dataManagment.dataLoader.FileLoader;
import main.dataManagment.dataLoader.JsonAdapter;

import org.junit.Test;

public class LeerArchivoErroneoTest {

	@Test(expected = Exception.class)
	public void leerArchivoTest() throws Exception {
		FileLoader fl = new FileLoader();
		String AbsolutePath = new File(".").getAbsolutePath();
		String archivoEmpresas = fl.readFile(AbsolutePath
				+ "/EmpresasMalHecho.json");
		new JsonAdapter().adaptarEmpresas(archivoEmpresas);
	}

}
