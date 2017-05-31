package app;

import java.util.List;

import org.uqbar.commons.utils.ApplicationContext;

import model.Empresa;
import model.Indicador;
import DataManagment.AdapterToJson;
import DataManagment.DataLoader;
import DataManagment.DataLoaderFactory;
import DataManagment.FileWriter;
import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;

public class AppData {

	public void cargarEmpresas() throws Exception {

		// LEO ARCHIVO YA ADAPTADO
		DataLoader cargador = DataLoaderFactory
				.cargarData(DataLoaderFactory.ARCHIVO);
		List<Empresa> empresas = cargador.getDataEmpresas();

		// CARGO EN REPO
		this.getRepoEmpresas().cargarListaEmpresas(empresas);

	}

	public void cargarIndicadores() throws Exception {

		// LEO ARCHIVO YA ADAPTADO
		DataLoader cargador = DataLoaderFactory
				.cargarData(DataLoaderFactory.ARCHIVO);
		List<Indicador> indicadores = cargador.getDataIndicadores();

		// CARGO EN REPO
		this.getRepoIndicadores().cargarListaIndicadores(indicadores);

	}

	public RepositorioIndicadores getRepoIndicadores() {
		return ApplicationContext.getInstance().getSingleton(Indicador.class);
	}

	public RepositorioEmpresa getRepoEmpresas() {
		return ApplicationContext.getInstance().getSingleton(Empresa.class);
	}
   
	public void guardarIndicador(Indicador unIndicador) throws Exception{
//Convertimos un indicador a json
		  String nuevoIndicadorString = new AdapterToJson(unIndicador).getstringJson();
//sobreescribimos un archivo segun nombre de archivo, textoviejo,textonuevo		
		  new FileWriter("./indicadores.json","}]","},\n"+nuevoIndicadorString+"]\r\n" );
		    
	}
}
