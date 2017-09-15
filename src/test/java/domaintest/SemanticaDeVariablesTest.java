package domaintest;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.time.Year;
import model.RegistroIndicador;
import org.junit.Before;
import org.junit.Test;
import parserIndicador.ParseException;
import semanticaIndicador.SemanticaVariable;

public class SemanticaDeVariablesTest {

	RegistroIndicador indicador1 = new RegistroIndicador("I1", "60+40");

	// FormulaIndicador formulaI1 = new Expresion(new Constante("60"),
	// new Constante("40"), new Suma());

	@Before
	public void init() {
	}

	@Test
	public void deberiaObtenerElValorDeUnaVariableCuenta() {

		SemanticaVariable variable1 = new SemanticaVariable("Ebitda",
				"FaceBook", Year.of(2016), 1);

		assertEquals(new BigDecimal("20").setScale(2), variable1.valor());
	}

	@Test(expected = RuntimeException.class)
	public void deberiaLanzarRuntimeExceptionNoSeEncontroElValorDeUnaVariableCuenta() {

		SemanticaVariable variable1 = new SemanticaVariable("FDS", "FaceBook",
				Year.of(2016), 1);

		variable1.valor();
	}

	@Test
	public void deberiaObtenerElValorDeUnaVariableIndicador()
			throws ParseException {

		SemanticaVariable variable2 = new SemanticaVariable("I1", "FaceBook",
				Year.of(2016), 1);

		assertEquals(new BigDecimal("100").setScale(2), variable2.valor());
	}

}
