package dataManagment.dataLoader;

import java.util.ArrayList;

import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;

public interface DataAdapter {

	public ArrayList<Empresa> adaptarEmpresas(String empresas) throws Exception;

	public ArrayList<RegistroIndicador> adaptarIndicadores(String indicadores)
			throws Exception;
	
	public ArrayList<Metodologia> adaptarMetodologias(String metodologias)
			throws Exception;

}
