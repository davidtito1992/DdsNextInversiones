package app;

import java.util.List;
import model.Empresa;
import DataManagment.DataLoader;
import DataManagment.DataLoaderFactory;
import repositories.RepositorioEmpresa;

public class AppData {

	public void cargarEmpresasEn(RepositorioEmpresa repositorioEmpresa)
			throws Exception {

		// LEO ARCHIVO YA ADAPTADO
		DataLoader cargador = DataLoaderFactory
				.cargarData(DataLoaderFactory.ARCHIVO);
		List<Empresa> empresas = cargador.getData();

		// CARGO EN REPO
		repositorioEmpresa.cargarListaEmpresas(empresas);

	}

}
