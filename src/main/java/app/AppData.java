package app;

import java.util.List;

import org.uqbar.commons.utils.ApplicationContext;

import model.Empresa;
import DataManagment.DataLoader;
import DataManagment.DataLoaderFactory;
import repositories.RepositorioEmpresa;

public class AppData {

	public void cargarEmpresas() throws Exception {

		// LEO ARCHIVO YA ADAPTADO
		DataLoader cargador = DataLoaderFactory
				.cargarData(DataLoaderFactory.ARCHIVO);
		List<Empresa> empresas = cargador.getData();

		// CARGO EN REPO
		this.getRepoEmpresas().cargarListaEmpresas(empresas);

	}

	public RepositorioEmpresa getRepoEmpresas() {

		return ApplicationContext.getInstance().getSingleton(Empresa.class);
	}

}
