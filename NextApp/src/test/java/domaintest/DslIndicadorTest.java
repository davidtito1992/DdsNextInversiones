package domaintest;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.Year;

import main.app.DslIndicador;
import main.formulaIndicador.FormulaIndicador;
import main.parserIndicador.ParseException;
import main.semanticaIndicador.AnalizadorSemantico;
import model.RegistroIndicador;

import org.junit.Before;
import org.junit.Test;

public class DslIndicadorTest {

	RegistroIndicador indicadorConCuentas;
	RegistroIndicador indicadorConIndicadores;
	RegistroIndicador indicadorNombreIncorrecto;
	RegistroIndicador indicadorFormulaIncorrecta;
	AnalizadorSemantico mockAnalizadorSemantico;
	DslIndicador dslIndicador;

	@Before
	public void init() {
		indicadorConCuentas = new RegistroIndicador("Indicador1", "2+2*FDS");
		indicadorConIndicadores = new RegistroIndicador("Indicador2", "2+6*I1");
		indicadorNombreIncorrecto = new RegistroIndicador("+++", "3+3");
		indicadorFormulaIncorrecta = new RegistroIndicador("Indicador2", "3++4");
		dslIndicador = new DslIndicador();
	}

	/*@Test
	public void deberiaAnalizarNombreDeIndicadorCorrectamente()
			throws ParseException {

		dslIndicador.analizarNombreIndicador(indicadorConCuentas.getNombre());
	}*/

	@Test(expected = ParseException.class)
	public void deberiaLanzarParseExceptionPorErroresDeSintaxisAlAnalizarNombre()
			throws ParseException {
		dslIndicador.analizarNombreIndicador(indicadorNombreIncorrecto
				.getNombre());
	}

	@Test(expected = RuntimeException.class)
	public void deberiaLanzarRuntimeExceptionPorErrorSemanticoAlAnalizarNombre()
			throws ParseException {

		dslIndicador.analizarNombreIndicador(indicadorConCuentas.getNombre());
	}

	/*@Test
	public void deberiaPrepararUnaFormulaCorrectamente() throws ParseException {
		dslIndicador.prepararFormula(indicadorConCuentas, "facebook",
				Year.of(2016), 1);
	}

	@Test
	public void deberiaCalcularDespuesDePrepararUnaFormulaCorrectamente()
			throws ParseException {

		FormulaIndicador formulaIndicador = dslIndicador.prepararFormula(
				indicadorConCuentas, "facebook", Year.of(2016), 1);
		assertEquals(new BigDecimal(6).setScale(2),
				formulaIndicador.calcular());
	}*/

	@Test(expected = ParseException.class)
	public void deberiaLanzarParseExceptionPorErroresDeSintaxisAlPrepararUnaFormula()
			throws ParseException {
		dslIndicador.prepararFormula(indicadorFormulaIncorrecta, "Google",
				Year.of(2016), 1);
	}

	/*@Test
	public void deberiaAnalizarFormulaDeIndicadorCorrectamente()
			throws ParseException {

		dslIndicador.analizarFormulaIndicador(indicadorConCuentas.getFormula());
	}*/

	@Test(expected = ParseException.class)
	public void deberiaLanzarParserExceptionPorErroresDeSintaxisAlAnalizarFormula()
			throws ParseException {
		dslIndicador.analizarFormulaIndicador(indicadorFormulaIncorrecta
				.getFormula());
	}

	@Test(expected = RuntimeException.class)
	public void deberiaLanzarRuntimeExceptionPorErroresDeSemanticaAlAnalizarFormulaI()
			throws Exception {

		dslIndicador.analizarFormulaIndicador(indicadorConIndicadores
				.getFormula());
	}

	/*@Test(expected = RuntimeException.class)
	public void deberiaLanzarRuntimeExceptionPorErroresDeSemanticaAlAnalizarFormulaC()
			throws Exception {

		dslIndicador.analizarFormulaIndicador(indicadorConCuentas.getFormula());
	}*/

}
