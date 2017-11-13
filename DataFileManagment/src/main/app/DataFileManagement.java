package main.app;

import java.util.ArrayList;

import main.dataManagment.dataLoader.DataLoader;
import main.dataManagment.dataLoader.DataLoaderFactory;
import main.repositories.RepositorioEmpresa;
import model.Empresa;

public class DataFileManagement {
	public static void main(String[] args) throws Exception {
		System.out.println("Iniciando programa...");
		new DataFileManagement().cargarEmpresas();
	}
	
	public void cargarEmpresas() throws Exception {
		// LEO ARCHIVO YA ADAPTADO
		DataLoader cargador = DataLoaderFactory
				.cargarData(DataLoaderFactory.ARCHIVO);
		ArrayList<Empresa> empresas = cargador.getDataEmpresas();
		this.getRepositorioEmpresas().cargarListaDeElementos(empresas);
	}
	
	public RepositorioEmpresa getRepositorioEmpresas() {
		return AplicacionContexto.getInstance().getInstanceRepoEmpresas();
	}

}
