package main.dataManagment.dataLoader;

import java.util.ArrayList;

import model.EmpresaModificacion;

public interface DataAdapter {

	public ArrayList<EmpresaModificacion> adaptarEmpresas(String empresas) throws Exception;

}
