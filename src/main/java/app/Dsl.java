package app;

import java.math.BigDecimal;

import model.RegistroIndicador;
import parserIndicador.ParserIndicador;
import semanticaIndicador.AnalizadorSemantico;
import semanticaIndicador.SemanticaVariable;

public class Dsl {

	public void añadirIndicador(RegistroIndicador posibleIndicador)
			throws Throwable {

		ParserIndicador preIndicador = new ParserIndicador(
				posibleIndicador.getFormula());
		preIndicador.pasear();

		new AnalizadorSemantico(preIndicador.variables()).analizar();

		posibleIndicador.setVariables(preIndicador.variables());

		new AppData().guardarIndicador(posibleIndicador);

	}

	public BigDecimal traducirVariable(String nombreVariable,
			String nombreEmpresa, int anio, int semestre) {
		return new SemanticaVariable(nombreVariable, nombreEmpresa, anio,
				semestre).valor();
	}
}