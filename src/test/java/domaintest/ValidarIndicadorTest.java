package domaintest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import model.Indicador;

import org.junit.Before;
import org.junit.Test;

import app.AppData;

public class ValidarIndicadorTest {
	Indicador indicadorSinNombre;
	Indicador indicadorCompuestoDeNumeros;
	Indicador indicadorNombreContieneEspacios;
	Indicador indicadorNuevo;
	Indicador indicadorConNombreYaExistente;
	ArrayList<Indicador> repoIndicadores;

	@Before
	public void initialize() {
		indicadorSinNombre = new Indicador(null, null);
		indicadorCompuestoDeNumeros = new Indicador(
				"IndicadorCompuestoDeNumeros", "45 45 45 45");
		indicadorNombreContieneEspacios = new Indicador("Nombre con espacios",
				"2+2");
		indicadorNuevo = new Indicador("IndicadorNuevo", "2*2");
		indicadorConNombreYaExistente = new Indicador("IndicadorNuevo", "2*3");
		indicadorNuevo = new Indicador("IndicadorNuevo", "IndicadorNuevo + 3");
	}

	@Test
	public void indicadorNoDebeContenerEspacios() {
		try {
			AppData app = new AppData();
			app.guardarIndicador(indicadorNombreContieneEspacios);
		} catch (Exception e) {
			assertEquals(
					"Favor de ingresar un nombre que no contenga espacios",
					e.getMessage());
		}
	}


}
