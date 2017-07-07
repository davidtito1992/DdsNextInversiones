package domaintest;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import parserIndicador.ParseException;
import condiciones.CondicionTaxativaMayorA;
import dataManagment.FileLoader;
import model.Empresa;
import model.RegistroIndicador;

public class CondicionTaxativaTest {

	Empresa empresa;
	List<Empresa> empresas;
	RegistroIndicador indicador;
	
	@Before
	public void inicializar() throws Exception {
		empresas = new FileLoader().getDataEmpresas();
		empresa = empresas.get(0);		
		indicador = new RegistroIndicador("NetoDiscontinuas","NetoDiscontinuas");
	}
	
	@Test
	public void CondicionNetoDiscontinuasMayorA4Test() throws ParseException {
		assertTrue(new CondicionTaxativaMayorA(indicador,BigDecimal.valueOf(4),2).calcular(empresa));
	}
	
}
