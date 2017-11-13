package main.dataManagment.dataUploader;

public class AdapterToDataFactory {

	/********* ATRIBUTOS *********/

	public static final String JSON = "json";

	/********* METODOS **********/

	public static AdapterToData adaptarAData(String criteria) {

		if (criteria.equals("json")) {
			return new AdapterToJson();
		}

		throw new RuntimeException("Tipo de AdapterToData inexistente");

	}
}
