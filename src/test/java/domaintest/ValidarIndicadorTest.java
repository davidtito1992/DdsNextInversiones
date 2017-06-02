package domaintest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import model.Indicador;

import org.junit.Before;
import org.junit.Test;

public class ValidarIndicadorTest {
	Indicador indicadorSinNombre;
	Indicador indicadorCompuestoDeNumeros;
	Indicador indicadorNombreContieneEspacios;
	Indicador indicadorNuevo;
	Indicador indicadorConNombreYaExistente;
	ArrayList<Indicador> repoIndicadores;
	
	@Before
	public void initialize() {
		indicadorSinNombre =  new Indicador(null,null);
		indicadorCompuestoDeNumeros = new Indicador("IndicadorCompuestoDeNumeros","45 45 45 45");
		indicadorNombreContieneEspacios = new Indicador("Nombre con espacios", "2+2");
		indicadorNuevo = new Indicador("IndicadorNuevo","2*2"); 
		indicadorConNombreYaExistente = new Indicador("IndicadorNuevo","2*3");
//		repoIndicadores = new ArrayList<Indicador>();
//		repoIndicadores.add(indicadorNuevo);
		indicadorNuevo = new Indicador("IndicadorNuevo", "IndicadorNuevo + 3");
	}
	
//	@Test 
//	public void indicadorSinNombreTest(){
//		try{
//			indicadorSinNombre.guardarIndicador();
//		}catch(Exception e){
//			assertEquals("Debe ingresar nombre y formula para guardar correctamente. Intentelo nuevamente", e.getMessage());
//		}
//	}
//
//	@Test
//	public void indicadorCompuestoSoloDeNumeros(){
//		try{
//			indicadorCompuestoDeNumeros.guardarIndicador();
//		}catch(Exception e){
//			assertEquals("La formula no puede estar compuesta por numeros unicamente", e.getMessage());
//		}
//	}
//	
//	@Test
//	public void indicadorNoDebeContenerEspacios(){
//		try{
//			indicadorNombreContieneEspacios.guardarIndicador();
//		}catch(Exception e){
//			assertEquals("Favor de ingresar un nombre que no contenga espacios", e.getMessage());
//		}
//	}
//	
////	@Test 
////	public void indicadorConNombreYaExistente(){
////		try{
////			indicadorConNombreYaExistente.guardarIndicador();
////		}catch(Exception e){
////			assertEquals("Un indicador con ese nombre ya se encuentra cargado en el sistema, Intentelo nuevamente", e.getMessage());
////		}
////	}
//	
//	@Test
//	public void testIndicadorConNombreExistenteEnLaFormula(){
//		try{
//			indicadorNuevo.guardarIndicador();
//		}catch(Exception e){
//			assertEquals("No puede usar ese nombre porque se encuentra dentro de la formula del mismo", e.getMessage());
//		}
//	}
	
}
