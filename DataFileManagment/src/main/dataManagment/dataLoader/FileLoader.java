package main.dataManagment.dataLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Empresa;

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

	public ArrayList<Empresa> getDataEmpresas(String rutaArchivo)
			throws Exception {
		DataAdapter adaptador = DataAdapterFactory
				.adaptarData(DataAdapterFactory.JSON);
		return adaptador.adaptarEmpresas(readFile(rutaArchivo));

	}


}
