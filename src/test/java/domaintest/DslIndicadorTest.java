package domaintest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.time.Year;
import model.Empresa;
import model.RegistroIndicador;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.commons.utils.ApplicationContext;
import formulaIndicador.FormulaIndicador;
import parserIndicador.ParseException;
import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;
import semanticaIndicador.AnalizadorSemantico;
import app.DslIndicador;

public class DslIndicadorTest {

	RepositorioEmpresa mockRepoEmpresas;
	RepositorioIndicadores mockRepoIndicadores;
	DslIndicador mockDslIndicador;
	RegistroIndicador indicadorConCuentas;
	RegistroIndicador indicadorConIndicadores;
	RegistroIndicador indicadorNombreIncorrecto;
	RegistroIndicador indicadorFormulaIncorrecta;
	AnalizadorSemantico mockAnalizadorSemantico;
	DslIndicador dslIndicador;

	@Before
	public void init() {

		mockRepoIndicadores = mock(RepositorioIndicadores.class);
		mockRepoEmpresas = mock(RepositorioEmpresa.class);
		ApplicationContext.getInstance().configureSingleton(Empresa.class,
				mockRepoEmpresas);
		ApplicationContext.getInstance().configureSingleton(
				RegistroIndicador.class, mockRepoIndicadores);
		indicadorConCuentas = new RegistroIndicador("Indicador1", "2+2*FDS");
		indicadorConIndicadores = new RegistroIndicador("Indicador2", "2+6*I1");
		indicadorNombreIncorrecto = new RegistroIndicador("+++", "3+3");
		indicadorFormulaIncorrecta = new RegistroIndicador("Indicador2", "3++4");
		dslIndicador = new DslIndicador();
	}

	@Test
	public void deberiaAnalizarNombreDeIndicadorCorrectamente()
			throws ParseException {

		dslIndicador.analizarNombreIndicador(indicadorConCuentas.getNombre());
	}

	@Test(expected = ParseException.class)
	public void deberiaLanzarParseExceptionPorErroresDeSintaxisAlAnalizarNombre()
			throws ParseException {
		dslIndicador.analizarNombreIndicador(indicadorNombreIncorrecto
				.getNombre());
	}

	@Test(expected = RuntimeException.class)
	public void deberiaLanzarRuntimeExceptionPorErrorSemanticoAlAnalizarNombre()
			throws ParseException {

		when(mockRepoEmpresas.esCuenta("Indicador1")).thenReturn(true);
		dslIndicador.analizarNombreIndicador(indicadorConCuentas.getNombre());
	}

	@Test
	public void deberiaPrepararUnaFormulaCorrectamente() throws ParseException {
		when(mockRepoEmpresas.esCuenta("FDS")).thenReturn(true);
		when(
				mockRepoEmpresas.getValorCuenta("facebook", Year.of(2016), 1,
						"FDS")).thenReturn(new BigDecimal(10).setScale(2));
		dslIndicador.prepararFormula(indicadorConCuentas, "facebook",
				Year.of(2016), 1);
	}

	@Test
	public void deberiaCalcularDespuesDePrepararUnaFormulaCorrectamente()
			throws ParseException {

		when(mockRepoEmpresas.esCuenta("FDS")).thenReturn(true);
		when(
				mockRepoEmpresas.getValorCuenta("facebook", Year.of(2016), 1,
						"FDS")).thenReturn(new BigDecimal(10).setScale(2));

		FormulaIndicador formulaIndicador = dslIndicador.prepararFormula(
				indicadorConCuentas, "facebook", Year.of(2016), 1);
		assertEquals(new BigDecimal(22).setScale(2),
				formulaIndicador.calcular());
	}

	@Test(expected = ParseException.class)
	public void deberiaLanzarParseExceptionPorErroresDeSintaxisAlPrepararUnaFormula()
			throws ParseException {
		dslIndicador.prepararFormula(indicadorFormulaIncorrecta, "Google",
				Year.of(2016), 1);
	}

	@Test
	public void deberiaAnalizarFormulaDeIndicadorCorrectamente()
			throws ParseException {

		when(mockRepoEmpresas.esCuenta("FDS")).thenReturn(true);

		dslIndicador.analizarFormulaIndicador(indicadorConCuentas.getFormula());
	}

	@Test(expected = ParseException.class)
	public void deberiaLanzarParserExceptionPorErroresDeSintaxisAlAnalizarFormula()
			throws ParseException {
		dslIndicador.analizarFormulaIndicador(indicadorFormulaIncorrecta
				.getFormula());
	}

	@Test(expected = RuntimeException.class)
	public void deberiaLanzarRuntimeExceptionPorErroresDeSemanticaAlAnalizarFormulaI()
			throws Exception {

		when(mockRepoIndicadores.esIndicador("I1")).thenReturn(false);

		dslIndicador.analizarFormulaIndicador(indicadorConIndicadores
				.getFormula());
	}

	@Test(expected = RuntimeException.class)
	public void deberiaLanzarRuntimeExceptionPorErroresDeSemanticaAlAnalizarFormulaC()
			throws Exception {

		when(mockRepoEmpresas.esCuenta("FDS")).thenReturn(false);

		dslIndicador.analizarFormulaIndicador(indicadorConCuentas.getFormula());
	}

}
