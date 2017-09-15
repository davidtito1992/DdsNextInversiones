package app;

import org.uqbar.commons.utils.Observable;

import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicador;
import repositories.RepositorioMetodologia;

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

	public RepositorioIndicador getInstanceRepoIndicadores() {
		return RepositorioIndicador.getSingletonInstance();
	}

	public RepositorioMetodologia getInstanceRepoMetodologias() {
		return RepositorioMetodologia.getSingletonInstance();
	}

}
