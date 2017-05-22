package domaintest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import repositories.RepositorioEmpresa;
import model.Cuenta;
import model.Empresa;
import model.Periodo;


public class FiltrosUnitariosTest {
	
	public Empresa CrearEmpresaTest(){
		Empresa emp = new Empresa();
		Periodo per = new Periodo();
		Cuenta cue = new Cuenta();
		List<Cuenta> lCue = new ArrayList<Cuenta>();
		List<Periodo> lPer = new ArrayList<Periodo>();
		cue.setNombre("CTest");
		cue.setValor(10);
		lCue.add(cue);

		per.setAño(2017);
		per.setSemestre(2);
		per.setCuentas(lCue);
		lPer.add(per);
		emp.setNombre("Google");
		emp.setPeriodos(lPer);
		return emp;
	}
	
	@Test
	public void filtroNombreCorrecto (){
		RepositorioEmpresa repo = new RepositorioEmpresa();
		Empresa emp = CrearEmpresaTest();
		assertEquals(true,repo.filtroNombre("Google", emp));
	}
	
	@Test
	public void filtroNombreIncorrecto (){
		RepositorioEmpresa repo = new RepositorioEmpresa();
		Empresa emp = CrearEmpresaTest();
		assertEquals(false,repo.filtroNombre("NombreIncorrecto", emp));
	}
	
	@Test
	public void filtroAnioCorrecto (){
		RepositorioEmpresa repo = new RepositorioEmpresa();
		Empresa emp = CrearEmpresaTest();
		assertEquals(true,repo.filtroAnio(2017, emp));
	}
	
	@Test
	public void filtroAnioIncorrecto (){
		RepositorioEmpresa repo = new RepositorioEmpresa();
		Empresa emp = CrearEmpresaTest();
		assertEquals(false,repo.filtroAnio(1810, emp));
	}
	
	@Test
	public void filtroSemestreCorrecto (){
		RepositorioEmpresa repo = new RepositorioEmpresa();
		Empresa emp = CrearEmpresaTest();
		assertEquals(true,repo.filtroSemestre(2, emp));
	}
	
	@Test
	public void filtroSemestreIncorrecto(){
		RepositorioEmpresa repo = new RepositorioEmpresa();
		Empresa emp = CrearEmpresaTest();
		assertEquals(false,repo.filtroSemestre(97979, emp));
	}
	
	@Test
	public void filtroCuentaCorrecto (){
		RepositorioEmpresa repo = new RepositorioEmpresa();
		Empresa emp = CrearEmpresaTest();
		assertEquals(true,repo.filtroCuenta("CTest", emp));
	}
	
	@Test
	public void filtroCuentaIncorrecto (){
		RepositorioEmpresa repo = new RepositorioEmpresa();
		Empresa emp = CrearEmpresaTest();
		assertEquals(false,repo.filtroCuenta("CuentaIncorrecta", emp));
	}
	
	
	
	
}







