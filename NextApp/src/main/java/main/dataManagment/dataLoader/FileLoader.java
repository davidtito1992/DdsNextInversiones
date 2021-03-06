package main.dataManagment.dataLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;

public class FileLoader implements DataLoader {

	// Al construir objetos con los elementos necesarios para su utilidad,
	// desacoplamos...

	public String readFile(String pathname) throws FileNotFoundException {
		File file;
		StringBuilder fileContents;
		Scanner scanner;
		try {
			file = new File(pathname);
			fileContents = new StringBuilder((int) file.length());
			scanner = new Scanner(file);
		} catch (Exception e) {
			throw new FileNotFoundException(
					"Archivo no encontrado, pongalo en el directorio de la aplicacion y vuelva a intentarlo.");
		}
		String lineSeparator = System.getProperty("line.separator");

		try {
			while (scanner.hasNextLine()) {
				fileContents.append(scanner.nextLine() + lineSeparator);
			}
			scanner.close();// No puede estar en el finally, porque si no hay
							// archivo, rompe la sentencia close.
			return fileContents.toString();
		} finally {
		}
	}

	public ArrayList<Empresa> getDataEmpresas() throws Exception {
		String AbsolutePath = new File(".").getAbsolutePath();
		String archivoEmpresas = readFile(AbsolutePath + "/empresas.json");

		DataAdapter adaptador = DataAdapterFactory
				.adaptarData(DataAdapterFactory.JSON);
		return adaptador.adaptarEmpresas(archivoEmpresas);
	}

	public ArrayList<RegistroIndicador> getDataIndicadores() throws Exception {
		String AbsolutePath = new File(".").getAbsolutePath();
		String archivoIndicadores = readFile(AbsolutePath + "/indicadores.json");

		DataAdapter adaptador = DataAdapterFactory
				.adaptarData(DataAdapterFactory.JSON);
		return adaptador.adaptarIndicadores(archivoIndicadores);
	}

	public ArrayList<Metodologia> getDataMetodologias() throws Exception {
		String AbsolutePath = new File(".").getAbsolutePath();
		String archivoMetodologias = readFile(AbsolutePath
				+ "/metodologias.json");

		DataAdapter adaptador = DataAdapterFactory
				.adaptarData(DataAdapterFactory.JSON);
		return adaptador.adaptarMetodologias(archivoMetodologias);
	}

}
