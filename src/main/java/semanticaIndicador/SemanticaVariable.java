package semanticaIndicador;

import java.math.BigDecimal;
import java.time.Year;
import model.Empresa;
import model.RegistroIndicador;
import org.uqbar.commons.utils.ApplicationContext;
import app.Dsl;
import formulaIndicador.FormulaIndicador;
import parserIndicador.ParseException;
import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;

public class SemanticaVariable {

	private String nombreVariable;
	private String nombreEmpresa;
	private Year anio;
	private int semestre;

	public SemanticaVariable(String nombreVariable, String nombreEmpresa,
			Year anio, int semestre) {

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
			valor = this.getRepoEmpresas().getValorCuenta(nombreEmpresa, anio,
					semestre, nombreVariable);

		} else {
			//
			// calculo del valor de un indicador segun parametros
			// ver si esto lo movemos al repo
			RegistroIndicador indicadorAObtener = this.getRepoIndicadores()
					.getRegistroIndicador(this.nombreVariable);

			try {
				FormulaIndicador variableIndicador = new Dsl().prepararFormula(
						indicadorAObtener.getFormula(), nombreEmpresa, anio,
						semestre);
				valor = variableIndicador.calcular();
				
			} catch (ParseException e) {

				throw new RuntimeException("Problemas al parsear la variable: "
						+ this.nombreVariable);

			}
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
