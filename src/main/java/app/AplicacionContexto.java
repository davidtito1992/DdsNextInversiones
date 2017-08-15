package app;

import org.uqbar.commons.utils.Observable;

import repositories.RepositorioUnicoDeEmpresas;
import repositories.RepositorioUnicoDeIndicadores;
import repositories.RepositorioUnicoDeMetodologias;

@Observable
public class AplicacionContexto {

	private static AplicacionContexto aplicacionContexto = null;

	public static AplicacionContexto getInstance() {
		if (aplicacionContexto == null) {
			aplicacionContexto = new AplicacionContexto();
		}
		return aplicacionContexto;
	}

	public RepositorioUnicoDeEmpresas getInstanceRepoEmpresas() {
		return RepositorioUnicoDeEmpresas.getSingletonInstance();
	}

	public RepositorioUnicoDeIndicadores getInstanceRepoIndicadores() {
		return RepositorioUnicoDeIndicadores.getSingletonInstance();
	}

	public RepositorioUnicoDeMetodologias getInstanceRepoMetodologias() {
		return RepositorioUnicoDeMetodologias.getSingletonInstance();
	}

}
