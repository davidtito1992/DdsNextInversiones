package app;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileLoader {

	public String readFile(String pathname) throws Exception {
	    File file;
	    StringBuilder fileContents;
	    Scanner scanner;
		try {
		    file = new File(pathname);
			fileContents = new StringBuilder((int)file.length());
		    scanner = new Scanner(file);
		} catch (Exception e) {
			throw new Exception("Archivo no encontrado, pongalo en el directorio de la aplicación y vuelva a intentarlo.");
		}
	    String lineSeparator = System.getProperty("line.separator");

	    try {
	        while(scanner.hasNextLine()) {
	            fileContents.append(scanner.nextLine() + lineSeparator);
	        }
	        return fileContents.toString();
	    } finally {
	        scanner.close();
	    }
	}
}
