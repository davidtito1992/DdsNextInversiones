package domaintest;

import static org.mockito.Mockito.mock;

import java.time.Year;

import model.Empresa;
import model.RegistroIndicador;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.commons.utils.ApplicationContext;

import parserIndicador.ParseException;
import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;
import app.DslIndicador;

public class DslIndicadorTest {
	
	RepositorioEmpresa mockRepoEmpresas;
	RepositorioIndicadores mockRepoIndicadores;
	DslIndicador mockDslIndicador;
	RegistroIndicador indicador;
	RegistroIndicador indicadorNombreIncorrecto;
	RegistroIndicador indicadorFormulaIncorrecta;
	DslIndicador dslIndicador;
	
	
	
	@Before
	public void init() {

		mockRepoIndicadores = mock(RepositorioIndicadores.class);
		mockRepoEmpresas = mock(RepositorioEmpresa.class);
		ApplicationContext.getInstance().configureSingleton(Empresa.class, mockRepoEmpresas);
		ApplicationContext.getInstance().configureSingleton(RegistroIndicador.class, mockRepoIndicadores);
		indicador = new RegistroIndicador("Indicador","2+2");
		indicadorNombreIncorrecto = new RegistroIndicador("+++","3+3");
		indicadorFormulaIncorrecta = new RegistroIndicador("Indicador2","3++4");
		dslIndicador = new DslIndicador();
	}
	
	@Test 
	public void analizarNombreDeIndicadorCorrectoTest() throws Exception{
		dslIndicador.añadirIndicador(indicador);
	}
	
	@Test(expected = ParseException.class)
	public void analizarNombreIndicadorIncorrectoTest() throws Exception {
			dslIndicador.añadirIndicador(indicadorNombreIncorrecto);
	}
	
	@Test 
	public void analizarFormulaCorrectaDeIndicador() throws ParseException{
		dslIndicador.prepararFormula(indicador, "facebook",Year.of(2016) , 1);
	}
	
	@Test(expected = ParseException.class)
	public void analizarFormulaIncorrectaDeIndicador() throws ParseException{
		dslIndicador.prepararFormula(indicadorFormulaIncorrecta,"Google", Year.of(2016),1);
	}
	
	@Test
	public void traducirVariableTest(){
		
		
	}

}
