package domaintest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import DataManagment.DataAdapter;
import DataManagment.DataAdapterFactory;
import DataManagment.DataLoader;
import model.Empresa;
import model.Indicador;

public class StubFileLoader implements DataLoader {

	public String readFile(String pathname) throws Exception {
		File file;
		StringBuilder fileContents;
		Scanner scanner = null;
		try {
			file = new File(pathname);
			fileContents = new StringBuilder((int) file.length());
			scanner = new Scanner(file);
			scanner.close();
			return fileContents.toString();
		} catch (Exception e) {
			throw new FileNotFoundException(
					"Archivo no encontrado, pongalo en el directorio de la aplicación y vuelva a intentarlo.");
		} finally {
		}
	}

	public List<Empresa> getDataEmpresas() throws Exception {
		String AbsolutePath = new File(".").getAbsolutePath();
		String archivoEmpresas = readFile(AbsolutePath
				+ "/archivoInexistente.json");

		DataAdapter adaptador = DataAdapterFactory
				.adaptarData(DataAdapterFactory.JSON);
		return adaptador.adaptarEmpresas(archivoEmpresas);

	}

	public List<Indicador> getDataIndicadores() throws Exception {
		String AbsolutePath = new File(".").getAbsolutePath();
		String archivoIndicadores = readFile(AbsolutePath + "/archivoInexistente.json");

		DataAdapter adaptador = DataAdapterFactory
				.adaptarData(DataAdapterFactory.JSON);
		return adaptador.adaptarIndicadores(archivoIndicadores);
	}
}