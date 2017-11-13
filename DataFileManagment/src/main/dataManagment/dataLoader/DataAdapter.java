package main.dataManagment.dataLoader;

import java.util.ArrayList;

import model.Empresa;

public interface DataAdapter {

	public ArrayList<Empresa> adaptarEmpresas(String empresas) throws Exception;

}
