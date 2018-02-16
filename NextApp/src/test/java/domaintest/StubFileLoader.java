package domaintest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import main.dataManagment.dataLoader.DataAdapter;
import main.dataManagment.dataLoader.DataAdapterFactory;
import main.dataManagment.dataLoader.DataLoader;
import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;

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

	public ArrayList<Empresa> getDataEmpresas() throws Exception {
		String AbsolutePath = new File(".").getAbsolutePath();
		String archivoEmpresas = readFile(AbsolutePath
				+ "/archivoInexistente.json");

		DataAdapter adaptador = DataAdapterFactory
				.adaptarData(DataAdapterFactory.JSON);
		return adaptador.adaptarEmpresas(archivoEmpresas);

	}

	public ArrayList<RegistroIndicador> getDataIndicadores() throws Exception {
		String AbsolutePath = new File(".").getAbsolutePath();
		String archivoIndicadores = readFile(AbsolutePath
				+ "/archivoInexistente.json");

		DataAdapter adaptador = DataAdapterFactory
				.adaptarData(DataAdapterFactory.JSON);
		return adaptador.adaptarIndicadores(archivoIndicadores);
	}

	@Override
	public ArrayList<Metodologia> getDataMetodologias() throws Exception {
		String AbsolutePath = new File(".").getAbsolutePath();
		String archivoMetodologias = readFile(AbsolutePath + "/archivoInexistente.json");

		DataAdapter adaptador = DataAdapterFactory
				.adaptarData(DataAdapterFactory.JSON);
		return adaptador.adaptarMetodologias(archivoMetodologias);
	}
}