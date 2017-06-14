package DataManagment;

import java.util.List;
import model.RegistroIndicador;
import model.Empresa;

public interface DataLoader {

	public List<Empresa> getDataEmpresas() throws Exception;

	public List<RegistroIndicador> getDataIndicadores() throws Exception;

}
