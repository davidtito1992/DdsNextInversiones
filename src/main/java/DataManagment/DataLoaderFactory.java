package DataManagment;

public class DataLoaderFactory {

	/********* ATRIBUTOS *********/

	public static final String ARCHIVO = "archivo";

	/********* METODOS **********/

	public static DataLoader cargarData(String criteria) {

		if (criteria.equals("archivo")) {
			return new FileLoader();
		}

		throw new RuntimeException("tipo de DataLoader inexistente");

	}

}
