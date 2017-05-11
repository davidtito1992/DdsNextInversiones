package app;

import java.util.List;

import model.Empresa;

import org.uqbar.commons.utils.ApplicationContext;

import DataManagment.DataAdapter;
import DataManagment.DataAdapterFactory;
import DataManagment.DataLoader;
import DataManagment.DataLoaderFactory;
import repositories.RepositorioEmpresa;

public class InicializadorDeData {

	public InicializadorDeData () throws Exception{
		//a modo de prueba del repo de arena
		cargarEmpresas();
	}
	
	public  void cargarEmpresas() throws Exception {
		
		// LEO ARCHIVO
		DataLoader cargador = DataLoaderFactory.cargarData("archivo");
		String archivoEmpresas = cargador.getData();	
		
		// ADAPTO STRING DE DATOS
		DataAdapter adaptador = DataAdapterFactory.adaptarData("json");
		List<Empresa> empresas = adaptador.adaptarEmpresas(archivoEmpresas);
		
		// CARGO EN REPO
		getRepoEmpresas().cargarListaEmpresas(empresas);
			
	}

	public RepositorioEmpresa getRepoEmpresas() {
		return (RepositorioEmpresa) ApplicationContext.getInstance().getSingleton(Empresa.class);
	}
	
}
