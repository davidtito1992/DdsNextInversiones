package DataManagment;

public interface DataLoader {

	//public String loadData();
	public String readFile(String pathname) throws Exception;
	public String getData() throws Exception;
	
}
