package domaintest;

import java.io.File;

import org.junit.Test;

import dataManagment.FileLoader;
import dataManagment.JsonAdapter;

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
