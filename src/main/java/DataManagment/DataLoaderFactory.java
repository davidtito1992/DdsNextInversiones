package DataManagment;
	
public class DataLoaderFactory {
	
	/********* ATRIBUTOS *********/
	
	public static final String ARCHIVO = "archivo";
    private static final String NOMBREDEARCHIVO = "empresas.json";
	/********* METODOS *********/
	
	public static DataLoader cargarData(String criteria){
		
		if (criteria.equals("archivo")){
			return new FileLoader();
		}
		
		return null;
		
	}
	
}
