package DataManagment;


public class DataLoaderFactory {

	public static DataLoader cargarData(String criteria){
		
		if (criteria.equals("archivo")){
			return new FileLoader();
		}
		
		return null;
		
	}
	
}
