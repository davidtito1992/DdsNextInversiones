package DataManagment;


public class DataAdapterFactory {

	public static DataAdapter adaptarData(String criteria) {

		if (criteria.equals("json")) {
			return new JsonAdapter();
		}

		return null;

	}

}
