package DataManagment;

import java.util.List;

import model.Empresa;

public interface DataLoader {

	public List<Empresa> getData() throws Exception;
	
}
