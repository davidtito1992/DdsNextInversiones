package semanticaIndicador;

import java.util.List;
import org.uqbar.commons.utils.ApplicationContext;
import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;
import model.Empresa;
import model.RegistroIndicador;

public class AnalizadorSemantico {

	// validamos que los nombres de las variables existan
	// ya sea una cuenta o indicador con la ayuda del
	// repo
	public void analizarVariablesDeFormula(List<String> variables) {

		variables.forEach(nombreVariable -> {
			if (this.getRepoEmpresas().esCuenta(nombreVariable))
				;
			else if (this.getRepoIndicadores().esIndicador(nombreVariable))
				;
			else
				throw new RuntimeException("El nombre de la variable: "
						+ nombreVariable + " no existe");
		});

	}

	public void analizarNombreDeIndicador(String nombreIndicador) {

		if (this.getRepoEmpresas().esCuenta(nombreIndicador))

			throw new RuntimeException("Existe una cuenta con el nombre: "
					+ nombreIndicador + ", escriba otro nombre de indicador.");

		else if (this.getRepoIndicadores().esIndicador(nombreIndicador))
			throw new RuntimeException("Existe un indicador con el nombre: "
					+ nombreIndicador + ", escriba otro nombre de indicador.");

	}

	public RepositorioIndicadores getRepoIndicadores() {
		return ApplicationContext.getInstance().getSingleton(
				RegistroIndicador.class);
	}

	public RepositorioEmpresa getRepoEmpresas() {
		return ApplicationContext.getInstance().getSingleton(Empresa.class);
	}
}
