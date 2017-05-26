package DataManagment;

import java.util.List;
import model.Empresa;
import model.Indicador;

public interface DataAdapter {

	public List<Empresa> adaptarEmpresas(String empresas) throws Exception;

	public List<Indicador> adaptarIndicadores(String indicadores)
			throws Exception;

}
