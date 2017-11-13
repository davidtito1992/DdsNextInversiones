package main.dataManagment.dataLoader;

import java.util.ArrayList;

import model.Empresa;

public interface DataLoader {

	public ArrayList<Empresa> getDataEmpresas(String nombreArchivo) throws Exception;

}
