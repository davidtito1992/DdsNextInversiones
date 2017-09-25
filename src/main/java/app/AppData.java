package app;

import java.util.ArrayList;

import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;
import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicador;
import repositories.RepositorioMetodologia;
import dataManagment.dataLoader.DataLoader;
import dataManagment.dataLoader.DataLoaderFactory;
import dataManagment.dataLoader.MetodologiasLoader;
import dataManagment.dataUploader.DataUploader;
import dataManagment.dataUploader.DataUploaderFactory;

public class AppData {

	@SuppressWarnings("unchecked")
	public void cargarEmpresas() throws Exception {

		// LEO ARCHIVO YA ADAPTADO
		DataLoader cargador = DataLoaderFactory
				.cargarData(DataLoaderFactory.ARCHIVO);
		ArrayList<Empresa> empresas = cargador.getDataEmpresas();
		this.getRepositorioEmpresas().cargarListaDeElementos(empresas);
		// List<Empresa> lista = this.getRepositorioEmpresas().allInstances();
		// String a = "a";
	}

	@SuppressWarnings("unchecked")
	public void cargarMetodologias() throws Exception {

		this.getRepositorioMetodologias().cargarListaDeElementos(
				MetodologiasLoader.damePredefinidas());

	}

	@SuppressWarnings("unchecked")
	public void cargarIndicadores() throws Exception {

		// LEO ARCHIVO YA ADAPTADO
		DataLoader cargador = DataLoaderFactory
				.cargarData(DataLoaderFactory.ARCHIVO);
		ArrayList<RegistroIndicador> indicadores = cargador
				.getDataIndicadores();
		this.getRepositorioIndicadores().cargarListaDeElementos(indicadores);
	}

	public void borrarMetodologia(Metodologia metSelec) {
		this.getRepositorioMetodologias().eliminarMetodologia(
				metSelec.getMetodologiaId());
	}

	public RepositorioIndicador getRepositorioIndicadores() {
		return AplicacionContexto.getInstance().getInstanceRepoIndicadores();
	}

	public RepositorioEmpresa getRepositorioEmpresas() {
		return AplicacionContexto.getInstance().getInstanceRepoEmpresas();
	}

	public RepositorioMetodologia getRepositorioMetodologias() {
		return AplicacionContexto.getInstance().getInstanceRepoMetodologias();
	}

	public void guardarIndicador(RegistroIndicador unIndicador) {

		try {
			DataUploader cargador = DataUploaderFactory
					.actualizarData(DataLoaderFactory.ARCHIVO);

			cargador.escribirNuevoIndicador(unIndicador);
			this.getRepositorioIndicadores().agregarIndicador(unIndicador);

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
			this.getRepositorioIndicadores().eliminarIndicador(
					unIndicador.getRegistroIndicadorId());

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
			this.getRepositorioMetodologias().agregarMetodologia(metodologia);

		} catch (Exception e) {
			throw new RuntimeException(
					"Debido a un problema en la lectura y/o escritura del archivo no pudimos realizar la operacion :/");
		}

	}

}
