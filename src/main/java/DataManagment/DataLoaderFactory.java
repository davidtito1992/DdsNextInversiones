package DataManagment;

public class DataLoaderFactory {

	/********* ATRIBUTOS *********/

	public static final String ARCHIVO = "archivo";

	/********* METODOS **********/

	public static DataLoader cargarData(String criteria) throws Exception {

		if (criteria.equals("archivo")) {
			return new FileLoader();
		}

		throw new Exception("tipo de DataLoader inexistente");

	}

}
