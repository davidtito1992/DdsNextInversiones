package main.app;

import org.uqbar.commons.utils.Observable;
import main.repositories.RepositorioEmpresa;
import main.repositories.RepositorioUsuario;

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

	public RepositorioUsuario getInstanceRepoUsuarios() {
		return RepositorioUsuario.getSingletonInstance();

	}

}
