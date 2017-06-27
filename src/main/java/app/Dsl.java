package app;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import formulaIndicador.FormulaIndicador;
import model.RegistroIndicador;
import parserIndicador.ParseException;
import parserIndicador.ParserIndicador;
import semanticaIndicador.AnalizadorSemantico;
import semanticaIndicador.SemanticaVariable;

public class Dsl {

	public void añadirIndicador(RegistroIndicador posibleIndicador)
			throws Exception {

		this.analizarNombreIndicador(posibleIndicador.getNombre());

		List<String> variablesIndicador = this
				.analizarFormulaIndicador(posibleIndicador.getFormula());

		posibleIndicador.setVariables(variablesIndicador);

		new AppData().guardarIndicador(posibleIndicador);

	}

	public BigDecimal traducirVariable(String nombreVariable,
			String nombreEmpresa, Year anio, int semestre)
			throws RuntimeException {

		return new SemanticaVariable(nombreVariable, nombreEmpresa, anio,
				semestre).valor();
	}

	private void analizarNombreIndicador(String nombre) throws ParseException {

		String nombreIndicador = new ParserIndicador(nombre).pasearSoloNombre();

		new AnalizadorSemantico().analizarNombreDeIndicador(nombreIndicador);
	}

	private List<String> analizarFormulaIndicador(String formula)
			throws ParseException {

		ParserIndicador preIndicador = new ParserIndicador(formula);
		preIndicador.pasear();

		new AnalizadorSemantico().analizarVariablesDeFormula(preIndicador
				.variables());

		return preIndicador.variables().stream().map(var -> var.getNombre())
				.collect(Collectors.toList());

	}

	public FormulaIndicador prepararFormula(String formulaIndicador,
			String nombreEmpresa, Year anio, int semestre)
			throws ParseException {

		ParserIndicador preIndicador = new ParserIndicador(formulaIndicador);
		preIndicador.pasear();

		preIndicador.variables().forEach(
				variable -> variable.setValor(this.traducirVariable(
						variable.getNombre(), nombreEmpresa, anio, semestre)));

		FormulaIndicador formulaACalcular = preIndicador.getFormulaIndicador();

		return formulaACalcular;
	}

}