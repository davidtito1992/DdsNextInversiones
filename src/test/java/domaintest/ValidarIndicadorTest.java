package domaintest;

import static org.junit.Assert.*;
import model.Indicador;

import org.junit.Before;
import org.junit.Test;

public class ValidarIndicadorTest {
	Indicador indicadorSinNombre;
	Indicador indicadorCompuestoDeNumeros;
	Indicador indicadorNombreContieneEspacios;
	Indicador indicadorFormulaSinEspacios;
	Indicador indicadorConNombreYaExistente;
	Indicador indicadorMalParentesis;

	@Before
	public void initialize() {
		indicadorSinNombre = new Indicador(null, null);
		indicadorCompuestoDeNumeros = new Indicador(
				"indicadorCompuestoDeNumeros", "45 45 45 45");
		indicadorNombreContieneEspacios = new Indicador("Nombre con espacios",
				"EBITDA + 2");
		indicadorFormulaSinEspacios = new Indicador(
				"indicadorFormulaSinEspacios", "EBITDA+2");
		indicadorConNombreYaExistente = new Indicador("Indicador1",
				"EBITDA + 2");
		indicadorMalParentesis = new Indicador("indicadorMalParentesis",
				"EBITDA + 2 )");
	}

	@Test
	public void indicadorSinNombreTest() {
		try {
			indicadorSinNombre.guardarIndicador();
		} catch (Exception e) {
			assertEquals(
					"Debe ingresar nombre y formula para guardar correctamente. Intentelo nuevamente",
					e.getMessage());
		}
	}

	@Test
	public void indicadorCompuestoSoloDeNumerosTest() {
		try {
			indicadorCompuestoDeNumeros.guardarIndicador();
		} catch (Exception e) {
			assertEquals(
					"La formula no puede estar compuesta por numeros unicamente",
					e.getMessage());
		}
	}

	@Test
	public void indicadorNombreContieneEspaciosTest() {
		try {
			indicadorNombreContieneEspacios.guardarIndicador();
		} catch (Exception e) {
			assertEquals(
					"Favor de ingresar un nombre que no contenga espacios",
					e.getMessage());
		}
	}

//	@Test
//	public void indicadorFormulaSinEspaciosTest() {
//		try {
//			indicadorFormulaSinEspacios.guardarIndicador();
//		} catch (Exception e) {
//			assertEquals(
//					"Debe ingresar una formula que contenga al menos una cuenta o un indicador existente"
//							+ ", o no se encuentra todo separado por espacios",
//					e.getMessage());
//		}
//	}

//	@Test
//	public void indicadorConNombreYaExistenteTest() {
//		try {
//			indicadorConNombreYaExistente.guardarIndicador();
//		} catch (Exception e) {
//			assertEquals(
//					"Un indicador con ese nombre ya se encuentra cargado en el sistema, Intentelo nuevamente",
//					e.getMessage());
//		}
//	}

	@Test
	public void indicadorMalParentesisTest() {
		try {
			indicadorMalParentesis.guardarIndicador();
		} catch (Exception e) {
			assertEquals(
					"Se han ingresado mal los parentesis, o no se encuentra todo separado por espacios",
					e.getMessage());
		}
	}

}
