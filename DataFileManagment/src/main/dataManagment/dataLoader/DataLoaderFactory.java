package main.dataManagment.dataLoader;

public class DataLoaderFactory {

	/********* ATRIBUTOS *********/

	public static final String ARCHIVO = "archivo";
	public static final String DATABASE = "base de datos";

	/********* METODOS **********/

	public static DataLoader cargarData(String criteria) {

		if (criteria.equals(ARCHIVO)) {
			return new FileLoader();
		} else {
			throw new RuntimeException("tipo de DataLoader inexistente");
		}
	}
}
