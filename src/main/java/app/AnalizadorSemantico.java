package app;

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

		if (this.getRepoIndicadores().filtrar(nombre).size() > 0) {
			throw new Exception(
					"Un indicador con ese nombre ya se encuentra cargado en el sistema, Intentelo nuevamente");
		}

		revisarSintaxisYSemantica(new Indicador(this.nombre, this.formula));

	}

	public void revisarSintaxisYSemantica(Indicador indicador) throws Exception {

		boolean cuentaOIndicador = false;
		String[] componentes = indicador.getFormula().split(" ");
		for (int i = 0; i < componentes.length; i++) {
			if (indicador.esIndicador(componentes[i])
					|| indicador.esCuenta(componentes[i], todasLasCuentas())) {
				componentes[i] = "2";
				cuentaOIndicador = true;
			}
		}
		if (cuentaOIndicador == false) {
			throw new Exception(
					"Debe ingresar una formula que contenga al menos una cuenta o un indicador existente"
							+ ", o no se encuentra todo separado por espacios");
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

	public List<Cuenta> todasLasCuentas() {
		List<Empresa> empresas = this.getRepoEmpresas().filtrar(null, null,
				null, null);
		HashSet<Cuenta> cuentas = new HashSet<Cuenta>();
		empresas.forEach(empresa -> {
			empresa.getPeriodos().forEach(periodo -> {
				periodo.getCuentas().forEach(cuenta -> cuentas.add(cuenta));
			});
		});

		return new ArrayList<Cuenta>(cuentas);
	}

	public RepositorioIndicadores getRepoIndicadores() {
		return ApplicationContext.getInstance().getSingleton(Indicador.class);
	}

	public RepositorioEmpresa getRepoEmpresas() {
		return ApplicationContext.getInstance().getSingleton(Empresa.class);
	}
}
