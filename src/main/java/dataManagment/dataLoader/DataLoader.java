package dataManagment.dataLoader;

import java.util.List;

import model.Metodologia;
import model.RegistroIndicador;
import model.Empresa;

public interface DataLoader {

	public List<Empresa> getDataEmpresas() throws Exception;

	public List<RegistroIndicador> getDataIndicadores() throws Exception;
	
	public List<Metodologia> getDataMetodologias() throws Exception;

}
