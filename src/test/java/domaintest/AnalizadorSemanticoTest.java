package domaintest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import model.Empresa;
import model.RegistroIndicador;
import repositories.RepositorioEmpresa;
import repositoriesVIEJOS.RepositorioIndicadores;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.commons.utils.ApplicationContext;

import semanticaIndicador.AnalizadorSemantico;
import formulaIndicador.Variable;

public class AnalizadorSemanticoTest {
	ArrayList<Variable> variables;
	RepositorioEmpresa mockRepo;
	RepositorioIndicadores mockRepoIndicadores;
	AnalizadorSemantico analizador;
	Variable i1;
	Variable i2;
    Variable ebitda;
    Variable fds;
    Variable cuentaNueva1;
    Variable cuentaNueva2;
    Variable indicadorNuevo1;
    Variable indicadorNuevo2;
 	
	
	@Before
	public void init() {
		mockRepoIndicadores = mock(RepositorioIndicadores.class);
		mockRepo = mock(RepositorioEmpresa.class);
	    ApplicationContext.getInstance().configureSingleton(Empresa.class, mockRepo);
	    ApplicationContext.getInstance().configureSingleton(RegistroIndicador.class, mockRepoIndicadores);
	    variables = new ArrayList<Variable>();
	    analizador = new AnalizadorSemantico();
	    i1 = new Variable("I1");
	    i2 = new Variable("I2");
	    ebitda = new Variable("EBITDA");
	    fds = new Variable("FDS");
	    cuentaNueva1 = new Variable("cuentaNueva1");
	    cuentaNueva2 = new Variable("cuentaNueva2");
	    indicadorNuevo1 = new Variable("indicadorNuevo1");
	    indicadorNuevo2 = new Variable("indicadorNuevo2");
	    
	}
	
	@Test
	public void nombreIndicadorNuevoIgualAIndicadorExistente(){
		try{
			when(mockRepoIndicadores.esIndicador("I1")).thenReturn(true);
			analizador.analizarNombreDeIndicador(i1.getNombre());
		}catch(RuntimeException e){
			assertEquals("Existe un indicador con el nombre: " + i1.getNombre() + ", escriba otro nombre de indicador.", e.getMessage());
		}
	}
	
	@Test
	public void nombreDeIndicadorNuevoIgualACuentaExistente(){
		try{
			when(mockRepo.esCuenta("EBITDA")).thenReturn(true);
			analizador.analizarNombreDeIndicador(ebitda.getNombre());
		}catch(RuntimeException e){
			assertEquals("Existe una cuenta con el nombre: " + ebitda.getNombre() + ", escriba otro nombre de indicador.", e.getMessage());
		}
	}
	
	@Test
	public void variablesDeFormulaSonCuentasExistentes(){
		variables.add(ebitda);
		variables.add(fds);
		when(mockRepo.esCuenta("EBITDA")).thenReturn(true);
		when(mockRepo.esCuenta("FDS")).thenReturn(true);
		AnalizadorSemantico analizador = new AnalizadorSemantico();	
		analizador.analizarVariablesDeFormula(variables);
	}
	
	@Test(expected = RuntimeException.class)
	public void variablesDeFormulaSonCuentasInexistentes(){
		variables.add(cuentaNueva1);
		variables.add(cuentaNueva2);
		when(mockRepo.esCuenta("cuentaNueva1")).thenReturn(false);
		when(mockRepo.esCuenta("cuentaNueva2")).thenReturn(false);
		analizador.analizarVariablesDeFormula(variables);
	}
	
	@Test
	public void variablesDeFormulaSonIndicadoresExistentes(){
		variables.add(i1);
		variables.add(i2);
		when(mockRepoIndicadores.esIndicador("I1")).thenReturn(true);
		when(mockRepoIndicadores.esIndicador("I2")).thenReturn(true);
		analizador.analizarVariablesDeFormula(variables);
	}
	
	@Test(expected = RuntimeException.class)
	public void variablesDeFormulaSonIndicadoresInexistentes(){
		variables.add(indicadorNuevo1);
		variables.add(indicadorNuevo2);
		when(mockRepoIndicadores.esIndicador("indicadorNuevo1")).thenReturn(false);
		when(mockRepoIndicadores.esIndicador("indicadorNuevo2")).thenReturn(false);
		analizador.analizarVariablesDeFormula(variables);
	}
	
	@Test
	public void variablesDeFormulaSonIndicadoresYCuentasExistentes(){
		variables.add(i1);
		variables.add(ebitda);
		when(mockRepoIndicadores.esIndicador("I1")).thenReturn(true);
		when(mockRepo.esCuenta("EBITDA")).thenReturn(true);
		analizador.analizarVariablesDeFormula(variables);
	}
	
	@Test(expected = RuntimeException.class)
	public void variablesDeFormulaSonIndicadorExistenteYCuentaInexistente(){
		variables.add(i1);
		variables.add(cuentaNueva1);
		when(mockRepoIndicadores.esIndicador("I1")).thenReturn(true);
		when(mockRepo.esCuenta("cuentaNueva1")).thenReturn(false);
		analizador.analizarVariablesDeFormula(variables);
	}
	
	@Test(expected = RuntimeException.class)
	public void variablesDeFormulaSonIndicadorInexistenteYCuentaExistente(){
		variables.add(indicadorNuevo1);
		variables.add(fds);
		when(mockRepoIndicadores.esIndicador("indicadorNuevo1")).thenReturn(false);
		when(mockRepo.esCuenta("FDS")).thenReturn(true);
		analizador.analizarVariablesDeFormula(variables);
	}
	
	@Test
	public void variablesDeFormulaContieneUnaCuentaInexistente(){
		try{
			variables.add(cuentaNueva1);
			variables.add(i1);
			when(mockRepoIndicadores.esIndicador("I1")).thenReturn(true);
			when(mockRepo.esCuenta("cuentaNueva1")).thenReturn(false);
			analizador.analizarVariablesDeFormula(variables);
		}catch(RuntimeException e){
			assertEquals("El nombre de la variable: " + cuentaNueva1.getNombre() + " no existe", e.getMessage());
		}
	}
	
	@Test
	public void variablesDeFormulaContieneUnIndicadorInexistente(){
		try{
			variables.add(indicadorNuevo1);
			variables.add(fds);
			when(mockRepoIndicadores.esIndicador("indicadorNuevo1")).thenReturn(false);
			when(mockRepo.esCuenta("FDS")).thenReturn(true);
			analizador.analizarVariablesDeFormula(variables);
		}catch(RuntimeException e){
			assertEquals("El nombre de la variable: " + indicadorNuevo1.getNombre() + " no existe", e.getMessage());
		}
	}
}
	