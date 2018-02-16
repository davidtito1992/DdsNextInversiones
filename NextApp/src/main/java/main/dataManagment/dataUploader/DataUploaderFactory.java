package main.dataManagment.dataUploader;

public class DataUploaderFactory {

	/********* ATRIBUTOS *********/

	public static final String ARCHIVO = "archivo";

	/********* METODOS **********/

	public static DataUploader actualizarData(String criteria) {

		if (criteria.equals("archivo")) {
			return new FileUploader();
		}

		throw new RuntimeException("tipo de DataUploader inexistente");

	}

}
