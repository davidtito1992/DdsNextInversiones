package main.app;

import main.dataManagment.dataLoader.FileLoader;
import main.repositories.RepositorioEmpresa;

public class DataFileManagement {
	public static void main(String[] args) throws Exception {
		System.out.println("Iniciando programa...");
		
		RepositorioEmpresa repoEmpresa = RepositorioEmpresa.getInstance();
		repoEmpresa.cargarListaDeElementos(new FileLoader().getDataEmpresas());
	}
	

}
