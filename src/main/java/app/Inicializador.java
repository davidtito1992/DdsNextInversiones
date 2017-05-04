package app;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.Empresa;

import org.uqbar.commons.utils.ApplicationContext;

import repositories.RepositorioEmpresa;

public class Inicializador {

	public Inicializador () throws Exception{
		//a modo de prueba del repo de arena
		cargarEmpresas();
	}
	
	public  void cargarEmpresas() throws Exception {

		FileLoader cargadorDeArchivos = new FileLoader();

		String AbsolutePath = new File(".").getAbsolutePath();

		String archivoEmpresas = cargadorDeArchivos.readFile(AbsolutePath
					+ "/empresas.json");
		
		JsonAdapter adaptador = new JsonAdapter();
		
		List<Empresa> empresas = adaptador.adaptarEmpresas(archivoEmpresas);
		
		for (int i = 0; i < empresas.size(); i++) {
			getRepoEmpresas().create(empresas.get(i));
		}
			
	}

	public RepositorioEmpresa getRepoEmpresas() {
		return (RepositorioEmpresa) ApplicationContext.getInstance().getSingleton(Empresa.class);
	}
	
}
