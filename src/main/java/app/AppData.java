package app;

import java.util.List;

import model.Empresa;

import DataManagment.DataAdapter;
import DataManagment.DataAdapterFactory;
import DataManagment.DataLoader;
import DataManagment.DataLoaderFactory;
import repositories.RepositorioEmpresa;

public class AppData {
	private RepositorioEmpresa repositorioEmpresa ;
	
	public AppData(RepositorioEmpresa repositorioEmpresa ){
		this.repositorioEmpresa = repositorioEmpresa ;
		
	}
	public void cargarEmpresas() throws Exception {
		
		// LEO ARCHIVO YA ADAPTADO
		DataLoader cargador = DataLoaderFactory.cargarData(DataLoaderFactory.archivo); 
		List<Empresa> empresas = cargador.getData();	
		
		// CARGO EN REPO
		getRepoEmpresas().cargarListaEmpresas(empresas);
			
	}

	public RepositorioEmpresa getRepoEmpresas() {
		return this.repositorioEmpresa ;
	}
	
}
