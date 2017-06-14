package domaintest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import model.RegistroIndicador;

import org.junit.Before;
import org.junit.Test;

import app.AppData;

public class ValidarIndicadorTest {
	RegistroIndicador indicadorSinNombre;
	RegistroIndicador indicadorCompuestoDeNumeros;
	RegistroIndicador indicadorNombreContieneEspacios;
	RegistroIndicador indicadorNuevo;
	RegistroIndicador indicadorConNombreYaExistente;
	ArrayList<RegistroIndicador> repoIndicadores;

	@Before
	public void initialize() {
		indicadorSinNombre = new RegistroIndicador(null, null);
		indicadorCompuestoDeNumeros = new RegistroIndicador(
				"IndicadorCompuestoDeNumeros", "45 45 45 45");
		indicadorNombreContieneEspacios = new RegistroIndicador("Nombre con espacios",
				"2+2");
		indicadorNuevo = new RegistroIndicador("IndicadorNuevo", "2*2");
		indicadorConNombreYaExistente = new RegistroIndicador("IndicadorNuevo", "2*3");
		indicadorNuevo = new RegistroIndicador("IndicadorNuevo", "IndicadorNuevo + 3");
	}

	@Test
	public void indicadorNoDebeContenerEspacios() {
		try {
			AppData app = new AppData();
			app.guardarIndicador(indicadorNombreContieneEspacios);
		} catch (Throwable e) {
			assertEquals(
					"Favor de ingresar un nombre que no contenga espacios",
					e.getMessage());
		}
	}


}
