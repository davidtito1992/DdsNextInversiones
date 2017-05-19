package DataManagment;

public class DataAdapterFactory {
	
	/********* ATRIBUTOS *********/
	
	public static final String json = "json";

	/********* METODOS *********/

	public static DataAdapter adaptarData(String criteria) {

		if (criteria.equals("json")) {
			return new JsonAdapter();
		}

		return null;

	}

}
