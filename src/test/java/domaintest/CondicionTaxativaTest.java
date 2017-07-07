package domaintest;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import DataManagment.FileLoader;
import model.Empresa;

public class CondicionTaxativaTest {

	Empresa empresa;
	List<Empresa> empresas;
	
	@Before
	public void inicializar() throws Exception {
		empresas = new FileLoader().getDataEmpresas();
		empresa = empresas.get(0);		
	}
	
	@Test
	public void CondicionNetoDiscontinuasMayorA4Test() {
		assertTrue(new CondicionTaxativaMayorA("NetoDiscontinuas",4,1).calcular(empresa));
	}
	
}
