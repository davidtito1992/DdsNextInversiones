package DataManagment;

public class DataAdapterFactory {

	/********* ATRIBUTOS *********/

	public static final String JSON = "json";

	/********* METODOS **********/

	public static DataAdapter adaptarData(String criteria) throws Exception {

		if (criteria.equals("json")) {
			return new JsonAdapter();
		}

		throw new Exception("Tipo de DataAdapter inexistente");

	}

}
