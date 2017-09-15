package domaintest;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import semanticaIndicador.AnalizadorSemantico;
import formulaIndicador.Variable;

public class AnalizadorSemanticoTest {
	ArrayList<Variable> variables;
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
			analizador.analizarNombreDeIndicador(i1.getNombre());
		}catch(RuntimeException e){
			assertEquals("Existe un indicador con el nombre: " + i1.getNombre() + ", escriba otro nombre de indicador.", e.getMessage());
		}
	}
	
	@Test
	public void nombreDeIndicadorNuevoIgualACuentaExistente(){
		try{
			analizador.analizarNombreDeIndicador(ebitda.getNombre());
		}catch(RuntimeException e){
			assertEquals("Existe una cuenta con el nombre: " + ebitda.getNombre() + ", escriba otro nombre de indicador.", e.getMessage());
		}
	}
	
	@Test
	public void variablesDeFormulaSonCuentasExistentes(){
		variables.add(ebitda);
		variables.add(fds);
		AnalizadorSemantico analizador = new AnalizadorSemantico();	
		analizador.analizarVariablesDeFormula(variables);
	}
	
	@Test(expected = RuntimeException.class)
	public void variablesDeFormulaSonCuentasInexistentes(){
		variables.add(cuentaNueva1);
		variables.add(cuentaNueva2);
		analizador.analizarVariablesDeFormula(variables);
	}
	
	@Test
	public void variablesDeFormulaSonIndicadoresExistentes(){
		variables.add(i1);
		variables.add(i2);
		analizador.analizarVariablesDeFormula(variables);
	}
	
	@Test(expected = RuntimeException.class)
	public void variablesDeFormulaSonIndicadoresInexistentes(){
		variables.add(indicadorNuevo1);
		variables.add(indicadorNuevo2);
		analizador.analizarVariablesDeFormula(variables);
	}
	
	@Test
	public void variablesDeFormulaSonIndicadoresYCuentasExistentes(){
		variables.add(i1);
		variables.add(ebitda);
		analizador.analizarVariablesDeFormula(variables);
	}
	
	@Test(expected = RuntimeException.class)
	public void variablesDeFormulaSonIndicadorExistenteYCuentaInexistente(){
		variables.add(i1);
		variables.add(cuentaNueva1);
		analizador.analizarVariablesDeFormula(variables);
	}
	
	@Test(expected = RuntimeException.class)
	public void variablesDeFormulaSonIndicadorInexistenteYCuentaExistente(){
		variables.add(indicadorNuevo1);
		variables.add(fds);
		analizador.analizarVariablesDeFormula(variables);
	}
	
	@Test
	public void variablesDeFormulaContieneUnaCuentaInexistente(){
		try{
			variables.add(cuentaNueva1);
			variables.add(i1);
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
			analizador.analizarVariablesDeFormula(variables);
		}catch(RuntimeException e){
			assertEquals("El nombre de la variable: " + indicadorNuevo1.getNombre() + " no existe", e.getMessage());
		}
	}
}
	