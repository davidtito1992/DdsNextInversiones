package main.app;

import java.util.ArrayList;

import main.dataManagment.dataLoader.DataLoader;
import main.dataManagment.dataLoader.DataLoaderFactory;
import main.dataManagment.dataLoader.MetodologiasLoader;
import main.dataManagment.dataUploader.DataUploader;
import main.dataManagment.dataUploader.DataUploaderFactory;
import main.repositories.RepositorioEmpresa;
import main.repositories.RepositorioIndicador;
import main.repositories.RepositorioMetodologia;
import main.repositories.RepositorioUsuario;
import model.Empresa;
import model.Metodologia;
import model.RegistroIndicador;
import model.User;

public class AppData {

	public void cargarEmpresas() throws Exception {
		// LEO ARCHIVO YA ADAPTADO
		DataLoader cargador = DataLoaderFactory.cargarData(DataLoaderFactory.ARCHIVO);
		ArrayList<Empresa> empresas = cargador.getDataEmpresas();
		this.getRepositorioEmpresas().cargarListaDeElementos(empresas);
	}
	
	public void cargarUsuarios(){
		this.getRepositorioUsuarios().agregar(new User("admin@dominio","admin"));
	}

	public void cargarMetodologias() throws Exception {

		this.getRepositorioMetodologias().cargarListaDeElementos(MetodologiasLoader.damePredefinidas());

	}

	public void cargarIndicadores() throws Exception {

		// LEO ARCHIVO YA ADAPTADO
		DataLoader cargador = DataLoaderFactory.cargarData(DataLoaderFactory.ARCHIVO);
		ArrayList<RegistroIndicador> indicadores = cargador.getDataIndicadores();
		this.getRepositorioIndicadores().cargarListaDeElementos(indicadores);
	}

	public void borrarMetodologia(Metodologia metSelec) {
		this.getRepositorioMetodologias().eliminar(metSelec.getMetodologiaId());
	}

	public RepositorioIndicador getRepositorioIndicadores() {
		return AplicacionContexto.getInstance().getInstanceRepoIndicadores();
	}
	
	public RepositorioUsuario getRepositorioUsuarios() {
		return AplicacionContexto.getInstance().getInstanceRepoUsuarios();
	}

	public RepositorioEmpresa getRepositorioEmpresas() {
		return AplicacionContexto.getInstance().getInstanceRepoEmpresas();
	}

	public RepositorioMetodologia getRepositorioMetodologias() {
		return AplicacionContexto.getInstance().getInstanceRepoMetodologias();
	}

	public void guardarIndicador(RegistroIndicador unIndicador) {

		try {
			DataUploader cargador = DataUploaderFactory.actualizarData(DataLoaderFactory.ARCHIVO);

			cargador.escribirNuevoIndicador(unIndicador);
			this.getRepositorioIndicadores().agregar(unIndicador);

		} catch (Exception e) {
			throw new RuntimeException("Debido a un problema en la lectura y/o escritura del archivo "
					+ "no pudimos realizar la operacion :/");
		}
	}

	public void borrarIndicador(RegistroIndicador unIndicador) {

		try {
			DataUploader cargador = DataUploaderFactory.actualizarData(DataLoaderFactory.ARCHIVO);

			cargador.borrarIndicador(unIndicador);
			this.getRepositorioIndicadores().eliminar(unIndicador.getRegistroIndicadorId());

		} catch (Exception e) {
			throw new RuntimeException("Debido a un problema en la lectura y/o escritura del archivo "
					+ "no pudimos realizar la operacion :/");
		}
	}

	public void guardarMetodologia(Metodologia metodologia) {

		try {

			DataUploader cargador = DataUploaderFactory.actualizarData(DataLoaderFactory.ARCHIVO);

			cargador.escribirNuevaMetodologia(metodologia);
			this.getRepositorioMetodologias().agregar(metodologia);

		} catch (Exception e) {
			throw new RuntimeException("Debido a un problema en la lectura y/o escritura del archivo "
					+ "no pudimos realizar la operacion :/");
		}

	}

}
