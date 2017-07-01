package domaintest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.commons.utils.ApplicationContext;

import repositories.RepositorioEmpresa;
import repositories.RepositorioIndicadores;
import semanticaIndicador.AnalizadorSemantico;
import formulaIndicador.Variable;

public class AnalizadorSemanticoTest {
	ArrayList<Variable> variables;
	RepositorioEmpresa mockRepo;
	RepositorioIndicadores mockRepoIndicadores;
	AnalizadorSemantico analizador;
	String I1;
 	
	
	@Before
	public void init() {
		 mockRepo = mock(RepositorioEmpresa.class);
	    ApplicationContext.getInstance().configureSingleton(RepositorioEmpresa.class, mockRepo);
	    ApplicationContext.getInstance().configureSingleton(RepositorioIndicadores.class, mockRepoIndicadores);
	    variables = new ArrayList<Variable>();
	    analizador = new AnalizadorSemantico();
	    I1 = "I1";
	}
	
	@Test(expected = RuntimeException.class)
	public void variablesDeFormulaSonCuentas(){
	    Variable EBITDA = new Variable("EBITDA");
	    Variable FDS = new Variable("FDS");
		variables.add(EBITDA);
		variables.add(FDS);
		when(mockRepo.esCuenta("EBITDA")).thenReturn(true);
		when(mockRepo.esCuenta("FDS")).thenReturn(true);
		AnalizadorSemantico analizador = new AnalizadorSemantico();	
		analizador.analizarVariablesDeFormula(variables);
	}
	
	@Test(expected = RuntimeException.class)
	public void variablesDeFormulaSonIndicadores(){
		Variable I1 = new Variable("I1");
	    Variable I2 = new Variable("I2");
		variables.add(I1);
		variables.add(I2);
		when(mockRepoIndicadores.esIndicador("I1")).thenReturn(true);
		when(mockRepoIndicadores.esIndicador("I2")).thenReturn(true);
		analizador.analizarVariablesDeFormula(variables);
	}
	
	@Test(expected = RuntimeException.class)
	public void variablesDeFormulaSonIndicadoresYCuentas(){
		Variable I1 = new Variable("I1");
		Variable EBITDA = new Variable("EBITDA");
		variables.add(I1);
		variables.add(EBITDA);
		when(mockRepoIndicadores.esIndicador("I1")).thenReturn(true);
		when(mockRepo.esCuenta("EBITDA")).thenReturn(true);
		analizador.analizarVariablesDeFormula(variables);
	}
	
	@Test
	public void variableDeFormulaEsInexistente(){
		try{
			Variable indicador = new Variable("Indicador");
			Variable i1 = new Variable("I1");
			variables.add(i1);
			variables.add(indicador);
			when(mockRepoIndicadores.esIndicador("I1")).thenReturn(true);
			when(mockRepoIndicadores.esIndicador("Indicador")).thenReturn(false);
		}catch(RuntimeException e){
			assertEquals("El nombre de la variable: Indicador, no existe", e.getMessage());
		}
	}
	

	
	@Test(expected = RuntimeException.class)
	public void analizarNombreIndicadorExistente(){
		try{
			when(mockRepoIndicadores.esIndicador("I1")).thenReturn(true);
			analizador.analizarNombreDeIndicador("I1");
		}catch(RuntimeException e){
			assertEquals("Existe un indicador con el nombre: I1, escriba otro nombre de indicador.", e.getMessage());
		}
	}
	

}
	