package domaintest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Year;
import model.Empresa;
import model.RegistroIndicador;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.commons.utils.ApplicationContext;
import parserIndicador.ParseException;
import repositories.RepositorioEmpresa;
import repositoriesVIEJOS.RepositorioIndicadores;
import app.DslIndicador;
import semanticaIndicador.SemanticaVariable;

public class SemanticaDeVariablesTest {

	RepositorioEmpresa mockRepoEmpresas;
	RepositorioIndicadores mockRepoIndicadores;
	DslIndicador mockDslIndicador;
	RegistroIndicador indicador1 = new RegistroIndicador("I1", "60+40");

	// FormulaIndicador formulaI1 = new Expresion(new Constante("60"),
	// new Constante("40"), new Suma());

	@Before
	public void init() {

		mockRepoIndicadores = mock(RepositorioIndicadores.class);
		mockRepoEmpresas = mock(RepositorioEmpresa.class);
		ApplicationContext.getInstance().configureSingleton(Empresa.class,
				mockRepoEmpresas);
		ApplicationContext.getInstance().configureSingleton(
				RegistroIndicador.class, mockRepoIndicadores);

	}

	@Test
	public void deberiaObtenerElValorDeUnaVariableCuenta() {

		when(mockRepoEmpresas.esCuenta("Ebitda")).thenReturn(true);
		when(
				mockRepoEmpresas.getValorCuenta("FaceBook", Year.of(2016), 1,
						"Ebitda")).thenReturn(new BigDecimal("20").setScale(2));

		SemanticaVariable variable1 = new SemanticaVariable("Ebitda",
				"FaceBook", Year.of(2016), 1);

		assertEquals(new BigDecimal("20").setScale(2), variable1.valor());
	}

	@SuppressWarnings("unchecked")
	@Test(expected = RuntimeException.class)
	public void deberiaLanzarRuntimeExceptionNoSeEncontroElValorDeUnaVariableCuenta() {

		when(mockRepoEmpresas.esCuenta("FDS")).thenReturn(true);
		when(
				mockRepoEmpresas.getValorCuenta("FaceBook", Year.of(2016), 1,
						"FDS")).thenThrow(RuntimeException.class);

		SemanticaVariable variable1 = new SemanticaVariable("FDS", "FaceBook",
				Year.of(2016), 1);

		variable1.valor();
	}

	@Test
	public void deberiaObtenerElValorDeUnaVariableIndicador()
			throws ParseException {

		when(mockRepoEmpresas.esCuenta("I1")).thenReturn(false);

		when(mockRepoIndicadores.getRegistroIndicador("I1")).thenReturn(
				indicador1);

		SemanticaVariable variable2 = new SemanticaVariable("I1", "FaceBook",
				Year.of(2016), 1);

		assertEquals(new BigDecimal("100").setScale(2), variable2.valor());
	}

}
