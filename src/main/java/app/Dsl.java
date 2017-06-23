package app;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

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
			String nombreEmpresa, int anio, int semestre) {

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

		return preIndicador.variables();

	}
   
	public List<SnapshotIndicador> resultadosDeIndicador(RegistroIndicador unIndicador){
		
		List<SnapshotIndicador> listaDeResultados= new ArrayList<SnapshotIndicador>();
		
		return listaDeResultados;
	}

	public String aplicarFormula(FormulaIndicador formulaIndicador,String nombreEmpresa, Year anio, int semestre) {

		String resultado;
		try {
			resultado = formulaIndicador.calcular(nombreEmpresa,
					anio.getValue(), semestre).toPlainString();
		} catch (RuntimeException e) {
			resultado = e.getMessage();
		}
		return resultado;
	}

}