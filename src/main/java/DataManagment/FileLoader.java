package DataManagment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import model.Empresa;

public class FileLoader implements DataLoader {
	private String nombreArchivo ;

//Al construir objetos con los elementos necesarios para su utilidad, desacoplamos...
	
	public FileLoader(String unNombreDeArchivo){
		
		this.nombreArchivo = unNombreDeArchivo ;
	}

	public String readFile(String pathname) throws Exception {
		File file;
		StringBuilder fileContents;
		Scanner scanner;
		try {
			file = new File(pathname);
			fileContents = new StringBuilder((int) file.length());
			scanner = new Scanner(file);
		} catch (Exception e) {
			throw new FileNotFoundException("Archivo no encontrado, pongalo en el directorio de la aplicación y vuelva a intentarlo.");
		}
		String lineSeparator = System.getProperty("line.separator");

		try {
			while (scanner.hasNextLine()) {
				fileContents.append(scanner.nextLine() + lineSeparator);
			}
			return fileContents.toString();
		} finally {
			scanner.close();
		}
	}
	
	public List<Empresa> getData() throws Exception{
		String AbsolutePath = new File(".").getAbsolutePath();
		String archivoEmpresas = readFile(AbsolutePath + "/" + nombreArchivo);
		
		DataAdapter adaptador =  DataAdapterFactory.adaptarData(DataAdapterFactory.JSON);
		return adaptador.adaptarEmpresas(archivoEmpresas);
	}	

}
