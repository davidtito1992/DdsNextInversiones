package domaintest;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import main.formulaIndicador.FormulaIndicador;
import main.parserIndicador.ParseException;
import main.parserIndicador.ParserIndicador;

import org.junit.Test;

public class ParserIndicadorTest {

	@Test
	public void deberiaPermitirParsearSinErroresDeSintaxis() throws Exception {
		new ParserIndicador("1.2 + 300").pasear();
	}

	@Test(expected = ParseException.class)
	public void noDeberiaPermitirParsearDebidoAErrorDeSintaxisSumaAlFinal()
			throws Exception {
		new ParserIndicador("1.2 + 300 +").pasear();
	}

	@Test(expected = ParseException.class)
	public void noDeberiaPermitirParsearDebidoAErrorDeSintaxisContieneEspacio()
			throws Exception {
		new ParserIndicador("1.2 + 300 5").pasear();
	}

	@Test
	public void deberiaSumarConstantes() throws Exception {
		ParserIndicador parser = new ParserIndicador("1 + 2");
		parser.pasear();
		FormulaIndicador formulaIndicador = parser.getFormulaIndicador();
		assertThat(formulaIndicador.calcular(),
				is(new BigDecimal("3").setScale(2)));
	}

	@Test
	public void deberiaRestarConstantes() throws Exception {
		ParserIndicador parser = new ParserIndicador("1 - 2");
		parser.pasear();
		FormulaIndicador formulaIndicador = parser.getFormulaIndicador();
		assertThat(formulaIndicador.calcular(),
				is(new BigDecimal("-1").setScale(2)));
	}

	@Test
	public void deberiaDividirConstantes() throws Exception {
		ParserIndicador parser = new ParserIndicador("1/2");
		parser.pasear();
		FormulaIndicador formulaIndicador = parser.getFormulaIndicador();
		assertThat(formulaIndicador.calcular(),
				is(new BigDecimal("0.5").setScale(2)));
	}

	@Test
	public void deberiaMultiplicarConstantes() throws Exception {
		ParserIndicador parser = new ParserIndicador("1 * 2.0");

		parser.pasear();
		FormulaIndicador formulaIndicador = parser.getFormulaIndicador();
		assertThat(formulaIndicador.calcular(),
				is(new BigDecimal("2").setScale(2)));
	}

	@Test
	public void deberiaSumarVariablesConConstantes() throws Exception {
		ParserIndicador parser = new ParserIndicador("EBITDA + 2");
		parser.pasear();
		parser.variables().forEach(
				variable -> variable.setValor(new BigDecimal("6")));
		FormulaIndicador formulaIndicador = parser.getFormulaIndicador();
		assertThat(formulaIndicador.calcular(),
				is(new BigDecimal("8").setScale(2)));
	}

	@Test
	public void deberiaRestarVariablesConConstantes() throws Exception {
		ParserIndicador parser = new ParserIndicador("FDS - 2");
		parser.pasear();
		parser.variables().forEach(
				variable -> variable.setValor(new BigDecimal("6")));
		FormulaIndicador formulaIndicador = parser.getFormulaIndicador();
		assertThat(formulaIndicador.calcular(),
				is(new BigDecimal("4").setScale(2)));
	}

	@Test
	public void deberiaDividirVariablesConConstantes() throws Exception {
		ParserIndicador parser = new ParserIndicador("FDS/2");
		parser.pasear();
		parser.variables().forEach(
				variable -> variable.setValor(new BigDecimal("10")));

		FormulaIndicador formulaIndicador = parser.getFormulaIndicador();
		assertThat(formulaIndicador.calcular(),
				is(new BigDecimal("5").setScale(2)));
	}

	@Test
	public void deberiaMultiplicarVariablesConConstantes() throws Exception {
		ParserIndicador parser = new ParserIndicador("EBITDA * 2");
		parser.pasear();
		parser.variables().forEach(
				variable -> variable.setValor(new BigDecimal("20")));
		FormulaIndicador formulaIndicador = parser.getFormulaIndicador();
		assertThat(formulaIndicador.calcular(),
				is(new BigDecimal("40").setScale(2)));
	}

	@Test
	public void deberiaCalcularConMuchasVariablesYConstantes() throws Exception {
		ParserIndicador parser = new ParserIndicador(
				"FDS/2 + FDS + I1 +EBITDA * 2");
		parser.pasear();
		parser.variables().forEach(variable -> {
			if (variable.getNombre().equalsIgnoreCase("FDS"))

				variable.setValor(new BigDecimal("10"));
			else if (variable.getNombre().equalsIgnoreCase("i1"))

				variable.setValor(new BigDecimal("20"));
			else if (variable.getNombre().equalsIgnoreCase("EBITDa"))

				variable.setValor(new BigDecimal("30"));
		});

		FormulaIndicador formulaIndicador = parser.getFormulaIndicador();
		assertThat(formulaIndicador.calcular(),
				is(new BigDecimal("95").setScale(2)));
	}

	@Test(expected = RuntimeException.class)
	public void deberiaLanzarExceptionDebidoALaVariableEbitda()
			throws Exception {
		ParserIndicador parser = new ParserIndicador("FDS+ EBITDA * 2");
		parser.pasear();
		parser.variables().forEach(variable -> {
			if (variable.getNombre().equalsIgnoreCase("FDS"))
				variable.setValor(new BigDecimal("10"));
			else if (variable.getNombre().equalsIgnoreCase("EBITDa"))
				throw new RuntimeException();
		});
	}

}