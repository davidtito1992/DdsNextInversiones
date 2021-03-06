package main.semanticaIndicador;

import java.math.BigDecimal;
import java.time.Year;

import main.app.AplicacionContexto;
import main.app.DslIndicador;
import main.formulaIndicador.FormulaIndicador;
import main.parserIndicador.ParseException;
import main.repositories.RepositorioEmpresa;
import main.repositories.RepositorioIndicador;
import model.RegistroIndicador;

public class SemanticaVariable {

	private String nombreVariable;
	private String nombreEmpresa;
	private Year anio;
	private int semestre;

	public SemanticaVariable(String nombreVariable, String nombreEmpresa, Year anio, int semestre) {

		this.nombreVariable = nombreVariable;
		this.nombreEmpresa = nombreEmpresa;
		this.anio = anio;
		this.semestre = semestre;
	}

	public BigDecimal valor() {

		BigDecimal valor;

		if (this.getRepositorioEmpresas().esCuenta(nombreVariable)) {

			// calculo del valor de una cuenta segun parametros
			valor = this.getRepositorioEmpresas().getValorCuenta(nombreEmpresa, anio, semestre, nombreVariable);

		} else {
			//
			// calculo del valor de un indicador segun parametros
			RegistroIndicador indicadorAObtener = this.getRepositorioIndicadores()
					.getRegistroIndicador(this.nombreVariable);

			try {
				FormulaIndicador variableIndicador = new DslIndicador().prepararFormula(indicadorAObtener,
						nombreEmpresa, anio, semestre);
				valor = variableIndicador.calcular();

			} catch (ParseException e) {

				throw new RuntimeException("Problemas al parsear la variable: " + this.nombreVariable);

			}
		}

		return valor;
	}

	public RepositorioIndicador getRepositorioIndicadores() {
		return AplicacionContexto.getInstance().getInstanceRepoIndicadores();
	}

	public RepositorioEmpresa getRepositorioEmpresas() {
		return AplicacionContexto.getInstance().getInstanceRepoEmpresas();
	}
}