package dataManagment;

import java.util.List;
import model.Empresa;
import model.RegistroIndicador;

public interface DataAdapter {

	public List<Empresa> adaptarEmpresas(String empresas) throws Exception;

	public List<RegistroIndicador> adaptarIndicadores(String indicadores)
			throws Exception;

}
