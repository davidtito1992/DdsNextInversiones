package DataManagment;
	
public class DataLoaderFactory {
	
	/********* ATRIBUTOS *********/
	
	public static final String archivo = "archivo";

	/********* METODOS *********/
	
	public static DataLoader cargarData(String criteria){
		
		if (criteria.equals("archivo")){
			return new FileLoader();
		}
		
		return null;
		
	}
	
}
