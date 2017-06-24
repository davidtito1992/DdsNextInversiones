package app;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import formulaIndicador.FormulaIndicador;
import model.RegistroIndicador;
import model.SnapshotIndicador;
import parserIndicador.ParseException;
import parserIndicador.ParserIndicador;
import semanticaIndicador.AnalizadorSemantico;
import semanticaIndicador.SemanticaVariable;

public class Dsl {

	public void añadirIndicador(RegistroIndicador posibleIndicador)
			throws Throwable {

		this.analizarNombreIndicador(posibleIndicador.getNombre());

		List<String> variablesIndicador = this
				.analizarFormulaIndicador(posibleIndicador.getFormula());

		posibleIndicador.setVariables(variablesIndicador);

		new AppData().guardarIndicador(posibleIndicador);

	}

	public BigDecimal traducirVariable(String nombreVariable,
			String nombreEmpresa, Year anio, int semestre) {

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

	public List<SnapshotIndicador> resultadosDeIndicador(
			RegistroIndicador unIndicador) {

		List<SnapshotIndicador> listaDeResultados = new ArrayList<SnapshotIndicador>();

		return listaDeResultados;
	}

	public FormulaIndicador prepararFormula(String formulaIndicador,
			String nombreEmpresa, Year anio, int semestre)
			throws ParseException {

		// String resultado;
		// try {
		ParserIndicador preIndicador = new ParserIndicador(formulaIndicador);
		FormulaIndicador formulaACalcular = preIndicador.pasear();

		preIndicador.variables().forEach(
				variable -> variable.setValor(this.traducirVariable(
						variable.getNombre(), nombreEmpresa, anio, semestre)));
		// resultado = formulaACalcular.calcular();

		return formulaACalcular;
	}

	// ParserIndicador unindicador = new ParserIndicador("EBITDA * FDS");
	// FormulaIndicador otroindicador= unindicador.pasear();
	// unindicador.variables().forEach(variable-> variable.setValor(new
	// BigDecimal(10))) ;
	// System.out.println(otroindicador.calcular()) ;
	// //System.out.println(variable.getNombre())
	// new NextInversiones().start();

}