package app;

import org.uqbar.commons.utils.Observable;

import repositories.RepositorioEmpresa;
import repositoriesVIEJOS.RepositorioUnicoDeIndicadores;
import repositoriesVIEJOS.RepositorioUnicoDeMetodologias;

@Observable
public class AplicacionContexto {

	private static AplicacionContexto aplicacionContexto = null;

	public static AplicacionContexto getInstance() {
		if (aplicacionContexto == null) {
			aplicacionContexto = new AplicacionContexto();
		}
		return aplicacionContexto;
	}

	public RepositorioEmpresa getInstanceRepoEmpresas() {
		return RepositorioEmpresa.getInstance();
	}

	public RepositorioUnicoDeIndicadores getInstanceRepoIndicadores() {
		return RepositorioUnicoDeIndicadores.getSingletonInstance();
	}

	public RepositorioUnicoDeMetodologias getInstanceRepoMetodologias() {
		return RepositorioUnicoDeMetodologias.getSingletonInstance();
	}

}
