package semanticaIndicador;

import java.util.List;

import repositories.RepositorioUnicoDeEmpresas;
import repositories.RepositorioUnicoDeIndicadores;
import app.AplicacionContexto;
import formulaIndicador.Variable;

public class AnalizadorSemantico {
	

	// validamos que los nombres de las variables existan
	// ya sea una cuenta o indicador con la ayuda del
	// repo
	public void analizarVariablesDeFormula(List<Variable> variables) {

		variables.forEach(nombreVariable -> {
			if (this.getRepositorioEmpresas().esCuenta(nombreVariable.getNombre()))
				;
			else if (this.getRepositorioIndicadores().esIndicador(
					nombreVariable.getNombre()))
				;
			else
				throw new RuntimeException("El nombre de la variable: "
						+ nombreVariable.getNombre() + " no existe");
		});

	}

	public void analizarNombreDeIndicador(String nombreIndicador) {

		if (this.getRepositorioEmpresas().esCuenta(nombreIndicador))

			throw new RuntimeException("Existe una cuenta con el nombre: "
					+ nombreIndicador + ", escriba otro nombre de indicador.");

		else if (this.getRepositorioIndicadores().esIndicador(nombreIndicador))
			throw new RuntimeException("Existe un indicador con el nombre: "
					+ nombreIndicador + ", escriba otro nombre de indicador.");

	}

	public RepositorioUnicoDeIndicadores getRepositorioIndicadores(){
		return AplicacionContexto.getInstance().getInstanceRepoIndicadores();
	}
	
	public RepositorioUnicoDeEmpresas getRepositorioEmpresas(){
		return AplicacionContexto.getInstance().getInstanceRepoEmpresas();
	}
}
