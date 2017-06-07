package formulaTeam;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.uqbar.commons.utils.ApplicationContext;

import calculator.Calculator;
import calculator.ParseException;
import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;
import model.Cuenta;
import model.Empresa;
import model.Indicador;

public class AnalizadorSemantico {

	private String nombre;
	private String formula;

	public AnalizadorSemantico(Indicador unIndicador) {

		this.nombre = unIndicador.getNombre();
		this.formula = unIndicador.getFormula();
	}

	public void analizar() throws Exception {

		if (getRepoIndicadores().indicadorYaExistente(nombre)) {
			throw new Exception(
					"Un indicador con ese nombre ya se encuentra cargado en el sistema, Intentelo nuevamente");
		}

		revisarSintaxisYSemantica(new Indicador(this.nombre, this.formula));

	}

	public void revisarSintaxisYSemantica(Indicador indicador) throws Exception {

		String[] componentes = indicador.getFormula().split(" ");
		List<Cuenta> todasLasCuentas = getRepoEmpresas().todasLasCuentas();

		for (int i = 0; i < componentes.length; i++) {
			if (getRepoIndicadores().esIndicador(componentes[i])
					|| getRepoEmpresas().esCuenta(componentes[i],
							todasLasCuentas)) {
				componentes[i] = "2";
			}
		}

		String formulaReemplazada = String.join(" ", componentes);
		Calculator calculator = new Calculator(new StringReader(
				formulaReemplazada));
		try {
			calculator.calculate();
		} catch (ParseException e) {
			throw new Exception("La sintaxis es incorrecta");
		} catch (Error e) {
			throw new Exception("Ingresó una cuenta o un indicador inexistente");
		}

	}

	public RepositorioIndicadores getRepoIndicadores() {
		return ApplicationContext.getInstance().getSingleton(Indicador.class);
	}

	public RepositorioEmpresa getRepoEmpresas() {
		return ApplicationContext.getInstance().getSingleton(Empresa.class);
	}
}
