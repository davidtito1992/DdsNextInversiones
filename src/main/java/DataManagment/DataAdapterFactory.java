package DataManagment;

public class DataAdapterFactory {

	/********* ATRIBUTOS *********/

	public static final String JSON = "json";

	/********* METODOS **********/

	public static DataAdapter adaptarData(String criteria) {

		if (criteria.equals("json")) {
			return new JsonAdapter();
		}

		throw new RuntimeException("Tipo de DataAdapter inexistente");

	}

}
