package semanticaIndicador;

import java.math.BigDecimal;
import java.time.Year;

import model.Empresa;
import model.RegistroIndicador;

import org.uqbar.commons.utils.ApplicationContext;

import formulaIndicador.FormulaIndicador;
import parserIndicador.ParseException;
import parserIndicador.ParserIndicador;
import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;

public class SemanticaVariable {

	private String nombreVariable;
	private String nombreEmpresa;
	private int anio;
	private int semestre;

	public SemanticaVariable(String nombreVariable, String nombreEmpresa,
			int anio, int semestre) {

		this.nombreVariable = nombreVariable;
		this.nombreEmpresa = nombreEmpresa;
		this.anio = anio;
		this.semestre = semestre;
	}

	public BigDecimal valor() throws RuntimeException {

		BigDecimal valor;

		if (this.getRepoEmpresas().esCuenta(nombreVariable)) {

			// calculo del valor de una cuenta segun parametros
			// ver si esto lo movemos al repo
			valor = this.getRepoEmpresas().getValorCuenta(nombreEmpresa,
					Year.of(anio), semestre, nombreVariable);

		} else {

			// calculo del valor de un indicador segun parametros
			// ver si esto lo movemos al repo
			RegistroIndicador indicadorAObtener = this.getRepoIndicadores()
					.getRegistroIndicador(this.nombreVariable);
			FormulaIndicador formulaIndicador;
			try {
				formulaIndicador = new ParserIndicador(
						indicadorAObtener.getFormula()).pasear();
			} catch (ParseException e) {

				throw new RuntimeException("Problemas al parsear la variable: "
						+ this.nombreVariable);

			}
			valor = formulaIndicador.calcular(nombreEmpresa, anio, semestre);
		}

		return valor;
	}

	public RepositorioIndicadores getRepoIndicadores() {
		return ApplicationContext.getInstance().getSingleton(
				RegistroIndicador.class);
	}

	public RepositorioEmpresa getRepoEmpresas() {
		return ApplicationContext.getInstance().getSingleton(Empresa.class);
	}
}
