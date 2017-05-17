package app;

import java.util.List;

import model.Empresa;

import org.uqbar.commons.utils.ApplicationContext;

import DataManagment.DataAdapter;
import DataManagment.DataAdapterFactory;
import DataManagment.DataLoader;
import DataManagment.DataLoaderFactory;
import repositories.RepositorioEmpresa;

public class AppData {

	public AppData () throws Exception{
		//a modo de prueba del repo de arena
		cargarEmpresas();
	}
	
	public void cargarEmpresas() throws Exception {
		
		// LEO ARCHIVO YA ADAPTADO
		DataLoader cargador = DataLoaderFactory.cargarData(DataLoaderFactory.archivo); 
		List<Empresa> empresas = cargador.getData();	
		
		// CARGO EN REPO
		getRepoEmpresas().cargarListaEmpresas(empresas);
			
	}

	public RepositorioEmpresa getRepoEmpresas() {
		return (RepositorioEmpresa) ApplicationContext.getInstance().getSingleton(Empresa.class);
	}
	
}
