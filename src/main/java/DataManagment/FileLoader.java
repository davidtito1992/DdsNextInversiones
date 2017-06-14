package DataManagment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import model.RegistroIndicador;
import model.Empresa;

public class FileLoader implements DataLoader {

	// Al construir objetos con los elementos necesarios para su utilidad,
	// desacoplamos...

	public String readFile(String pathname) throws Exception {
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

	public List<Empresa> getDataEmpresas() throws Exception {
		String AbsolutePath = new File(".").getAbsolutePath();
		String archivoEmpresas = readFile(AbsolutePath + "/empresas.json");

		DataAdapter adaptador = DataAdapterFactory
				.adaptarData(DataAdapterFactory.JSON);
		return adaptador.adaptarEmpresas(archivoEmpresas);
	}

	public List<RegistroIndicador> getDataIndicadores() throws Exception {
		String AbsolutePath = new File(".").getAbsolutePath();
		String archivoIndicadores = readFile(AbsolutePath + "/indicadores.json");

		DataAdapter adaptador = DataAdapterFactory
				.adaptarData(DataAdapterFactory.JSON);
		return adaptador.adaptarIndicadores(archivoIndicadores);
	}

}
