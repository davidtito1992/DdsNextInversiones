package app;

import java.util.List;

import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;

import org.uqbar.commons.utils.ApplicationContext;

import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;
import repositories.RepositorioMetodologias;
import dataManagment.dataLoader.DataLoader;
import dataManagment.dataLoader.DataLoaderFactory;
import dataManagment.dataUploader.DataUploader;
import dataManagment.dataUploader.DataUploaderFactory;

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
			DataUploader cargador = DataUploaderFactory
					.actualizarData(DataLoaderFactory.ARCHIVO);

			cargador.escribirNuevoIndicador(unIndicador);

			this.getRepoIndicadores().create(unIndicador);

		} catch (Exception e) {
			throw new RuntimeException(
					"Debido a un problema en la lectura y/o escritura del archivo no pudimos realizar la operacion :/");
		}

	}

	public void borrarIndicador(RegistroIndicador unIndicador) {

		try {
			DataUploader cargador = DataUploaderFactory
					.actualizarData(DataLoaderFactory.ARCHIVO);

			cargador.borrarIndicador(unIndicador);

			this.getRepoIndicadores().delete(unIndicador);

		} catch (Exception e) {
			throw new RuntimeException(
					"Debido a un problema en la lectura y/o escritura del archivo no pudimos realizar la operacion :/");
		}
	}

	public void guardarMetodologia(Metodologia metodologia) {

		try {
			// String nuevaMetodologiaString = new AdapterToJson()
			// .getStringMetodologia(metodologia);
			//
			// // sobreescribimos un archivo segun nombre de archivo,
			// textoviejo,textonuevo
			// new FileUploader("./metodologias.json", "}]",
			// nuevaMetodologiaString
			// + "]\r\n");
			//
			// this.getRepoMetodologias().create(metodologia);

			DataUploader cargador = DataUploaderFactory
					.actualizarData(DataLoaderFactory.ARCHIVO);

			cargador.escribirNuevaMetodologia(metodologia);

			this.getRepoMetodologias().create(metodologia);

		} catch (Exception e) {
			throw new RuntimeException(
					"Debido a un problema en la lectura y/o escritura del archivo no pudimos realizar la operacion :/");
		}

	}

}
