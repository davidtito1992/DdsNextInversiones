package DataManagment;

import java.util.List;
import model.Indicador;
import model.Empresa;

public interface DataLoader {

	public List<Empresa> getDataEmpresas() throws Exception;
	public List<Indicador> getDataIndicadores() throws Exception;
	
}
