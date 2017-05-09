package DataManagment;

import java.util.List;

import model.Empresa;

public interface DataAdapter {
	
	public List<Empresa> adaptarEmpresas(String empresas) throws Exception;

}
