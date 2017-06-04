package app;

import java.util.ArrayList;
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

	public void guardarIndicador(Indicador unIndicador) throws Exception {

		//parseamos la formula,luego comprobamos la existencia de cuentas, indicadores,etc
		//si esta todo ok ingresa para guardarse en archivo y repo
		new Parser(unIndicador).parsear() ; 
		new AnalizadorSemantico(unIndicador).analizar() ;	

			try {			
				//Convertimos un indicador a json
				String nuevoIndicadorString = new AdapterToJson(unIndicador).getstringJson();

				//sobreescribimos un archivo segun nombre de archivo, textoviejo,textonuevo		
				new FileWriter("./indicadores.json","}]",nuevoIndicadorString+"]\r\n" );

				this.getRepoIndicadores().create(unIndicador);	

			} catch (Exception e) {
				throw new Exception(
						"Debido a un problema en la lectura y/o escritura del archivo no pudimos realizar la operacion :/");
			}
		

	}

	public void borrarIndicador(Indicador unIndicador) throws Exception{

		try{
			//Convertimos un indicador a json
			String nuevoIndicadorString = new AdapterToJson( new Indicador(unIndicador.getNombre(),unIndicador.getFormula())).getstringJson();
			//sobreescribimos para borrar		 
			new FileWriter("./indicadores.json",nuevoIndicadorString,"");

			//eliminamos del repo
			this.getRepoIndicadores().delete(unIndicador);

		} catch (Exception e) {
			throw new Exception(
					"Debido a un problema en la lectura y/o escritura del archivo no pudimos realizar la operacion :/");
		}			    
	}

}

