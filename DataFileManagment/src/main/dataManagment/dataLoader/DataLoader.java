package main.dataManagment.dataLoader;

import java.util.ArrayList;

import model.EmpresaModificacion;

public interface DataLoader {

	public ArrayList<EmpresaModificacion> getDataEmpresas(String nombreArchivo) throws Exception;

}
