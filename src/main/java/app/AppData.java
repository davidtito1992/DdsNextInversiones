package app;

import java.util.List;

import org.uqbar.commons.utils.ApplicationContext;

import dataManagment.AdapterToJson;
import dataManagment.DataLoader;
import dataManagment.DataLoaderFactory;
import dataManagment.FileWriter;
import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;
import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;
import repositories.RepositorioMetodologias;

public class AppData {

	public void cargarEmpresas() throws Exception {

		// LEO ARCHIVO YA ADAPTADO
		DataLoader cargador = DataLoaderFactory
				.cargarData(DataLoaderFactory.ARCHIVO);
		List<Empresa> empresas = cargador.getDataEmpresas();

		// CARGO EN REPO
		this.getRepoEmpresas().cargarListaEmpresas(empresas);
	}

	public void cargarMetodologias() throws Exception {

		// LEO ARCHIVO YA ADAPTADO
		DataLoader cargador = DataLoaderFactory
				.cargarData(DataLoaderFactory.ARCHIVO);
		List<Metodologia> metodologias = cargador.getDataMetodologias();

		// CARGO EN REPO
		this.getRepoMetodologias().cargarListaMetodologias(metodologias);
	}
	
	public void cargarIndicadores() throws Exception {

		// LEO ARCHIVO YA ADAPTADO
		DataLoader cargador = DataLoaderFactory
				.cargarData(DataLoaderFactory.ARCHIVO);
		List<RegistroIndicador> indicadores = cargador.getDataIndicadores();

		// CARGO EN REPO
		this.getRepoIndicadores().cargarListaIndicadores(indicadores);

	}

	public RepositorioIndicadores getRepoIndicadores() {
		return ApplicationContext.getInstance().getSingleton(
				RegistroIndicador.class);
	}

	public RepositorioEmpresa getRepoEmpresas() {
		return ApplicationContext.getInstance().getSingleton(Empresa.class);
	}
	
	public RepositorioMetodologias getRepoMetodologias() {
		return ApplicationContext.getInstance().getSingleton(Metodologia.class);
	}

	public void guardarIndicador(RegistroIndicador unIndicador) {

		try {
			String nuevoIndicadorString = new AdapterToJson()
					.getStringRegistroIndicador(unIndicador);

			// sobreescribimos un archivo segun nombre de archivo,
			// textoviejo,textonuevo
			new FileWriter("./indicadores.json", "}]", nuevoIndicadorString
					+ "]\r\n");

			this.getRepoIndicadores().create(unIndicador);

		} catch (Exception e) {
			throw new RuntimeException(
					"Debido a un problema en la lectura y/o escritura del archivo no pudimos realizar la operacion :/");
		}

	}

	public void borrarIndicador(RegistroIndicador unIndicador) {

		try {
			// Convertimos un indicador a json
			String nuevoIndicadorString = new AdapterToJson()
					.getStringRegistroIndicador(new RegistroIndicador(
							unIndicador.getNombre(), unIndicador.getFormula(),
							unIndicador.getVariables()));
			// sobreescribimos para borrar
			new FileWriter("./indicadores.json", nuevoIndicadorString, "");

			// eliminamos del repo
			this.getRepoIndicadores().delete(unIndicador);

		} catch (Exception e) {
			throw new RuntimeException(
					"Debido a un problema en la lectura y/o escritura del archivo no pudimos realizar la operacion :/");
		}
	}

}
