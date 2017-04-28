package domaintest;

import static org.junit.Assert.*;
import java.util.ArrayList;
import model.*;
import org.junit.Test;
import app.*;


public class NombreEmpresaTest {
	
	private ArrayList<String> nombresEsperados = new ArrayList<String>();

	@Test	
	public void NombreEmpresaTest() {
		generarNombresEsperados();
		Mixin.cargarEmpresas();
		assertEquals(nombresEsperados, RepositorioMaestro.dameNombresEmpresas());
	}
	
	public void generarNombresEsperados(){
		nombresEsperados.add("Facebook");
		nombresEsperados.add("Google");
		nombresEsperados.add("Twitter");
		
	}

}

